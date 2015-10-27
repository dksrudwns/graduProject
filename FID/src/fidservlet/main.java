package fidservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import daoto.CrimeDAO;
import daoto.MissingDAO;
import daoto.TraceDAO;

/**
 * Servlet implementation class main
 */
@WebServlet("/main.do")
public class main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public main() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 내용 구분
		JSONObject title = new JSONObject();
		
		//범죄자 리스트
		CrimeDAO cDAO = new CrimeDAO();
		JSONArray crime = cDAO.selectCrimeInfoLimt5();
		
		//실종자리스트
		MissingDAO mDAO = new MissingDAO();
		JSONArray missing = mDAO.selectMissingInfoLimt5();
		
		//수배자리스
		TraceDAO tDAO = new TraceDAO();
		JSONArray trace = tDAO.selectTraceInfoLimt5();

		PrintWriter out = null;
		try {
			title.put("Crime", crime);
			title.put("Missing", missing);
			title.put("Trace", trace);
			System.out.println(title);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			response.setContentType("application/x-json; charset=UTF-8");

			out = response.getWriter();
			out.print(title);
			out.flush();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
