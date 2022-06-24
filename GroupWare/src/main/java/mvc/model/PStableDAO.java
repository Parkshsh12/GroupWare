package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import database.DBConnection;

public class PStableDAO {
	private static PStableDAO instance;

	public static PStableDAO getInstance() {
		if (instance == null) {
			instance = new PStableDAO();
		}
		return instance;
	}

	public int getPStableCount(String search_item, String text, String division) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;

		String sql;

		try {
			if (search_item != null && search_item != "") {
				if (division != null) {
					sql = "select count(*) from PStable where division = '" + division + "' and " + search_item
							+ " like '%" + text + "%'";
				} else {
					sql = "select count(*) from PStable where " + search_item + " like '%" + text + "%'";
				}
			} else {
				sql = "select count(*) from PStable";
			}

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getPStableCount() 에러 : " + ex);
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

	public ArrayList<PStableDTO> getPStableList(ArrayList<PStableDTO> list, String division, String company,
			String search_item, String text, int pageNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int total_record = getPStableCount(search_item, text, division);
		int start = (pageNum - 1) * 5;
		int index = start + 1;

		String sql;
		
		try {
			if (division.equals("p") || division.equals("s")) {
				if (search_item == null && search_item.equals("")) {
					sql = "select * from pstable where division = '" + division + "' order by date desc";
				} else {
					sql = "select * from pstable where division = '" + division + "' and " + search_item + " like '%" + text
							+ "%' order by date desc";
				}
			} else {
				if (company != null && company.equals("")) {
					sql = "select * from pstable order by company asc";
					index = 1;
				} else {
					sql = "select * from pstable where company like'%" + company + "%' order by company asc";
					index = 1;
				}
			}

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			if(division.equals("p") || division.equals("s")) {
				while (rs.absolute(index)) {
					PStableDTO dto = new PStableDTO();
					dto.setCompany(rs.getString("company"));
					dto.setCategory(rs.getString("category"));
					dto.setName(rs.getString("name"));
					dto.setQty(rs.getInt("qty"));
					dto.setUnit(rs.getString("unit"));
					dto.setPrice(rs.getInt("price"));
					dto.setBecause(rs.getString("because"));
					dto.setSeq(rs.getInt("seq"));
					dto.setDate(rs.getString("date"));
					dto.setDivision(rs.getString("division"));
					list.add(dto);
					if (index < (start + 5) && index <= total_record) {
						index++;
					} else {
						break;
					}
				}
			} else {
				while (rs.next()) {
					PStableDTO dto = new PStableDTO();
					dto.setCompany(rs.getString("company"));
					dto.setCategory(rs.getString("category"));
					dto.setName(rs.getString("name"));
					dto.setQty(rs.getInt("qty"));
					dto.setUnit(rs.getString("unit"));
					dto.setPrice(rs.getInt("price"));
					dto.setBecause(rs.getString("because"));
					dto.setSeq(rs.getInt("seq"));
					dto.setDate(rs.getString("date"));
					dto.setDivision(rs.getString("division"));
					list.add(dto);

				}
			}
		} catch (Exception ex) {
			System.out.println("getPStableList() 에러 : " + ex);
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

	public ArrayList getBusinessList(ArrayList list, String year) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList Array = new ArrayList();

		String sql;
		
		try {
			conn = DBConnection.getConnection();

			String[] arraylist = new String[list.size()];
			
			for(int i = 0; i < list.size(); i++) {
				arraylist[i] = ((PStableDTO) list.get(i)).getCompany();
			}
			HashSet<String> hashset = new HashSet<>(Arrays.asList(arraylist));
			String[] resultArr = hashset.toArray(new String[0]);		
			
			int purchase_t[][] = new int[resultArr.length][12];
			int sales_t[][] = new int[resultArr.length][12];
			int month_f[] = new int[12];
			int month_s[] = new int[12];

			for (int i = 0; i < list.size(); i++) {
				PStableDTO dto = (PStableDTO) list.get(i);

				for (int j = 0; j < resultArr.length; j++) {
					int date = Integer.parseInt(dto.getDate().substring(5, 7)) - 1;

					if (resultArr[j].equals(dto.getCompany())) {

						if (dto.getDivision().equals("p")) {
							sql = "select qty*price from pstable where division = 'p' and company = '"
									+ resultArr[j] + "' and seq = '" + dto.getSeq() + "' and date like '" + year
									+ "%'";
							pstmt = conn.prepareStatement(sql);
							rs = pstmt.executeQuery();

							while (rs.next()) {

								purchase_t[j][date] += rs.getInt(1);
								month_f[date] += rs.getInt(1);
							}
						} else {
							sql = "select qty*price from pstable where division = 's' and company = '"
									+ resultArr[j] + "' and seq = '" + dto.getSeq() + "' and date like '" + year
									+ "%'";
							pstmt = conn.prepareStatement(sql);
							rs = pstmt.executeQuery();

							while (rs.next()) {
								sales_t[j][date] += rs.getInt(1);
								month_s[date] += rs.getInt(1);
							}
						}
					}
				}
			}
			Array.add(resultArr);
			Array.add(purchase_t);
			Array.add(sales_t);
			Array.add(month_f);
			Array.add(month_s);
		} catch (Exception ex) {
			System.out.println("getBusinessList() 에러 : " + ex);
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

		return Array;
	}

	public void setPStable(PStableDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql;
		try {
			sql = "insert into pstable(division,company,category,name,qty,unit,price,because,date) values (?,?,?,?,?,?,?,?,?)";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getDivision());
			pstmt.setString(2, dto.getCompany());
			pstmt.setString(3, dto.getCategory());
			pstmt.setString(4, dto.getName());
			pstmt.setInt(5, dto.getQty());
			pstmt.setString(6, dto.getUnit());
			pstmt.setInt(7, dto.getPrice());
			pstmt.setString(8, dto.getBecause());
			pstmt.setString(9, dto.getDate());

			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("setPStable() 예외 : " + ex);
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

	public String[] getCompanyList(String[] companyNameList) {
		companyNameList = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql;

		try {
			conn = DBConnection.getConnection();

			sql = "select distinct p_company, count(*) as count from business_company";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			companyNameList = new String[rs.getInt("count")];

			sql = "select distinct p_company from business_company";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				companyNameList[i] = rs.getString("p_company");
				i++;
			}
		} catch (Exception ex) {
			System.out.println("getCompanyList() 에러 : " + ex);
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

		return companyNameList;

	}

	public PStableDTO getpsUpdate(PStableDTO dto, int seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql;

		try {
			sql = "select * from pstable where seq = ?";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto.setSeq(rs.getInt("seq"));
				dto.setDivision(rs.getString("division"));
				dto.setCompany(rs.getString("company"));
				dto.setCategory(rs.getString("category"));
				dto.setName(rs.getString("name"));
				dto.setQty(rs.getInt("qty"));
				dto.setUnit(rs.getString("unit"));
				dto.setPrice(rs.getInt("price"));
				dto.setDate(rs.getString("date"));
				dto.setBecause(rs.getString("because"));
			}
		} catch (Exception ex) {
			System.out.println("getpsUpdate() 에러 : " + ex);
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

	public void setupdate(PStableDTO dto, int seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql;

		try {
			sql = "update pstable set division = ?, company = ?, category = ?, name = ?, qty = ?, unit = ?, price = ?, because = ?, date = ? where seq = ?";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getDivision());
			pstmt.setString(2, dto.getCompany());
			pstmt.setString(3, dto.getCategory());
			pstmt.setString(4, dto.getName());
			pstmt.setInt(5, dto.getQty());
			pstmt.setString(6, dto.getUnit());
			pstmt.setInt(7, dto.getPrice());
			pstmt.setString(8, dto.getBecause());
			pstmt.setString(9, dto.getDate());
			pstmt.setInt(10, seq);

			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("setupdate() 에러 : " + ex);
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
}
