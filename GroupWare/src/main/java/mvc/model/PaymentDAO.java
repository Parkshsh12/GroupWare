package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.DBConnection;

public class PaymentDAO {
	private static PaymentDAO instance;
	
	public static PaymentDAO getInstance() {
		if(instance == null) {
			instance = new PaymentDAO();
		}
		return instance;
	}
	public int getAllpaymentCount(String number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		String sql = "select count(*) from manager_pay where number = ?";
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt(1);
			} 
		} catch(Exception ex) {
			System.out.println("getAllpaymentCount()" + ex);
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
	
	public ArrayList<PaymentDTO> getPaymentList(int pageNum, int limit, String number){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PaymentDTO dto = null;
		int total_record = getAllpaymentCount(number);
		int start = (pageNum - 1) * limit;
		int index = start + 1;
		ArrayList<PaymentDTO> paymentList = new ArrayList<PaymentDTO>();
		String sql = "select * from manager_pay where number = ? order by payment_date desc";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();
			
			while(rs.absolute(index)) {
				dto = new PaymentDTO();
				dto.setPaynumber(rs.getInt("paynumber"));
				dto.setNumber(rs.getString("number"));
				dto.setImputed_date(rs.getString("imputed_date"));
				dto.setPayment_date(rs.getString("payment_date"));
				paymentList.add(dto);
				
				if(index < (start + limit) && index <= total_record) {
					index++;
				} else {
					break;
				}
			}
			System.out.println("된다9");
			return paymentList;
		} catch(Exception ex) {
			System.out.println("getAllCompanyList 예외 : " + ex);
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
