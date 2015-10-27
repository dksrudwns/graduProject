package daoto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class TraceDAO {

	private String url = "jdbc:mysql://14.63.217.25:3306/project-fid";
	private String id = "root";
	private String pw = "5284";

	public TraceDAO() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int traceInsert(TraceDTO mDTO) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "insert into TraceAll (TraceCode, peopleNum, T_Money,TraceCondition, TraceDate) values (?,?,?,?,?)";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mDTO.getTraceCode());
			pstmt.setString(2, mDTO.getPeopleNum());
			pstmt.setString(3, mDTO.getT_Money());
			pstmt.setString(4, mDTO.getTraceCondition());
			pstmt.setString(5, mDTO.getTraceDate());
			pstmt.executeUpdate();
			ri = 0;

		} catch (Exception e) {
			e.printStackTrace();
			ri = 1;
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

	public JSONArray selectTraceInfoLimt5() {

		JSONArray jarray = new JSONArray();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " select * from TraceAll group by peopleNum limit 5";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				JSONObject body = new JSONObject();

				String peopleNum = rs.getString("peopleNum");
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
	
	public JSONObject selectTraceYearInfo(String year) {

		JSONObject yearList = new JSONObject();//1년 정보
		HashMap<String, HashMap<String, Integer>> format = new HashMap<>();// 월
																
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " select * from TraceAll where TraceDate like ?";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, year + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TraceDTO tDTO = new TraceDTO(null, null, null, null, null, null);
				tDTO.setTraceDate(rs.getString("TraceDate"));
				tDTO.setTraceCode(rs.getString("TraceCode"));
				String[] month = tDTO.getTraceDate().split("-");
				if (format.containsKey(month[1])) {// 이미 월이 있을때
					HashMap<String, Integer> subformat = format.get(month[1]);
					if (subformat.containsKey(tDTO.getTraceCode())) {// 해당 월에 현재 종류가 있음
						int count = subformat.get(tDTO.getTraceCode());// 해당 종류의  현재횟수를 받아 증가 후 업데이트
						count++;
						subformat.put(tDTO.getTraceCode(), count);
						format.put(month[1], subformat);// 변경된 val을 업데이트
					} else {// 해당 월에 종류가 없음
						subformat.put(tDTO.getTraceCode(), 1);
						;// 해당 월에 최조 종류 등록
						format.put(month[1], subformat);// 변경된 val을 업데이트
					}

				} else {// 월값이 없을때 월값에 최초값 넣음
					HashMap<String, Integer> subformat = new HashMap<>();// 종류와 횟수
					subformat.put(tDTO.getTraceCode(), 1);//
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
						
						

						if (subformat.containsKey("a")) {// 당월에 미아가 있을때
							int count = subformat.get("a");
							item.put(count);
						} else {//없을때
							item.put(0);
						}
						if (subformat.containsKey("b")) {// 당월에 청소년 가출 있을때
							int count = subformat.get("b");
							item.put(count);
						} else {
							item.put(0);
						}
						if (subformat.containsKey("c")) {// 당월에 실종 있을때
							int count = subformat.get("c");
							item.put(count);
						} else {
							item.put(0);
						}
				
						
					}else {
						item.put(0);item.put(0);item.put(0);
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

	public Vector<TraceDTO> selectTraceInfo(String num) {
		Vector<TraceDTO> result = new Vector<TraceDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select TraceText,peopleNum, T_Money, TraceCondition, TraceDate , Pri from TraceAll left join trace on TraceAll.TraceCode  = trace.TraceCode where TraceAll.peopleNum = ?";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String traceCode = rs.getString("TraceText");
				String peopleNum = rs.getString("peopleNum");
				String t_Money = rs.getString("T_Money");
				String traceCondition = rs.getString("TraceCondition");
				String traceDate = rs.getString("TraceDate");
				String pri = rs.getString("Pri");
				// sDTO = new searchLocalDTO(peopleNum, name);
				result.add(new TraceDTO(traceCode, peopleNum, traceCondition, t_Money, traceDate, pri));
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

	public int traceCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "select count(TraceCode) as count from TraceAll";
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

	public HashMap<String, Integer> traceDetailCount() {
		int countA = 0;
		int countB = 0;
		int countC = 0;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "select count(if(TraceCode='a',TraceCode,null))A,count(if(TraceCode='b',TraceCode,null))B,count(if(TraceCode= 'c',TraceCode,null))c from TraceAll";
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				countA = rs.getInt("A");
				countB = rs.getInt("B");
				countC = rs.getInt("C");
				map.put("countA", countA);
				map.put("countB", countB);
				map.put("countC", countC);
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

	public int traceDelete(TraceDTO tDTO) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "delete from TraceAll where Pri = ?";

		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, tDTO.getPri());
			pstmt.executeUpdate();
			ri = 0;

		} catch (Exception e) {
			e.printStackTrace();
			ri = 1;
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

	public int updateTrace(TraceDTO tDTO) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "update TraceAll set TraceCode = ?, peopleNum = ?, T_Money = ?, TraceCondition = ?, TraceDate = ?   where Pri = ?";
		try {
			conn = DriverManager.getConnection(this.url, this.id, this.pw);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, tDTO.getTraceCode());
			pstmt.setString(2, tDTO.getPeopleNum());
			pstmt.setString(3, tDTO.getT_Money());
			pstmt.setString(4, tDTO.getTraceCondition());
			pstmt.setString(5, tDTO.getTraceDate());
			pstmt.setString(6, tDTO.getPri());
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
