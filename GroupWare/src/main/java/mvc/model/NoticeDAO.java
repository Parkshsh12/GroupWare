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
			System.out.println("getAllNoticeListCount() ���ܹ߻� : " + ex);
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
			System.out.println("updateHit() ���ܹ߻� : " + ex);
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
	
	public int setNoticeAdd(String number, String name, String title, String content) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int seq;
		
		long milliseconds = System.currentTimeMillis();
		Date now = new Date(milliseconds);
		
		String date = now.toString();
		String sql;
		
		System.out.println(number);
		System.out.println(name);
		System.out.println(title);
		System.out.println(content);
		System.out.println(date);
		
		
		
		sql = "insert into notice (number, name, title, content, b_date) values ('?','?','?','?','?');";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, number);
			pstmt.setString(2, name);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, date);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("setNoticeAdd() ���� : " + ex);
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
		return 1;
		
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
			System.out.println("getNoticeNum() ���ܹ߻� : " + ex);
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
			System.out.println("getAllNoticeList() ���ܹ߻� : " + ex);
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
