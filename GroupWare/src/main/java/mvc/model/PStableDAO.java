package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.DBConnection;

public class PStableDAO {
	private static PStableDAO instance;

	public static PStableDAO getInstance() {
		if (instance == null) {
			instance = new PStableDAO();
		}
		return instance;
	}

	public ArrayList<PStableDTO> getPStableList(ArrayList<PStableDTO> list, String division) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql;

		try {

			if (division.equals("p")) {
				sql = "select * from pstable where division = 'p' order by date desc";
			} else if (division.equals("s")) {
				sql = "select * from pstable where division = 's' order by date desc";
			} else {
				sql = "select * from pstable order by company asc";
			}

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

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

	public ArrayList getBusinessList(ArrayList list) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String year = "2022";

		ArrayList Array = new ArrayList();

		String sql;

		try {
			sql = "select distinct company from pstable";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			int size = 0;

			for (int i = 0; rs.next(); i++) {
				size++;
			}

			rs = pstmt.executeQuery();

			String[] t_companyList = new String[size];
			int purchase_t[][] = new int[size][12];
			int sales_t[][] = new int[size][12];

			int month_f[] = new int[12];
			int month_s[] = new int[12];
			
			for (int i = 0; i < size; i++) {
				rs.next();
				t_companyList[i] = rs.getString("company");
			}

			for (int i = 0; i < list.size(); i++) {
				PStableDTO dto = (PStableDTO) list.get(i);

				for (int j = 0; j < t_companyList.length; j++) {
					int date = Integer.parseInt(dto.getDate().substring(5, 7))-1;

					if (t_companyList[j].equals(dto.getCompany())) {

						if (dto.getDivision().equals("p")) {
							sql = "select qty*price from pstable where division = 'p' and company = '" + t_companyList[j]
									+ "' and seq = '" + dto.getSeq() + "'";
							pstmt = conn.prepareStatement(sql);
							rs = pstmt.executeQuery();

							while (rs.next()) {
								
								purchase_t[j][date] += rs.getInt(1);
								month_f[date] += rs.getInt(1);
							}
						} else {
							sql = "select qty*price from pstable where division = 's' and company = '" + t_companyList[j]
									+ "' and seq = '" + dto.getSeq() + "'";
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
			Array.add(t_companyList);
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
		} catch(Exception ex) {
			System.out.println("setPStable() 예외 : " + ex);
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
