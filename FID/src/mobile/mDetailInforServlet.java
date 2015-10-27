package mobile;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoto.CrimeDAO;
import daoto.CrimeDTO;
import daoto.MissingDAO;
import daoto.MissingDTO;
import daoto.MissingpeDAO;
import daoto.MissingpeDTO;
import daoto.PeopleInDAO;
import daoto.PeopleInDTO;
import daoto.TraceDAO;
import daoto.TraceDTO;
import daoto.peopleInfoDAO;
import daoto.peopleInfoDTO;

/**
 * Servlet implementation class detailInforServlet
 */
@WebServlet("/mDetailInforServlet.do")
public class mDetailInforServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public mDetailInforServlet() {
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

		String peopleNum = request.getParameter("num");

		peopleInfoDAO pDAO = new peopleInfoDAO();
		peopleInfoDTO pDTO = pDAO.selectPeopleInfo(peopleNum);

		// 실종
		Vector<MissingDTO> vMDTO = new Vector<>();
		MissingDAO mDAO = new MissingDAO();
		vMDTO = mDAO.selectMissingInfo(peopleNum);
		if (vMDTO != null) {

			request.setAttribute("MissingArea", vMDTO);

		}

		// 범죄
		Vector<CrimeDTO> vCDTO = new Vector<>();
		CrimeDAO cDAO = new CrimeDAO();
		vCDTO = cDAO.selectCrimeInfo(peopleNum);
		if (vCDTO != null) {
			request.setAttribute("CrimeArea", vCDTO);
		}

		// 수배
		Vector<TraceDTO> vTDTO = new Vector<>();
		TraceDAO tDAO = new TraceDAO();
		vTDTO = tDAO.selectTraceInfo(peopleNum);
		if (vTDTO != null) {
			request.setAttribute("TraceArea", vTDTO);
		}

		request.setAttribute("peopleNum", pDTO.getPeopleNum());
		request.setAttribute("name", pDTO.getName());
		request.setAttribute("zipcode", pDTO.getZipcode());
		request.setAttribute("address", pDTO.getDetailAdress());

		System.out.println("test3");
		RequestDispatcher dispatcher = request.getRequestDispatcher("mobile/mDetailInfo.jsp");
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
