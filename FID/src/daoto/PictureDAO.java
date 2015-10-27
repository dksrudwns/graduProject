
package daoto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.codehaus.jettison.json.JSONArray;

public class PictureDAO {
	private String url = "jdbc:mysql://14.63.217.25:3306/project-fid";
	private String id = "root";
	private String pw = "5284";

	public PictureDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSONArray PictureIdSelect() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "Select FaceID from picture";
		JSONArray Facelist = new JSONArray();
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String FaceID = rs.getString("FaceID");
				Facelist.put(FaceID);
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

		return Facelist;
	}

    public int insertPicture(PictureDTO dto){
    	int ri = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "insert into picture values (?,?)";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getURL());
			pstmt.setString(2, dto.getFacID());
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
    public PictureDTO selectPicture(String tURL){
    	
    	PictureDTO ri=null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from picture where URL =?";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, tURL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String URL = rs.getString("URL");
				String FaceId = rs.getString("FaceID");
				ri=new PictureDTO(URL,FaceId);
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
    public int updatePicture(PictureDTO dto){
    	int ri = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "update picture set FaceID =? where URL =?";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getFacID());
			pstmt.setString(2, dto.getURL());
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
}
