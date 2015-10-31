
package daoto;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class MemberDAO {
	private String url = "jdbc:mysql://14.63.217.25:3306/project-fid";
	private String id = "root";
	private String pw = "5284";

	public MemberDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int memberInsert(MemberDTO dto) {
		int ri = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "insert into member values (?,?,?,?)";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getLv());
			pstmt.setString(4, dto.getPn());
			pstmt.executeUpdate();
			ri = 0;
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return ri;
	}

	public MemberDTO memberSelect(String input_id) {
		MemberDTO mdto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "Select * from member where id = ?";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, input_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("ID");
				String pw = rs.getString("Password");
				String lv = rs.getString("Level");
				String pn = rs.getString("PeopleNum");

				mdto = new MemberDTO(id, pw, lv, pn);
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
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return mdto;
	}

	public int confirmId(String mid) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select id from member where id =?";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ri = 1;
			} else {
				ri = 0;
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
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return ri;
	}

	public int confirmPN(String mPN) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from member where peopleNum =?";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mPN);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ri = 0;
			} else {
				ri = -1;
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
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return ri;
	}

	public int deleteMember(String mId) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "delete from member where id =?";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mId);
			pstmt.executeUpdate();
			ri = 0;
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return ri;
	}

	public String userCheck(String mid) {
		String mpw = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select password from member where id = ?";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mpw = rs.getString("password");
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
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return mpw;
	}

	public String getlv(String mid) {
		String mlv = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select level from member where id = ?";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mlv = rs.getString("level");
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
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return mlv;
	}

	public int updateMember(String mId, String lv, String pw) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "update member set  level = ?,password=? where id =?";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, lv);
			pstmt.setString(2, pw);
			pstmt.setString(3, mId);
			pstmt.executeUpdate();
			ri = 0;
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return ri;
	}

	public Vector<MemberDTO> selectMemberInfoList() {
		MemberDTO ri = null;
		Vector<MemberDTO> list = new Vector<MemberDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from member";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String mid = rs.getString("ID");
				String mpw = rs.getString("Password");
				String lv = rs.getString("Level");
				String pn = rs.getString("PeopleNum");
				ri = new MemberDTO(mid, mpw, lv, pn);
				list.add(ri);
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
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return list;
	}

	public boolean checkId(String mid) {
		boolean ri = false;
		String pw;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select id from member where id = ?";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pw = rs.getString("id");
				if (pw.equals(mid)) {
					ri = true;
					System.out.println("db결과 있을떄" + ri);
				} else {
					ri = false;
					System.out.println("db결과 없을때" + ri);
				}
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
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return ri;
	}

}