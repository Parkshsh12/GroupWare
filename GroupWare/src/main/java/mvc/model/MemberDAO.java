package mvc.model;

import java.sql.*;

import database.DBConnection;

public class MemberDAO {

	private static MemberDAO instance;

	private MemberDAO() {
	}

	public static MemberDAO getInstance() {
		if (instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}

	public String[] loginMember(String number, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql;
		ResultSet rs = null;
		String[] array = new String[3];
		try {
			conn = DBConnection.getConnection();
			sql = "select * from employee where number = ? and pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, number);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				array[0] = rs.getString("number");
				array[1] = rs.getString("name");
				if (array[0].equals("0000")) {
					array[2] = "admin";
				} else {
					array[2] = "employee";
				}
				System.out.println("login[0] : " + array[0]);
				System.out.println("login[1] : " + array[1]);
				System.out.println("login[2] : " + array[2]);
				return array;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return array;
	}

	public String SearchIdPw(String number, String name, String email, String type) {
		String result="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs=null;
		try 
		{
			conn = DBConnection.getConnection();
			if(type.equals("SearchPw"))
			{
				System.out.println("鍮꾨�踰덊샇 李얘린�엫");
				sql = "select pw from emplyee where name = ? and number = ? and email = ?";
			}
			else {
				// �븘�씠�뵒 李얘린	
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, number);
			pstmt.setString(3, email);
		} catch(Exception ex) {
			
		}
		return result;
	}
	//shpark start
	public MemberDTO getMemberById(String number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO member = null;
		
		String sql = "select * from employee where number = ?";
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberDTO();
				member.setPw(rs.getString("pw"));
				member.setNumber(rs.getString("number"));
				member.setName(rs.getString("name"));
				member.setDepartment(rs.getString("department"));
				member.setPosition(rs.getString("position"));
				member.setAddress(rs.getString("address"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				member.setJoin_date(rs.getString("join_date"));
			}
			return member;
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
	
	public void updateInfo(MemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update employee set pw = ?, address = ?, email = ?, phone = ? where number = ?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getAddress());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getPhone());
			pstmt.setString(5, dto.getNumber());
			pstmt.executeUpdate();
		} catch(Exception ex) {
			System.out.println("updateInfo()" + ex);
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
}
