package fidservlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import daoto.PeopleInDAO;
import daoto.PeopleInDTO;
import daoto.peopleInfoDAO;
import daoto.peopleInfoDTO;

/**
 * Servlet implementation class checkPeopleIn
 */
@WebServlet("/checkPeopleIn.do")
public class checkPeopleIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public checkPeopleIn() {
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
	
		String peopleNum = request.getParameter("num");
		System.out.println(peopleNum);
		peopleInfoDAO peDAO = new peopleInfoDAO();
		peopleInfoDTO peDTO = peDAO.selectPeopleInfo(peopleNum);

		if (peDTO != null) {

			request.setAttribute("name", peDTO.getName());
			request.setAttribute("deAd", peDTO.getDetailAdress());
			request.setAttribute("num", peDTO.getPeopleNum());
			request.setAttribute("zipcode", peDTO.getZipcode());
			
			System.out.println(peDTO.getName());
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("main/people/PeopleUp.jsp");
			dispatcher.forward(request, response);

		} else {
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script type='text/javascript'>");
			writer.println("alert('wrong.');");
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
