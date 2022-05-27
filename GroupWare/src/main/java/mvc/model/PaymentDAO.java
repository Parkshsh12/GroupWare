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
	public int[] getSalary(int normal_pay) {
		int[] salary = new int[9];
		int normal = normal_pay*174;
	  	int holiday = normal_pay*35;
	  	int income_tax = 0;
	  	int national_Pension = (int)((normal + holiday) * 0.045);
	 	int health_Insurance = (int)((normal + holiday) * 0.03495);
	 	int employment_Insurance = (int)((normal + holiday) * 0.0025);
	 	int sanje = (int)((normal + holiday) * 0.0007);
	  	if((normal + holiday)*12 <= 12000000){
	  		income_tax = (int)((normal + holiday)*0.06);
	  	}
	  	else if((normal + holiday)*12 > 12000000 && (normal + holiday)*12 <= 46000000){
	  		int x = 1080000/12;
	  		income_tax = (int)((normal + holiday)*0.15 - x);
	  	}
	  	else if((normal + holiday)*12 > 46000000 && (normal + holiday)*12 <= 88000000){
	  		int x = 5220000/12;
	  		income_tax = (int)((normal + holiday)*0.24 - x);
	  	}
	  	else if((normal + holiday)*12 > 88000000 && (normal + holiday)*12 <= 150000000){
	  		int x = 14900000/12;
	  		income_tax = (int)((normal + holiday)*0.35 - x);
	  	}
	  	else if((normal + holiday)*12 > 150000000 && (normal + holiday)*12 <= 300000000){
	  		int x = 19400000/12;
	  		income_tax = (int)((normal + holiday)*0.38 - x);
	  	}
	  	else if((normal + holiday)*12 > 300000000 && (normal + holiday)*12 <= 500000000){
	  		int x = 25400000/12;
	  		income_tax = (int)((normal + holiday)*0.40 - x);
	  	}
	  	else if((normal + holiday)*12 > 500000000){
	  		int x = 35400000/12;
	  		income_tax = (int)((normal + holiday)*0.42 - x);
	  	}
	  	salary[0] = normal;
	  	salary[1] = holiday;
	  	salary[2] = national_Pension;
	  	salary[3] = health_Insurance;
	  	salary[4] = employment_Insurance;
	  	salary[5] = sanje;
	  	salary[6] = income_tax;
	  	salary[7] = normal + holiday;
	  	salary[8] =	national_Pension + health_Insurance + employment_Insurance + income_tax + sanje;
	  	
	  	return salary;
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
				dto.setPosition_pay(rs.getInt("position_pay"));
				dto.setMargin_pay(rs.getInt("margin_pay"));
				paymentList.add(dto);
				
				if(index < (start + limit) && index <= total_record) {
					index++;
				} else {
					break;
				}
			}
			return paymentList;
		} catch(Exception ex) {
			System.out.println("getAllCompanyList ¿¹¿Ü : " + ex);
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
