package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.DBConnection;

public class CompanyDAO {

	private static CompanyDAO instance;

	private CompanyDAO() {
	}

	public static CompanyDAO getInstance() {
		if (instance == null) {
			instance = new CompanyDAO();
		}
		return instance;
	}

	public ArrayList<CompanyDTO> getAllCompanyList(String search_item, String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql;

		if (search_item == null && text == null) {
			sql = "select * from business_company order by seq asc";
			System.out.println("이거실행");
		} else {
			sql = "select * from business_company where " + search_item + " like '%" + text + "%' order by seq asc";
			System.out.println("다른거 실행");
		}
		System.out.println(search_item);
		System.out.println(text);
		ArrayList<CompanyDTO> list = new ArrayList<CompanyDTO>();

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CompanyDTO company = new CompanyDTO();
				company.setP_company(rs.getString("p_company"));
				company.setP_industry(rs.getString("p_industry"));
				company.setP_address(rs.getString("p_address"));
				company.setP_person(rs.getString("p_person"));
				company.setP_companyNum(rs.getString("p_companyNum"));
				company.setP_personNum(rs.getString("p_personNum"));
				company.setSeq(rs.getInt("seq"));
				list.add(company);
			}
			return list;
		} catch (Exception ex) {
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
		return list;
	}

	public void CompanyAdd(CompanyDTO company) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql;

		try {
			sql = "insert into business_company(p_company,p_industry,p_address,p_person,p_companyNum,p_personNum) values( ?, ?, ?, ?, ?, ? )";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, company.getP_company());
			pstmt.setString(2, company.getP_industry());
			pstmt.setString(3, company.getP_address());
			pstmt.setString(4, company.getP_person());
			pstmt.setString(5, company.getP_companyNum());
			pstmt.setString(6, company.getP_personNum());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("CompanyAdd() 에러 : " + ex);
		} finally {
			try {
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

	public CompanyDTO getCompany(String seq) {
		CompanyDTO dto = new CompanyDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql;

		try {
			sql = "select * from business_company where seq = ?";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, seq);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setP_company(rs.getString("p_company"));
				dto.setP_industry(rs.getString("p_industry"));
				dto.setP_address(rs.getString("p_address"));
				dto.setP_person(rs.getString("p_person"));
				dto.setP_companyNum(rs.getString("p_companyNum"));
				dto.setP_personNum(rs.getString("p_personNum"));
				dto.setSeq(rs.getInt("seq"));
			}

		} catch (Exception ex) {
			System.out.println("getCompany() 에러 : " + ex);
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

		return dto;
	}
	public void deleteCompany(int seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql;
		
		try {
			sql = "delete from business_company where seq = ?";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
		
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
		} catch(Exception ex) {
			System.out.println("dropCompany 예외 : " + ex);
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
	
	public void companyUpdateAction(CompanyDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql;
		
		try {
			sql = "update business_company set p_industry = ? , p_address = ? , p_person = ? , p_companyNum = ? , p_personNum = ? where seq = ?";
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getP_industry());
			pstmt.setString(2, dto.getP_address());
			pstmt.setString(3, dto.getP_person());
			pstmt.setString(4, dto.getP_companyNum());
			pstmt.setString(5, dto.getP_personNum());
			pstmt.setInt(6, dto.getSeq());
			
			pstmt.executeUpdate();
		} catch(Exception ex) {
			System.out.println("companyUpdateAction() 에러 : " + ex);
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
}
