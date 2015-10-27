package mainTrace;

import java.io.IOException;
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

/**
 * Servlet implementation class deleteTrace
 */
@WebServlet("/deleteTrace.do")
public class deleteTrace extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteTrace() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String pri = (String) request.getParameter("pri");
	
		
		
		TraceDTO tDTO = new TraceDTO(null, null, null, null, null,pri);
		TraceDAO tDAO = new TraceDAO();
		tDAO.traceDelete(tDTO);
		
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
