package mainMissing;

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

/**
 * Servlet implementation class deleteMissing
 */
@WebServlet("/deleteMissing.do")
public class deleteMissing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteMissing() {
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
		
		MissingDTO mDTO = new MissingDTO(null, null, null, null, null, null, pri);
		MissingDAO mDAO = new MissingDAO();
		mDAO.missingDelete(mDTO);
		
		// 나중에 missingAll에서 전부 없어지면 지우자
		//missingpeDTO mpDTO = new MissingpeDTO(num, null, null);
		//missingpeDAO mpDAO = new MissingpeDAO();
		//mpDAO.missingpeDelete(mpDTO);
		
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
