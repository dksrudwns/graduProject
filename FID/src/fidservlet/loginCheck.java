
package fidservlet;

import daoto.MemberDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({ "/loginCheck.do" })
public class loginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		try {
			//client�뿉�꽌 id,pw 諛쏄린
			String id = request.getParameter("id"); 
			String pw = request.getParameter("pw");
			
			MemberDAO mDAO = new MemberDAO();

			String mpw = mDAO.userCheck(id);

			//db�뿉 ���옣�맂 鍮꾨�踰덊샇�옉 媛숈�吏� �솗�씤
			if (pw.equals(mpw)) {
				HttpSession sessionL = request.getSession();
				Cookie cookie = new Cookie("sessionId", sessionL.getId());
				cookie.setMaxAge(2 * 60);
				sessionL.setAttribute("mid", id);
				sessionL.setAttribute("sessionId", sessionL.getId());
				sessionL.setAttribute("pw", pw);
				sessionL.setMaxInactiveInterval(10 * 60);
				response.setStatus(200);
				response.addCookie(cookie);
				response.sendRedirect("main.jsp");
			}

			else {
				response.setStatus(404);
				response.setCharacterEncoding("EUC-KR");
				PrintWriter writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('정보가 틀렸어요.');");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}