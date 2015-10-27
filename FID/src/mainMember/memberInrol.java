package mainMember;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoto.MemberDAO;
import daoto.MemberDTO;
import daoto.peopleInfoDAO;

/**
 * Servlet implementation class memberInrol
 */
@WebServlet("/memberInrol.do")
public class memberInrol extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public memberInrol() {
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
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		String lv = request.getParameter("rank");
		String pn = request.getParameter("idNum1") + request.getParameter("idNum2");

		if(lv.equals("admin")){
			lv="1";
		}else{
			lv="2";
		}
		
		peopleInfoDAO pDAO = new peopleInfoDAO();
		int checkp = pDAO.confirmPN(pn);

		MemberDAO mDAO = new MemberDAO();
		MemberDTO mDTO = new MemberDTO(id, pw, lv, pn);
		if (checkp == 0) {
			try {

				mDAO.memberInsert(mDTO);
				response.sendRedirect("main.jsp");

			} catch (Exception e) {
				// TODO: handle exception
				response.setCharacterEncoding("EUC-KR");
				PrintWriter writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('정보를 다시 확인하세요.');");
				writer.println("history.go(-1);");
				writer.println("</script>");
				writer.flush();
				return;
			}

		} else {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
