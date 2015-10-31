package mainCrime;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoto.AddressCodeDAO;
import daoto.AddressCodeDTO;
import daoto.CrimeDTO;
import daoto.peopleInfoDAO;
import daoto.CrimeDAO;

/**
 * Servlet implementation class crimRegiser
 */
@WebServlet("/crimRegiser.do")
public class crimRegiser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public crimRegiser() {
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

		request.setCharacterEncoding("utf-8");

		String peopleNum = request.getParameter("idNum1") + request.getParameter("idNum2");
		String crimeCode = request.getParameter("rank");
		String crimeDate = request.getParameter("date");
		String crimeArea = request.getParameter("zip_code");
		String sido = request.getParameter("sido");
		String sigungu = request.getParameter("sigungu");
		String detail = request.getParameter("detail_1");

		peopleInfoDAO pDAO = new peopleInfoDAO();
		int checkp = pDAO.confirmPN(peopleNum);
		if (checkp == 1) {

			try {
				AddressCodeDAO aDAO = new AddressCodeDAO();
				AddressCodeDTO aDTO = new AddressCodeDTO(crimeArea, sido, sigungu, detail);

				CrimeDTO cDTO = new CrimeDTO(crimeCode, peopleNum, crimeArea, crimeDate, null);
				CrimeDAO cDAO = new CrimeDAO();

				cDAO.crimeInsert(cDTO);
				aDAO.insertAddressCode(aDTO);
				response.sendRedirect("main.jsp");

			} catch (Exception e) {
				response.setCharacterEncoding("EUC-KR");
				PrintWriter writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("history.go(-1);");
				writer.println("</script>");
				writer.flush();
				return;
			}
		} else {
			response.setCharacterEncoding("EUC-KR");
			PrintWriter writer = response.getWriter();
			writer.println("<script type='text/javascript'>");
			writer.println("history.go(-1);");
			writer.println("</script>");
			writer.flush();
			return;
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
