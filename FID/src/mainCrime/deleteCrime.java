package mainCrime;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoto.CrimeDAO;
import daoto.CrimeDTO;

/**
 * Servlet implementation class deleteCrime
 */
@WebServlet("/deleteCrime.do")
public class deleteCrime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteCrime() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String num = (String) request.getParameter("num");
		String date = (String) request.getParameter("date");
		String code = (String) request.getParameter("rank");
		String pri = (String) request.getParameter("pri");
		
		CrimeDTO cDTO = new CrimeDTO(code, num, null, date,pri);
		CrimeDAO cDAO = new CrimeDAO();
		cDAO.crimedelete(cDTO);
		
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
