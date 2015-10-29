package daoto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.json.JsonArray;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class CrimeDAO {

	private String url = "jdbc:mysql://14.63.217.25:3306/project-fid";
	private String id = "root";
	private String pw = "5284";

	public CrimeDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int crimeInsert(CrimeDTO dto) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "insert into CrimeAll (CrimeCode, PeopleNum, origin_address, CrimeDate) values (?,?,?,?)";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getCrimeCode());
			pstmt.setString(2, dto.getPeopleNum());
			pstmt.setString(3, dto.getCrimeArea());
			pstmt.setString(4, dto.getCrimeDate());
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
	public JSONArray selectCountArea(String sido) {

		JSONArray jarray = new JSONArray();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select c.CrimeCode, Count(c.CrimeCode) as Count from CrimeAll c, AddressCode a where c.origin_address = a.zipcode and a.sido = ? group by c.CrimeCode";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,sido);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				String typeCount = rs.getString("Count");
				jarray.put(typeCount);
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

		return jarray;
	}

	public JSONArray selectCrimeInfoLimt5() {

		JSONArray jarray = new JSONArray();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from CrimeAll group by peopleNum limit 5";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				JSONObject body = new JSONObject();

				String peopleNum = rs.getString("PeopleNum");
				body.put("num", peopleNum);
				// sDTO = new searchLocalDTO(peopleNum, name);
				peopleInfoDAO peDAO = new peopleInfoDAO();
				peopleInfoDTO peDTO = peDAO.selectPeopleInfo(peopleNum);

				String name = peDTO.getName();
				body.put("name", name);

				AddressCodeDAO aDAO = new AddressCodeDAO();
				AddressCodeDTO aDTO = aDAO.selectAddressCodeDAO(peDTO.getZipcode());
				body.put("adr", peDTO.getDetailAdress());

				jarray.put(body);
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

		return jarray;
	}
	

	public JSONObject selectCrimeYearInfo(String year) {

		JSONObject yearList = new JSONObject();//1년 정보
		HashMap<String, HashMap<String, Integer>> format = new HashMap<>();// 월
																
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " select * from CrimeAll where CrimeDate like ?";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, year + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CrimeDTO cDTO = new CrimeDTO(null, null, null, null, null);
				cDTO.setCrimeDate(rs.getString("CrimeDate"));
				cDTO.setCrimeCode(rs.getString("CrimeCode"));
				String[] month = cDTO.getCrimeDate().split("-");
				if (format.containsKey(month[1])) {// 이미 월이 있을때
					HashMap<String, Integer> subformat = format.get(month[1]);
					if (subformat.containsKey(cDTO.getCrimeCode())) {// 해당 월에 현재 종류가 있음
						int count = subformat.get(cDTO.getCrimeCode());// 해당 종류의  현재횟수를 받아 증가 후 업데이트
						count++;
						subformat.put(cDTO.getCrimeCode(), count);
						format.put(month[1], subformat);// 변경된 val을 업데이트
					} else {// 해당 월에 종류가 없음
						subformat.put(cDTO.getCrimeCode(), 1);
						;// 해당 월에 최조 종류 등록
						format.put(month[1], subformat);// 변경된 val을 업데이트
					}

				} else {// 월값이 없을때 월값에 최초값 넣음
					HashMap<String, Integer> subformat = new HashMap<>();// 종류와 횟수
					subformat.put(cDTO.getCrimeCode(), 1);//
					format.put(month[1], subformat);
				}
				
				// json
				String monthKey = null;
				for (int i = 1; i <= 12; i++) {

					if (i < 10) {
						monthKey = "0" + i;
					} else {
						monthKey = i+"";
					}
					String nMonth = "month"+i;
					JSONArray item = new JSONArray();// 당월 카운트값
					if (format.containsKey(monthKey)) {// 있을때
						HashMap<String, Integer> subformat = format.get(monthKey);// 당월 범죄 종류 카운트값 get
						
						

						if (subformat.containsKey("1")) {// 당월에 살인 있을때
							int count = subformat.get("1");
							item.put(count);
						} else {//없을때
							item.put(0);
						}
						if (subformat.containsKey("2")) {// 당월에 절도 있을때
							int count = subformat.get("2");
							item.put(count);
						} else {
							item.put(0);
						}
						if (subformat.containsKey("3")) {// 당월에 폭행 있을때
							int count = subformat.get("3");
							item.put(count);
						} else {
							item.put(0);
						}
						if (subformat.containsKey("4")) {// 당월에 성폭행 있을때
							int count = subformat.get("4");
							item.put(count);
						} else {
							item.put(0);
						}
				
						
					}else {
						item.put(0);item.put(0);item.put(0);item.put(0);
					}
					yearList.put(nMonth, item);
				}
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
		return yearList;
	}

	public Vector<CrimeDTO> selectCrimeInfo(String num) {
		Vector<CrimeDTO> result = new Vector<CrimeDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from CrimeAll  where PeopleNum = ?";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String crimeCode = rs.getString("CrimeCode");
				String peopleNum = rs.getString("PeopleNum");
				String crimeArea = rs.getString("origin_address");
				String CrimeDate = rs.getString("CrimeDate");
				String CrimePri = rs.getString("Pri");
				// sDTO = new searchLocalDTO(peopleNum, name);
				result.add(new CrimeDTO(crimeCode, peopleNum, crimeArea, CrimeDate, CrimePri));
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

	public int crimeCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "select count(CrimeCode) as count from CrimeAll";
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {
			// TODO: handle exception
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

		return count;
	}

	public HashMap<String, Integer> crimeDetailCount() {
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "select count(if(CrimeCode='1',CrimeCode,null))'1',count(if(CrimeCode='2',CrimeCode,null))'2',count(if(CrimeCode= '3',CrimeCode,null))'3',count(if(CrimeCode= '4',CrimeCode,null))'4' from CrimeAll";
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count1 = rs.getInt("1");
				count2 = rs.getInt("2");
				count3 = rs.getInt("3");
				count4 = rs.getInt("4");
				map.put("count1", count1);
				map.put("count2", count2);
				map.put("count3", count3);
				map.put("count4", count4);
			}
		} catch (Exception e) {
			// TODO: handle exception
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

		return map;
	}

	public HashMap<String, Integer> crimeDetailCountLocal(String sido, String gugun) {
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "select count(if(CrimeCode='1',CrimeCode,null))'1',count(if(CrimeCode='2',CrimeCode,null))'2',count(if(CrimeCode= '3',CrimeCode,null))'3',count(if(CrimeCode= '4',CrimeCode,null))'4' from CrimeAll c,AddressCode a where c.origin_address = a.zipcode and sido = ? and gugun = ?";
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sido);
			pstmt.setString(2, gugun);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count1 = rs.getInt("1");
				count2 = rs.getInt("2");
				count3 = rs.getInt("3");
				count4 = rs.getInt("4");
				map.put("count1", count1);
				map.put("count2", count2);
				map.put("count3", count3);
				map.put("count4", count4);
			}
		} catch (Exception e) {
			// TODO: handle exception
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

		return map;
	}

	public int crimedelete(CrimeDTO dto) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "delete from CrimeAll where Pri = ? ";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getPri());
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

	public int crimeUpdate(CrimeDTO dto) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "update CrimeAll set CrimeCode=?, origin_address=?, CrimeDate= ?  where Pri = ?";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getCrimeCode());
			pstmt.setString(2, dto.getCrimeArea());
			pstmt.setString(3, dto.getCrimeDate());
			pstmt.setString(4, dto.getPri());
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
