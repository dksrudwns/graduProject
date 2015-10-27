package upDelforCMT;

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
import daoto.TraceDAO;
import daoto.TraceDTO;
import daoto.peopleInfoDAO;
import daoto.peopleInfoDTO;

/**
 * Servlet implementation class detailInforServlet
 */
@WebServlet("/detailInforServletForCMT.do")
public class detailInforServletForCMT extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public detailInforServletForCMT() {
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
		System.out.println(peopleNum);

		peopleInfoDAO pDAO = new peopleInfoDAO();
		peopleInfoDTO pDTO = pDAO.selectPeopleInfo(peopleNum);
		
		// 실종
		MissingDAO mDAO = new MissingDAO();
		Vector<MissingDTO> vMDTO  = mDAO.selectMissingInfo(peopleNum);
		System.out.println(vMDTO.size());
		if (vMDTO.size() != 0) {
			MissingpeDAO mepDAO = new MissingpeDAO();
			MissingpeDTO mpeDTO = mepDAO.selectMissingpeInfo(peopleNum);
			peopleInfoDAO peoDAO = new peopleInfoDAO();
			peopleInfoDTO peoDTO = peoDAO.selectPeopleInfo(mpeDTO.getProtecterCode());
			request.setAttribute("MissingArea", vMDTO);
			request.setAttribute("proPhon", mpeDTO.getProtecterPh());
			request.setAttribute("proName", peoDTO.getName());
			
		}

		// 범죄
		Vector<CrimeDTO> vCDTO = new Vector<>();
		CrimeDAO cDAO = new CrimeDAO();
		vCDTO = cDAO.selectCrimeInfo(peopleNum);
		if (vCDTO.size() != 0) {
			request.setAttribute("CrimeArea", vCDTO);
		}

		// 수배
		Vector<TraceDTO> vTDTO = new Vector<>();
		TraceDAO tDAO = new TraceDAO();
		vTDTO = tDAO.selectTraceInfo(peopleNum);
		if (vTDTO .size() != 0) {
			request.setAttribute("TraceArea", vTDTO);
		}
		

		request.setAttribute("peopleNum", pDTO.getPeopleNum());
		request.setAttribute("name", pDTO.getName());
		request.setAttribute("zipcode", pDTO.getZipcode());
		request.setAttribute("address", pDTO.getDetailAdress());

		System.out.println("test3");
		RequestDispatcher dispatcher = request.getRequestDispatcher("main/etc/detailInfoCMT.jsp");
		dispatcher.forward(request, response);
		System.out.println("test4");
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
