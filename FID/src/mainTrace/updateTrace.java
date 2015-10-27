package mainTrace;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoto.TraceDAO;
import daoto.TraceDTO;

/**
 * Servlet implementation class updateTrace
 */
@WebServlet("/updateTrace.do")
public class updateTrace extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateTrace() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pri= (String) request.getParameter("pri");
		String code = (String) request.getParameter("rank");
		String num = (String) request.getParameter("num");
		String money = (String) request.getParameter("T_money");
		String condition = (String) request.getParameter("traceCondition");
		String date = (String) request.getParameter("date");
		
		System.out.println(pri+" "+code+" "+num+" "+money+" "+condition+" "+date);
		
		TraceDTO tDTO = new TraceDTO(code, num, condition, money, date, pri);
		TraceDAO tDAO = new TraceDAO();
		tDAO.updateTrace(tDTO);
		
		response.sendRedirect("./peopleListforCMT.do");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
