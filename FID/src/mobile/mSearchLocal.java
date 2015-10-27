package mobile;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoto.searchLocalCDTO;
import daoto.searchLocalDAO;
import daoto.searchLocalMDTO;
import daoto.searchLocalTDTO;

/**
 * Servlet implementation class searchLocal
 */
@WebServlet("/searchLocal.do")
public class mSearchLocal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public mSearchLocal() {
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

		String[] local = result.split(" ");

		String sido = null;
		String gugun = null;

		//시 구 구분
		if (local[1].endsWith("시")) {
			
			sido = local[1].substring(0, 2);
			gugun = local[2];

		} else {
			sido = local[1].substring(0, 2);
			gugun = local[2]+" "+local[3];

		}
		
		searchLocalDAO sDAO = new searchLocalDAO();
		
		Vector<searchLocalMDTO> missigRs = sDAO.selectInfoM(sido, gugun);
		Vector<searchLocalCDTO> CrimeRs = sDAO.selectInfoC(sido, gugun);
		Vector<searchLocalTDTO> TraceRs = sDAO.selectInfoT(sido, gugun);
		
		request.setAttribute("sido", sido);
		request.setAttribute("gugun", gugun);
		request.setAttribute("missigRs", missigRs);
		request.setAttribute("CrimeRs", CrimeRs);
		request.setAttribute("TraceRs", TraceRs);

		RequestDispatcher dispatcher = request.getRequestDispatcher("mobile/mSearchLocal.jsp");
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
