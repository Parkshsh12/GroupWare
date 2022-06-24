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
		String result = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			if (type.equals("SearchPw")) {
				sql = "select pw from emplyee where name = ? and number = ? and email = ?";
			} else {
				// 占쎈툡占쎌뵠占쎈탵 筌≪뼐由�
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, number);
			pstmt.setString(3, email);
		} catch (Exception ex) {

		}
		return result;
	}

	// shpark start
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

			if (rs.next()) {
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
		} catch (Exception ex) {
			System.out.println("getBoardByNum()" + ex);
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
		} catch (Exception ex) {
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

	public ArrayList<MemberDTO> memberList(ArrayList<MemberDTO> memberlist) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql;

		try {
			sql = "select * from employee";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberDTO dto = new MemberDTO();

				dto.setNumber(rs.getString("number"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setAddress(rs.getString("address"));
				dto.setPhone(rs.getString("phone"));
				dto.setPosition(rs.getString("position"));
				dto.setDepartment(rs.getString("department"));
				dto.setEmail(rs.getString("email"));
				dto.setJoin_date(rs.getString("join_date"));
				dto.setHourlywage(rs.getInt("hourlywage"));
				memberlist.add(dto);
			}

		} catch (Exception ex) {
			System.out.println("memberList()오류 : " + ex);
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
				new RuntimeException(ex.getMessage());
			}
		}
		return memberlist;
	}

	public String SearchPw(String name, String number, String email) {
		String result = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select pw from employee where name = ? and number = ? and email = ?";
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, number);
			pstmt.setString(3, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("pw");
				System.out.println(result);
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
		return result;
	}

	public int getallMemberListCount(String search_item, String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;

		String sql;

		if (search_item == null && text == null) {
			sql = "select count(*) from employee";
		} else {
			sql = "select count(*) from employee where " + search_item + " like '%" + text + "%'";
		}
		System.out.println(search_item);
		System.out.println(text);
		ArrayList<MemberDTO> memberlist = new ArrayList<MemberDTO>();

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getAllMemberListCount() �삁�쇅 : " + ex);
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

	public ArrayList<MemberDTO> getallMemberList(int pageNum, int limit, String search_item, String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int total_record = getallMemberListCount(search_item, text);
		int start = (pageNum - 1) * limit;
		int index = start + 1;
		String sql;

		if (search_item == null && text == null) {
			sql = "select * from employee ORDER BY number desc";
		} else {
			sql = "select * from employee where " + search_item + " like '%" + text + "%' order by number desc";
		}
		ArrayList<MemberDTO> memberlist = new ArrayList<MemberDTO>();

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.absolute(index)) {
				MemberDTO member = new MemberDTO();
				member.setNumber(rs.getString("number"));
				member.setName(rs.getString("name"));
				member.setAddress(rs.getString("address"));
				member.setPhone(rs.getString("phone"));
				member.setPosition(rs.getString("position"));
				member.setDepartment(rs.getString("department"));
				member.setEmail(rs.getString("email"));
				member.setJoin_date(rs.getString("join_date"));
				memberlist.add(member);
				if (index < (start + limit) && index <= total_record) {
					index++;
				} else {
					break;
				}
			}
		} catch (Exception ex) {
			System.out.println("getallMemberList() �삁�쇅" + ex);
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
		return memberlist;
	}
	
	public void insertMember(MemberDTO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into member values(?,?,?,?,?,?,?,?)";
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getNumber());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getPosition());
			pstmt.setString(6, member.getDepartment());
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getJoin_date());
			
			
			pstmt.executeUpdate();
		} catch(Exception ex) {
			System.out.println("insertMember()" + ex);
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
	public MemberDTO getMemberByNum(int num) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    MemberDTO member = null;
	    
	    String sql = "select * from employee where number = ?";
	    updateMemberHit(num);
	    
	    try {
	        conn = DBConnection.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, num);
	        rs = pstmt.executeQuery();
	        
	        if(rs.next()) {
	        	/*MemberDTO member = new MemberDTO();*/
	        	member.setNumber(rs.getString("number"));
	            member.setName(rs.getString("name"));
	            member.setAddress(rs.getString("address"));
	            member.setPhone(rs.getString("phone"));
	            member.setPosition(rs.getString("position"));
	            member.setDepartment(rs.getString("department"));
	            member.setEmail(rs.getString("email"));
	            member.setJoin_date(rs.getString("join_date"));
	            member.setHourlywage(rs.getInt("hourlywage"));
	        }
	        return member;
	    } catch(Exception ex) {
	        System.out.println("getMemberByNum()" + ex);
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
	public void updateMemberHit(int num) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        conn = DBConnection.getConnection();
	        
	        String sql = "select hit from member where seq = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, num);
	        rs = pstmt.executeQuery();
	        
	        int hit = 0;
	        
	        if(rs.next()) {
	            hit = rs.getInt("hit") + 1;
	        }
	        
	        sql = "update member set hit = ? where seq = ?";
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
	public void updateMember(MemberDTO member) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
	        String sql = "update employee set number = ?, name = ?, address = ?, phone = ?, position = ?, department = ?, email = ?, join_date = ?, hourlywage = ? where number = ?";
	        conn = DBConnection.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        
	        pstmt.setString(1, member.getNumber());
	        pstmt.setString(2, member.getName());
	        pstmt.setString(3, member.getAddress());
	        pstmt.setString(4, member.getPhone());
	        pstmt.setString(5, member.getPosition());
	        pstmt.setString(6, member.getDepartment());
	        pstmt.setString(7, member.getEmail());
	        pstmt.setString(8, member.getJoin_date());
	        pstmt.setInt(9, member.getHourlywage());
	        
	        pstmt.executeUpdate();
	    } catch(Exception ex) {
	        System.out.println("updateMember()" + ex);
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
	public void deleteMember(int num) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
	        String sql = "delete from employee where seq = ?";
	        conn = DBConnection.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, num);
	        pstmt.executeUpdate();
	    } catch(Exception ex) {
	        System.out.println("deleteMember()" + ex);
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
	public String SearchId(String name, String email, String pw) {
		String result = "";
		Connection conn=null;
		PreparedStatement pstmt = null;
		String sql = "select number from employee where name = ? and email = ? and pw =?";
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, pw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString("number");
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
		return result;
	}
}
