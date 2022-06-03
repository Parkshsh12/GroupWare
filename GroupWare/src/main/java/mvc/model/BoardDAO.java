package mvc.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;

import database.DBConnection;

public class BoardDAO {
	private static BoardDAO instance;
	
	private BoardDAO() {
		
	}
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	//board record count
	public int getallBoardListCount(String search_item, String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		
		String sql;
		
		if((search_item==null) && (text==null)) {
			sql = "select count(*) from board";
		} else {
			sql = "select count(*) from board where " + search_item + " like '%" + text + "%'";
		}
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception ex) {
			System.out.println("getAllBoardListCount() 예외발생 : " + ex);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
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
		return x;
	}
	
	public ArrayList<BoardDTO> getallBoardList(int pageNum, int limit, String search_item, String text){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int total_record = getallBoardListCount(search_item, text);
		int start = (pageNum - 1) * limit;
		int index = start + 1;
		
		String sql;

		if(search_item == null && text == null) {
			sql = "select * from board ORDER BY seq desc";

		} else {
			sql = "select * from board where " + search_item + " like '%" + text + "%' order by seq desc";
		}
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.absolute(index)) {
				BoardDTO board = new BoardDTO();
				board.setName(rs.getString("name"));
				board.setContent(rs.getString("content"));
				board.setHit(rs.getInt("hit"));
				board.setNumber(rs.getString("number"));
				board.setTitle(rs.getString("title"));
				board.setB_date(rs.getString("b_date"));
				board.setSeq(rs.getInt("seq"));
				list.add(board);
				
				if(index < (start + limit) && index <= total_record) {
					index++;
				}
				else {
					break;
				}
			}
			return list;
		} catch(Exception ex) {
			System.out.println("getallBoardList()" + ex);
		}finally {
			try {
				if(rs != null) {
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
		return null;
	}
	
	public String getLoginNameByNumber(String number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String name = null;
		String sql = "select * from employee where number = ?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				name = rs.getString("name");
			}
			return name;
		} catch(Exception ex) {
			System.out.println(ex);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return null;
	}
	
	public void insertBoard(BoardDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into board values(?, ?, ?, ?, ?, ?, ?)";
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getNumber());
			pstmt.setString(2, board.getName());
			pstmt.setInt(3, board.getHit());
			pstmt.setString(4, board.getTitle());
			pstmt.setString(5, board.getContent());
			pstmt.setInt(6, board.getSeq());
			pstmt.setString(7, board.getB_date());
			
			pstmt.executeUpdate();
		} catch(Exception ex) {
			System.out.println("insertBoard()" + ex);
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
	
	public BoardDTO getBoardByNum(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO board = null;
		
		String sql = "select * from board where seq = ?";
		updateBoardHit(num);
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardDTO();
				board.setNumber(rs.getString("number"));
				board.setName(rs.getString("name"));
				board.setHit(rs.getInt("hit"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setSeq(rs.getInt("seq"));
				board.setB_date(rs.getString("b_date"));
			}
			return board;
		} catch(Exception ex) {
			System.out.println("getBoardByNum()" + ex);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
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
		return null;
	}
	
	public void updateBoardHit(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			
			String sql = "select hit from board where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			int hit = 0;
			
			if(rs.next()) {
				hit = rs.getInt("hit") + 1;
			}
			
			sql = "update board set hit = ? where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, hit);
			pstmt.setInt(2, num);
			pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("updatehit()" + e);
		}finally {
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
	
	public void updateBoard(BoardDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update board set title = ?, content = ? where seq = ?";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getSeq());
			
			pstmt.executeUpdate();
		} catch(Exception ex) {
			System.out.println("updateBoard()" + ex);
		} finally {
			try {										
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}		
		}
	}
	public void deleteBoard(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "delete from board where seq = ?";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch(Exception ex) {
			System.out.println("deleteBoard()" + ex);
		}  finally {
			try {										
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}		
		}
	}
}
