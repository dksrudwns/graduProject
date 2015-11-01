package fidservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoto.MemberDAO;

/**
 * Servlet implementation class userLogincheck
 */
@WebServlet("/userLogincheck.do")
public class userLogincheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userLogincheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		try {
			//client�뿉�꽌 id,pw 諛쏄린
			String id = request.getParameter("id"); 
			String pw = request.getParameter("pw");
			
			MemberDAO mDAO = new MemberDAO();

			String mpw = mDAO.userCheck(id);
			String mlv = mDAO.getlv(id);

			//db�뿉 ���옣�맂 鍮꾨�踰덊샇�옉 媛숈�吏� �솗�씤
			
			if (mlv.equals("1") &&pw.equals(mpw)) {
				HttpSession sessionL = request.getSession();
				sessionL.setAttribute("mid", id);
				sessionL.setAttribute("sessionId", sessionL.getId());
				sessionL.setAttribute("pw", pw);
				sessionL.setMaxInactiveInterval(10 * 60);
				response.setStatus(200);
				response.sendRedirect("main.jsp");
			}
			else if(mlv.equals("2") &&pw.equals(mpw)){
				
				response.setCharacterEncoding("euc-kr");
				PrintWriter writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('관리자 계정이 아닙니다.');");
				writer.println("history.go(-1);");
				writer.println("</script>");
				writer.flush();
				return;
			}
			else {
				response.setCharacterEncoding("euc-kr");
				PrintWriter writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('입력 정보를 다시 한번 확인해주세요.');");
				writer.println("history.go(-1);");
				writer.println("</script>");
				writer.flush();
				return;
				
			}

		} catch (Exception e) {
			response.setStatus(404);
			response.sendRedirect("main.jsp");
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
