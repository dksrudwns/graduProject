package mainMissing;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoto.MissingpeDAO;
import daoto.MissingpeDTO;
import daoto.TraceDAO;
import daoto.TraceDTO;
import daoto.peopleInfoDAO;

/**
 * Servlet implementation class updatePro
 */
@WebServlet("/updatePro.do")
public class updatePro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatePro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String phon= (String) request.getParameter("proPhon");
		String pro = (String) request.getParameter("proNum");
		String num = (String) request.getParameter("num");
		System.out.println(phon+pro+num);
		MissingpeDTO peDTO = new MissingpeDTO(num, pro, phon);
		MissingpeDAO peDAO = new MissingpeDAO();
		peDAO.missingpeInsert(peDTO);
		
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
