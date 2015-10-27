package daoto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class searchLocalDAO {

	private String url = "jdbc:mysql://14.63.217.25:3306/project-fid";
	private String id = "root";
	private String pw = "5284";

	public searchLocalDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vector<searchLocalMDTO> selectInfoM(String sido, String gugun) {

		Vector<searchLocalMDTO> result = new Vector<searchLocalMDTO>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select distinct p.name as name,p.peopleNum as peopleNum from peopleInfo p,AddressCode ad,MissingAll m where m.PeopleNum=p.peopleNum and m.missingAddress = ad.zipcode and ad.sido = ? and ad.gugun = ?";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sido);
			pstmt.setString(2, gugun);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String peopleNum = rs.getString("peopleNum");
				String name = rs.getString("name");
				// sDTO = new searchLocalDTO(peopleNum, name);
				result.add(new searchLocalMDTO(peopleNum, name));
			}

		} catch (Exception e) {
			// TODO: handle exception
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

		return result;

	}

	public Vector<searchLocalCDTO> selectInfoC(String sido, String gugun) {

		Vector<searchLocalCDTO> result = new Vector<searchLocalCDTO>();
		searchLocalCDTO sDTO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select distinct p.name as name ,p.peopleNum as peopleNum, cr.CrimeText as CrimeText, c.origin_address as CrimeArea from peopleInfo p, AddressCode ad, CrimeAll c, crime cr where ad.zipcode = p.zipcode and cr.CrimeCode = c.CrimeCode and c.peopleNum = p.peopleNum and ad.sido= ? and ad.gugun= ?";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sido);
			pstmt.setString(2, gugun);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String peopleNum = rs.getString("peopleNum");
				String name = rs.getString("name");
				String CrimeText = rs.getString("CrimeText");
				String CrimeArea = rs.getString("CrimeArea");
				// sDTO = new searchLocalDTO(peopleNum, name);
				result.add(new searchLocalCDTO(peopleNum, name, CrimeArea, CrimeText));
			}

		} catch (Exception e) {
			// TODO: handle exception
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

		return result;

	}

	public Vector<searchLocalTDTO> selectInfoT(String sido, String gugun) {

		Vector<searchLocalTDTO> result = new Vector<searchLocalTDTO>();
		searchLocalTDTO sDTO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select distinct p.name as name ,p.peopleNum as peopleNum, tr.TraceText as Text, c.T_Money as Money  from peopleInfo p,TraceAll c, trace tr, AddressCode ad  where tr.TraceCode = c.TraceCode and c.peopleNum = p.peopleNum and p.zipcode=ad.zipcode and ad.sido= ? and ad.gugun= ?";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sido);
			pstmt.setString(2, gugun);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String peopleNum = rs.getString("peopleNum");
				String name = rs.getString("name");
				String traceText = rs.getString("Text");
				String money = rs.getString("Money");
				// sDTO = new searchLocalDTO(peopleNum, name);
				result.add(new searchLocalTDTO(traceText, money, name, peopleNum));
			}

		} catch (Exception e) {
			// TODO: handle exception
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

		return result;

	}

}
