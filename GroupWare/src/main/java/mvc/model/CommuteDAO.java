package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
		
		String sql = "insert into commute(number, start_time) values(?, ?)";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getNumber());
			pstmt.setString(2, dto.getStart_time());
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
	
	public String getWhetherCommute(String number, String start_date) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String commute_Log = null;
		String sql = "select * from commute where number = ? and start_time like '%" + start_date + "%'";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				commute_Log = rs.getString("commute_log");
			}
			return commute_Log;
			
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
		return null;
	}
	
	public void setLeaveWork(String commute_Log) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String end_time = formatter.format(new Date());
		String sql = "update commute set end_time = ? where commute_log = ?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, end_time);
			pstmt.setString(2, commute_Log);
			pstmt.executeUpdate();
		} catch(Exception ex) {
			System.out.println("setLeaveWork()" + ex);
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
	
	public String getEndTime(String number, String end_date) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String end_time = null;
		String sql = "select * from commute where number = ? and start_time = ?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, number);
			pstmt.setString(2, end_date);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				end_time = rs.getString("end_time");
			}
			return end_time;
			
		} catch(Exception ex) {
			System.out.println("getEndTime()" + ex);
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
	
	public String getStartTime(String number, String start_date) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String start_time = null;
		String sql = "select * from commute where number = ? and end_time like '%" + start_date + "%'";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				start_time = rs.getString("start_time");
			}
			return start_time;
			
		} catch(Exception ex) {
			System.out.println("getEndTime()" + ex);
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
}
