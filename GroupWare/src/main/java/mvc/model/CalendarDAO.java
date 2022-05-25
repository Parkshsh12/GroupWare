package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.DBConnection;

public class CalendarDAO {
	private CalendarDAO() {
		
	}
	
	private static CalendarDAO instance;
	
	public static CalendarDAO getInstance() {
		if(instance == null) {
			instance = new CalendarDAO();
		}
		return instance;
	}
	
	public ArrayList<CalendarDTO> getCalendarContent(String department){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CalendarDTO> list = new ArrayList<CalendarDTO>();
		
		String sql = "select * from calendar where department = ?";
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, department);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CalendarDTO dto = new CalendarDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setNumber(rs.getString("number"));
				dto.setName(rs.getString("name"));
				dto.setC_title(rs.getString("c_title"));
				dto.setC_content(rs.getString("c_content"));
				dto.setDepartment(rs.getString("department"));
				dto.setStart_date(rs.getString("start_date"));
				dto.setEnd_date(rs.getString("end_date"));
				list.add(dto);
			}
			return list;
		} catch(Exception ex) {
			System.out.println("getCalendarContent()" + ex);
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
	public void insertDepSchedule(CalendarDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into calendar(number,name,c_title,c_content,start_date,end_date,department) values(?,?,?,?,?,?,?)";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getNumber());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getC_title());
			pstmt.setString(4, dto.getC_content());
			pstmt.setString(5, dto.getStart_date());
			pstmt.setString(6, dto.getEnd_date());
			pstmt.setString(7, dto.getDepartment());
			pstmt.executeUpdate();
		} catch(Exception ex) {
			System.out.println("insertDepSchedule()" + ex);
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
	public CalendarDTO getCalendarSeq(int seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CalendarDTO dto = null;
		String sql = "select * from calendar where seq = ?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new CalendarDTO();
				dto.setC_content(rs.getString("c_content"));
				dto.setC_title(rs.getString("c_title"));
				dto.setDepartment(rs.getString("department"));
				dto.setEnd_date(rs.getString("end_date"));
				dto.setStart_date(rs.getString("start_date"));
				dto.setName(rs.getString("name"));
				dto.setNumber(rs.getString("number"));
				dto.setSeq(rs.getInt("seq"));
			}
			return dto;
		} catch(Exception ex) {
			System.out.println("getCalendarSeq()" + ex);
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
	public void deleteSchedule(int seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from calendar where seq = ?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
		} catch(Exception ex) {
			System.out.println("deleteSchedule()" + ex);
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
	public ArrayList<CalendarDTO> getAllCalendar(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CalendarDTO dto = null;
		ArrayList<CalendarDTO> calendarList = new ArrayList<CalendarDTO>();
		String sql = "select * from calendar";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new CalendarDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setNumber(rs.getString("number"));
				dto.setName(rs.getString("name"));
				dto.setC_title(rs.getString("c_title"));
				dto.setC_content(rs.getString("c_content"));
				dto.setDepartment(rs.getString("department"));
				dto.setStart_date(rs.getString("start_date"));
				dto.setEnd_date(rs.getString("end_date"));
				calendarList.add(dto);
			}
			return calendarList;
		} catch(Exception ex) {
			System.out.println("getAllCalendar()" + ex);
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
		return null;
	}
}
