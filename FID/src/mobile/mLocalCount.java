package mobile;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoto.CrimeDAO;
import daoto.MissingDAO;
import daoto.TraceDAO;

/**
 * Servlet implementation class mLocalCount
 */
@WebServlet("/mLocalCount.do")
public class mLocalCount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public mLocalCount() {
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
		request.setCharacterEncoding("UTF-8");

		String result = request.getParameter("Local");
		System.out.println(result);
		String[] local = result.split(" ");

		String sido = null;
		String gugun = null;

		// 시 구 구분
		if (local[1].endsWith("시")) {

			sido = local[1].substring(0, 2);
			gugun = local[2];

		} else {
			sido = local[1].substring(0, 2);
			gugun = local[2] + " " + local[3];

		}
		CrimeDAO cDAO = new CrimeDAO();
		HashMap<String, Integer> cmap = cDAO.crimeDetailCountLocal(sido, gugun);
		int crime1 = cmap.get("count1");// 살인
		int crime2 = cmap.get("count2");// 절도
		int crime3 = cmap.get("count3");// 폭행
		int crime4 = cmap.get("count4");// 성폭행
		System.out.println(crime1+crime2+crime3+crime4);
		
		MissingDAO mDAO = new MissingDAO();
		HashMap<String, Integer> mmap = mDAO.missingDetailCountLocal(sido, gugun);
		int missing1 = mmap.get("count1");// 미아
		int missing2 = mmap.get("count2");// 청소년가출
		int missing3 = mmap.get("count3");// 실종
		int missing4 = mmap.get("count4");// 노인실종
		
		TraceDAO tDAO = new TraceDAO();
		HashMap<String, Integer> tmap = tDAO.traceDetailCount();
		int traceA = tmap.get("countA");// 지명수배
		int traceB = tmap.get("countB");// 벌금수배
		int traceC = tmap.get("countC");// 지명통보

		

		request.setAttribute("crime1", crime1);
		request.setAttribute("crime2", crime2);
		request.setAttribute("crime3", crime3);
		request.setAttribute("crime4", crime4);
		request.setAttribute("traceA", traceA);
		request.setAttribute("traceB", traceB);
		request.setAttribute("traceC", traceC);
		request.setAttribute("missing1", missing1);
		request.setAttribute("missing2", missing2);
		request.setAttribute("missing3", missing3);
		request.setAttribute("missing4", missing4);

		RequestDispatcher dispatcher = request.getRequestDispatcher("chart/mobilelocal.jsp");
		dispatcher.forward(request, response);
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
