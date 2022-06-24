package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import database.DBConnection;

public class CommuteDAO {

	private static CommuteDAO instance;

	private CommuteDAO() {
	}

	public static CommuteDAO getInstance() {
		if (instance == null) {
			instance = new CommuteDAO();
		}
		return instance;
	}

	public boolean chkCommute(String number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = formatter.format(new java.util.Date());
		String commute_log = date.substring(0, 10);

		try {
			sql = "select number, commute_log from commute where number = '" + number + "' and commute_log = '"
					+ commute_log + "'";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception ex) {
			System.out.println("chkCommute() 에러 : " + ex);
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
		return false;
	}

	public void newStartCommute(String number) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = formatter.format(new java.util.Date());
		String commute_log = date.substring(0, 10);

		try {
			sql = "insert into commute(number, commute_log, start_time, chk) values ('" + number + "', '" + commute_log
					+ "', '" + date + "', true)";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("startCommute() 에러 : " + ex);
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

	public void updateStartCommute(String number) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = formatter.format(new java.util.Date());
		String commute_log = date.substring(0, 10);

		try {
			sql = "update commute set start_time = '" + date + "', chk = true where number='" + number
					+ "' and commute_log = '" + commute_log + "'";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("startCommute() 에러 : " + ex);
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

	public void endCommute(String number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String start_time = null;
		String t_time;
		long sqlt_time = 0;

		String sql;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = formatter.format(new java.util.Date());
		String commute_log = date.substring(0, 10);

		try {
			conn = DBConnection.getConnection();

			sql = "select start_time, t_time from commute where number = '" + number + "' and commute_log = '"
					+ commute_log + "'";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				start_time = rs.getString("start_time");
				sqlt_time = rs.getLong("t_time");
			}
			Date first = null;
			Date second = null;

			first = formatter.parse(start_time);
			second = formatter.parse(date);

			long calDate = (Math.abs(first.getTime() - second.getTime())) + sqlt_time;

			sql = "update commute set t_time = '" + calDate + "', end_time = '" + date + "', chk = false where number='"
					+ number + "' and commute_log = '" + commute_log + "'";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("endCommute() 에러 : " + ex);
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

	}

	public boolean CommuteChk(String number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = formatter.format(new java.util.Date());
		String commute_log = date.substring(0, 10);
		boolean chk = false;

		try {
			sql = "select chk from commute where number = '" + number + "' and commute_log = '" + commute_log + "'";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				chk = rs.getBoolean("chk");
			}

			if (chk == true) {
				return chk;
			} else {
				return chk;
			}

		} catch (Exception ex) {
			System.out.println("chkCommute() 에러 : " + ex);
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
		return chk;
	}

	public ArrayList<CommuteDTO> commutelist(ArrayList<CommuteDTO> commutelist) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new java.util.Date());

		String sql;

		try {
			sql = "select * from commute where commute_log = '" + today + "'";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CommuteDTO dto = new CommuteDTO();

				dto.setNumber(rs.getString("number"));
				dto.setCommute_log(rs.getString("commute_log"));
				dto.setStart_time(rs.getString("start_time"));
				dto.setEnd_time(rs.getString("end_time"));
				dto.setT_time(rs.getLong("t_time"));
				dto.setChk(rs.getBoolean("chk"));
				commutelist.add(dto);
			}

		} catch (Exception ex) {
			System.out.println("commutelist() 에러 : " + ex);
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

		return commutelist;
	}

	public String[][] gett_list(ArrayList<MemberDTO> memberlist, ArrayList<CommuteDTO> commutelist,
			String[][] commute_true) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new java.util.Date());

		commute_true = new String[commutelist.size()][2];
		try {

			for (int i = 0; i < commutelist.size(); i++) {
				CommuteDTO commutedto = commutelist.get(i);

				for (int j = 0; j < memberlist.size(); j++) {
					MemberDTO memberdto = memberlist.get(j);
					if (memberdto.getNumber().equals(commutedto.getNumber())) {
						commute_true[i][0] = memberdto.getNumber();
						commute_true[i][1] = (commutedto.getStart_time()).substring(0,19);
					}
				}
			}

		} catch (Exception ex) {
			System.out.println("gett_list 에러 : " + ex);
		}
		return commute_true;
	}
}