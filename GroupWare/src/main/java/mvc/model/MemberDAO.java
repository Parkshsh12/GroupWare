package mvc.model;

import java.sql.*;
import java.util.ArrayList;

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
			}
			return array;
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
				sql = "select pw from emplyee where name = ? and number = ? and email = ?";
			}
			else {
				// 占쎈툡占쎌뵠占쎈탵 筌≪뼐由�	
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
				member.setHourlywage(rs.getInt("hourlywage"));
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
	public ArrayList<MemberDTO> getMember(int pageNum, int limit, String search_item, String text){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO dto = null;
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		int total_record = getAllMemberCount(search_item, text);
		int start = (pageNum - 1) * limit;
		int index = start + 1;
		String sql = null;
		if(search_item == null && text == null) {
			sql = "select * from employee order by join_date";			
		} else {
			sql = "select * from employee where " + search_item + " like '%" + text + "%' order by join_date";
		}
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.absolute(index)) {
				dto = new MemberDTO();
				dto.setName(rs.getString("name"));
				dto.setNumber(rs.getString("number"));
				dto.setAddress(rs.getString("address"));
				dto.setPhone(rs.getString("phone"));
				dto.setPosition(rs.getString("position"));
				dto.setDepartment(rs.getString("department"));
				dto.setEmail(rs.getString("email"));
				dto.setJoin_date(rs.getString("join_date"));
				System.out.println(dto.getName());
				list.add(dto);
				
				if(index < (start + limit) && index <= total_record) {
					index++;
				}
				else {
					break;
				}
			}
			return list;
		} catch(Exception ex) {
			System.out.println("getMember()" + ex);
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
		return null;
	}
	
	public int getAllMemberCount(String search_item, String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		String sql = null;
		
		if((search_item==null) && (text==null)) {
			sql = "select count(*) from employee";
		} else {
			sql = "select count(*) from employee where " + search_item + " like '%" + text + "%'";
		}
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt(1);
			} 
		} catch(Exception ex) {
			System.out.println("getAllMemberCount()" + ex);
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
}
