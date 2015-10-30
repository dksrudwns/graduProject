package daoto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class peopleInfoDAO {
	private String url = "jdbc:mysql://14.63.217.25:3306/project-fid";
	private String id = "root";
	private String pw = "5284";

	public peopleInfoDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int insertPeopleInfor(peopleInfoDTO pDTO) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "REPLACE INTO peopleInfo values (?,?,?,?)";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pDTO.getPeopleNum());
			pstmt.setString(2, pDTO.getName());
			pstmt.setString(3, pDTO.getZipcode());
			pstmt.setString(4, pDTO.getDetailAdress());
			ri = pstmt.executeUpdate();
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
	public int insertPeople(peopleInfoDTO pDTO) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "insert INTO peopleInfo values (?,?,?,?)";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pDTO.getPeopleNum());
			pstmt.setString(2, pDTO.getName());
			pstmt.setString(3, pDTO.getZipcode());
			pstmt.setString(4, pDTO.getDetailAdress());
			ri = pstmt.executeUpdate();
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

	public peopleInfoDTO selectPeopleInfo(String num) {
		peopleInfoDTO ri=null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from peopleInfo p, AddressCode ad where p.zipcode=ad.zipcode and peopleNum =?";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String peopleNum = rs.getString("peopleNum");
				String name = rs.getString("name");
				String zipcode = rs.getString("zipcode");
				String detailAdress = rs.getString("sido")+" "+rs.getString("gugun")+" "+rs.getString("detailAdress");
				ri=new peopleInfoDTO(peopleNum, name, zipcode, detailAdress);
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
	
	public Vector<peopleInfoDTO> selectCMTPeopleInfoList() {
		peopleInfoDTO ri=null;
		Vector<peopleInfoDTO> list = new Vector<peopleInfoDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from peopleInfo where peopleNum IN (select PeopleNum from CrimeAll UNION select PeopleNum from MissingAll UNION select PeopleNum from TraceAll)";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String peopleNum = rs.getString("peopleNum");
				String name = rs.getString("name");
				String zipcode = rs.getString("zipcode");
				AddressCodeDAO aDAO = new AddressCodeDAO();
				AddressCodeDTO aDTO =aDAO.selectAddressCodeDAO(zipcode);
				String detailAdress = aDTO.getSido()+" "+aDTO.getGugun()+" "+rs.getString("detailAdress");
				ri=new peopleInfoDTO(peopleNum, name, zipcode, detailAdress);
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
	
	public Vector<peopleInfoDTO> selectPeopleInfoList() {
		peopleInfoDTO ri=null;
		Vector<peopleInfoDTO> list = new Vector<peopleInfoDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from peopleInfo";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String peopleNum = rs.getString("peopleNum");
				String name = rs.getString("name");
				String zipcode = rs.getString("zipcode");
				AddressCodeDAO aDAO = new AddressCodeDAO();
				AddressCodeDTO aDTO =aDAO.selectAddressCodeDAO(zipcode);
				String detailAdress = aDTO.getSido()+" "+aDTO.getGugun()+" "+rs.getString("detailAdress");
				ri=new peopleInfoDTO(peopleNum, name, zipcode, detailAdress);
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
	
	public int updatePeopleInfor(peopleInfoDTO pDTO) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "update peopleInfo set  zipcode = ?, detailAdress = ? where peopleNum = ? ";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, pDTO.getName());
			pstmt.setString(2, pDTO.getZipcode());
			pstmt.setString(3, pDTO.getDetailAdress());
			pstmt.setString(4, pDTO.getPeopleNum());
			
			ri = pstmt.executeUpdate();
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
	
	public int confirmPN(String mPN) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from peopleInfo where peopleNum =?";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mPN);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ri = 1;
			}
			else{
				ri=0;
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
