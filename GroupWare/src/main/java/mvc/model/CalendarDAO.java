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
}
