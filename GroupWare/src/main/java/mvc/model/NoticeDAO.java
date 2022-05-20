package mvc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;

import java.sql.PreparedStatement;
import database.DBConnection;

public class NoticeDAO {

	private static NoticeDAO instance;

	private NoticeDAO() {
	}

	public static NoticeDAO getInstance() {
		if (instance == null) {
			instance = new NoticeDAO();
		}
		return instance;
	}
	
	public int getNoticeNext(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int seq = 0;
		String sql;
		sql = "select seq from notice order by seq desc";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				seq = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getNoticeNext() 예외 발생 : " + ex);
		} finally {
			try {
				if( rs != null ) {
					rs.close();
				}
				if ( pstmt != null) {
					pstmt.close();
				}
				if ( conn != null ) {
					conn.close();
				}
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return seq;
	}

	public int getAllNoticeListCount(String search_item, String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;
		
		String sql;

		if (search_item == null && text == null) {
			sql = "select count(*) from notice";
		} else {
			sql = "select count(*) from notice where " + search_item + " like '%" + text + "%'";
		}

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getAllNoticeListCount() 예외발생 : " + ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return x;
	}

	public void updateNoticeHit(int seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();

			String sql = "select hit from notice where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();

			int hit = 0;

			if (rs.next()) {
				hit = rs.getInt("hit") + 1;
			}

			sql = "update notice set hit = ? where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, hit);
			pstmt.setInt(2, seq);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("updateHit() 예외발생 : " + ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}
	
	public void setNoticeDelete(int seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql;
		
		try {
			conn = DBConnection.getConnection();
			sql = "delete from notice where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("setNoticeDelete : " + ex);
		} finally {
			try {
				
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		
	}
	public void updateNoticeSubmit(int seq, String title, String content) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql;

		try {
			conn = DBConnection.getConnection();
			sql = "update notice set title = ? , content = ? where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, seq);
			pstmt.executeUpdate();
		} catch(Exception ex) {
			System.out.println("updateNoticeSubmit() : " + ex);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}
	
	public void setNoticeAdd(NoticeDTO notice) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql;
		
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String date = formatter.format(new java.util.Date()); 
		notice.setB_date(date);
		
		sql = "insert into notice (number, name, title, content, b_date) values (?, ?, ?, ?, ?)";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice.getNumber());
			pstmt.setString(2, notice.getName());
			pstmt.setString(3, notice.getTitle());
			pstmt.setString(4, notice.getContent());
			pstmt.setString(5, notice.getB_date());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("setNoticeAdd() 에러 : " + ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}

	public NoticeDTO getNoticeNum(int seq, int pageNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeDTO notice = null;
		
		String sql;

		updateNoticeHit(seq);
		
		sql = "select * from notice where seq = ? ";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				notice = new NoticeDTO();
				notice.setTitle(rs.getString("title"));
				notice.setName(rs.getString("name"));
				notice.setB_date(rs.getString("b_date"));
				notice.setHit(rs.getInt("hit"));
				notice.setContent(rs.getString("content"));
				notice.setNumber(rs.getString("number"));
				notice.setSeq(rs.getInt("seq"));
			}
		} catch(Exception ex) {
			System.out.println("getNoticeNum() 예외발생 : " + ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch(Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return notice;
	}

	public ArrayList<NoticeDTO> getAllNoticeList(int pageNum, int limit, String search_item, String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int total_record = getAllNoticeListCount(search_item, text);
		int start = (pageNum - 1) * limit;
		int index = start + 1;

		String sql;

		if (search_item == null && text == null) {
			sql = "select * from notice order by seq desc";
		} else {
			sql = "select * from notice where " + search_item + " like '%" + text + "%' order by seq desc";
		}
		ArrayList<NoticeDTO> list = new ArrayList<NoticeDTO>();

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.absolute(index)) {
				NoticeDTO notice = new NoticeDTO();
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setName(rs.getString("name"));
				notice.setB_date(rs.getString("b_date"));
				notice.setHit(rs.getInt("hit"));
				notice.setSeq(rs.getInt("seq"));
				list.add(notice);

				if (index < (start + limit) && index <= total_record) {
					index++;
				} else {
					break;
				}
			}
			return list;
		} catch (Exception ex) {
			System.out.println("getAllNoticeList() 예외발생 : " + ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return null;
	}
	
	


}
