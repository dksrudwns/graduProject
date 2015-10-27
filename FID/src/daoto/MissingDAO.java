package daoto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class MissingDAO {
	
	private String url = "jdbc:mysql://14.63.217.25:3306/project-fid";
	private String id = "root";
	private String pw = "5284";
	
	public MissingDAO(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int missingInsert(MissingDTO mDTO){
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "REPLACE into MissingAll (MissingCode, peopleNum, missingDate,findDate, missingAddress,findAddress) values (?,?,?,?,?,?) ";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mDTO.getMissingCode());
			pstmt.setString(2, mDTO.getPeopleNum());
			pstmt.setString(3, mDTO.getMissingDate());
			pstmt.setString(4, mDTO.getFindDate());
			pstmt.setString(5, mDTO.getMissingAddress());
			pstmt.setString(6, mDTO.getFindAddress());
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

	public Vector<MissingDTO> selectMissingInfo(String num) {
		Vector<MissingDTO> result = new Vector<MissingDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select MissingText, peopleNum, MissingDate, missingAddress , findDate, findAddress, Pri from MissingAll left join missing on MissingAll.MissingCode = missing.MissingCode where peopleNum =?";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String missingCode = rs.getString("MissingText");
				String peopleNum = rs.getString("peopleNum");
				String missingDate = rs.getString("MissingDate");
				String missingAddress = rs.getString("missingAddress");
				String findDate = rs.getString("findDate");
				String findAddress = rs.getString("findAddress");
				String pri = rs.getString("Pri");
				// sDTO = new searchLocalDTO(peopleNum, name);
				result.add(new MissingDTO(missingCode, peopleNum, missingDate, findDate, missingAddress, findAddress, pri));
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
	
	
public JSONArray selectMissingInfoLimt5() {
		
		JSONArray jarray = new JSONArray();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " select * from MissingAll group by peopleNum limit 5";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				JSONObject body = new JSONObject();
				
				String peopleNum = rs.getString("peopleNum");
				String mzipcode = rs.getString("missingAddress");
				body.put("num", peopleNum);
				// sDTO = new searchLocalDTO(peopleNum, name);
				peopleInfoDAO peDAO = new peopleInfoDAO();
				peopleInfoDTO peDTO = peDAO.selectPeopleInfo(peopleNum);
				
				String name = peDTO.getName();
				body.put("name", name);
				
				AddressCodeDAO aDAO = new AddressCodeDAO();
				AddressCodeDTO aDTO = aDAO.selectAddressCodeDAO(mzipcode);
				
				String adr = aDTO.getSido()+" "+aDTO.getGugun();
				body.put("adr", adr);
				
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


public JSONObject selectMissingYearInfo(String year) {

	JSONObject yearList = new JSONObject();//1년 정보
	HashMap<String, HashMap<String, Integer>> format = new HashMap<>();// 월
															
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String query = " select * from MissingAll where missingDate like ?";

	try {
		conn = DriverManager.getConnection(this.url, this.id, this.pw);
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, year + "%");
		rs = pstmt.executeQuery();
		while (rs.next()) {
			MissingDTO mDTO = new MissingDTO(null, null, null, null, null, null, null);
			mDTO.setMissingCode(rs.getString("MissingCode"));
			mDTO.setMissingDate(rs.getString("missingDate"));
			String[] month = mDTO.getMissingDate().split("-");
			if (format.containsKey(month[1])) {// 이미 월이 있을때
				HashMap<String, Integer> subformat = format.get(month[1]);
				if (subformat.containsKey(mDTO.getMissingCode())) {// 해당 월에 현재 종류가 있음
					int count = subformat.get(mDTO.getMissingCode());// 해당 종류의  현재횟수를 받아 증가 후 업데이트
					count++;
					subformat.put(mDTO.getMissingCode(), count);
					format.put(month[1], subformat);// 변경된 val을 업데이트
				} else {// 해당 월에 종류가 없음
					subformat.put(mDTO.getMissingCode(), 1);
					;// 해당 월에 최조 종류 등록
					format.put(month[1], subformat);// 변경된 val을 업데이트
				}

			} else {// 월값이 없을때 월값에 최초값 넣음
				HashMap<String, Integer> subformat = new HashMap<>();// 종류와 횟수
				subformat.put(mDTO.getMissingCode(), 1);//
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
					
					

					if (subformat.containsKey("1")) {// 당월에 미아가 있을때
						int count = subformat.get("1");
						item.put(count);
					} else {//없을때
						item.put(0);
					}
					if (subformat.containsKey("2")) {// 당월에 청소년 가출 있을때
						int count = subformat.get("2");
						item.put(count);
					} else {
						item.put(0);
					}
					if (subformat.containsKey("3")) {// 당월에 실종 있을때
						int count = subformat.get("3");
						item.put(count);
					} else {
						item.put(0);
					}
					if (subformat.containsKey("4")) {// 당월에 노인 실종  있을때
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
	
	
	public int MissingCount(){
		int count= 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "select count(MissingCode) as count from MissingAll";
		ResultSet rs =null;
		try {
			conn = DriverManager.getConnection(this.url,this.id,this.pw);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt("count");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
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
	
	public HashMap<String , Integer> missingDetailCount() {
		int count1= 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		HashMap<String , Integer> map = new HashMap<String , Integer>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "select count(if(MissingCode='1',MissingCode,null))'1',count(if(MissingCode='2',MissingCode,null))'2',count(if(MissingCode= '3',MissingCode,null))'3',count(if(MissingCode= '4',MissingCode,null))'4' from MissingAll";
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
	
	public HashMap<String , Integer> missingDetailCountLocal(String sido, String gugun){
		int count1= 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		HashMap<String , Integer> map = new HashMap<String , Integer>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "select count(if(MissingCode='1',MissingCode,null))'1',count(if(MissingCode='2',MissingCode,null))'2',count(if(MissingCode= '3',MissingCode,null))'3',count(if(MissingCode= '4',MissingCode,null))'4' from MissingAll c,AddressCode a where c.missingAddress = a.zipcode and sido = ? and gugun = ? ";
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
	
	public int missingDelete(MissingDTO mDTO){
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "delete from MissingAll where Pri = ?";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mDTO.getPri());
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
	
	public int updateMissing(MissingDTO mDTO){
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "update MissingAll set MissingCode = ?, peopleNum = ?, missingDate = ?, findDate = ?, missingAddress = ?, findAddress = ?   where Pri = ?";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mDTO.getMissingCode());
			pstmt.setString(2, mDTO.getPeopleNum());
			pstmt.setString(3, mDTO.getMissingDate());
			pstmt.setString(4, mDTO.getFindDate());
			pstmt.setString(5, mDTO.getMissingAddress());
			pstmt.setString(6, mDTO.getFindAddress());
			pstmt.setString(7, mDTO.getPri());
			pstmt.executeUpdate();
			result = 0;

		} catch (Exception e) {
			e.printStackTrace();
			result = 1;
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

		return result;
	}
	
}
