package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.DBConnection;

public class CommuteDAO {
	private static CommuteDAO instance;
	
	public static CommuteDAO getInstance() {
		if(instance == null) {
			instance = new CommuteDAO();
		}
		return instance;
	}
	
	public void goToWork(CommuteDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into commute(number, start_time, whether_commute) values(?, ?, ?)";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getNumber());
			pstmt.setString(2, dto.getStart_time());
			pstmt.setBoolean(3, true);
			pstmt.executeUpdate();
		} catch(Exception ex) {
			System.out.println("goToWork()" + ex);
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
	
	public ArrayList getWhetherCommute(String number, String start_time) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean whetherCommute = false;
		String start_date = null;
		ArrayList list = new ArrayList();
		String sql = "select start_time, whether_commute from commute where number = ? and start_time = ?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, number);
			pstmt.setString(2, start_time);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				whetherCommute = rs.getBoolean("whether_commute");
				list.add(whetherCommute);
				start_date = rs.getString("start_time");
				list.add(start_date);
			}
			
		} catch(Exception ex) {
			System.out.println("getWhetherCommute()" + ex);
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
		return list;
	}
}
