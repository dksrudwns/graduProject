package daoto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class MissingpeDAO {
	
	private String url = "jdbc:mysql://14.63.217.25:3306/project-fid";
	private String id = "root";
	private String pw = "5284";
	public MissingpeDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int missingpeInsert(MissingpeDTO mDTO){
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "REPLACE into missingpeople (MissingPeopleCode, ProtecterCode, ProtecterPh) values (?,?,?)";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mDTO.getMissingPeopleCode());
			pstmt.setString(2, mDTO.getProtecterCode());
			pstmt.setString(3, mDTO.getProtecterPh());
			pstmt.executeUpdate();
			ri = 0;
			
		} catch (Exception e) {
			e.printStackTrace();
			ri=1;
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
	
	public MissingpeDTO selectMissingpeInfo(String num) {
		MissingpeDTO mpDTO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from missingpeople where missingPeopleCode =?";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String missingPeopleCode = rs.getString("missingPeopleCode");
				String protecterCode = rs.getString("protecterCode");
				String protecterPh = rs.getString("protecterPh");
				// sDTO = new searchLocalDTO(peopleNum, name);
				mpDTO = new MissingpeDTO(missingPeopleCode, protecterCode, protecterPh);
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

		return mpDTO;
	}
	
	public int missingpeDelete(MissingpeDTO mDTO){
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "delete from missingpeople where MissingPeopleCode = ?";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mDTO.getMissingPeopleCode());
			pstmt.executeUpdate();
			ri = 0;
			
		} catch (Exception e) {
			e.printStackTrace();
			ri=1;
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

}
