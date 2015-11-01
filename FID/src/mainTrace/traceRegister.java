package mainTrace;

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
import daoto.CrimeDAO;
import daoto.TraceDAO;
import daoto.TraceDTO;

/**
 * Servlet implementation class traceRegister
 */
@WebServlet("/traceRegister.do")
public class traceRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public traceRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String peopleNum = request.getParameter("idNum1") + request.getParameter("idNum2");
		String traceCode = request.getParameter("rank");
		String traceDate = request.getParameter("date");
		String T_Money = request.getParameter("T_money");
		String traceCondition = "Y";

		if (peopleNum == "" || peopleNum == null) {
			response.setCharacterEncoding("EUC-KR");
			PrintWriter writer = response.getWriter();
			writer.println("<script type='text/javascript'>");
			writer.println("alert('정보를 다시 확인하세요.');");
			writer.println("history.go(-1);");
			writer.println("</script>");
			writer.flush();
			return;
		}
	

	
		TraceDAO tDAO = new TraceDAO();
		TraceDTO tDTO = new TraceDTO(traceCode, peopleNum, traceCondition, T_Money, traceDate,null);
		

		try {

			tDAO.traceInsert(tDTO);
			response.sendRedirect("main.jsp");

		} catch (Exception e) {
			response.setCharacterEncoding("EUC-KR");
			PrintWriter writer = response.getWriter();
			writer.println("<script type='text/javascript'>");
			writer.println("alert('정보를 다시 확인하세요.');");
			writer.println("history.go(-1);");
			writer.println("</script>");
			writer.flush();
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
