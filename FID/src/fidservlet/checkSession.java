package fidservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class checkSession
 */
@WebServlet("/checkSession.do")
public class checkSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public checkSession() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cookie = (String) request.getParameter("id");

		if(cookie.equals("sessionId")){
			response.setStatus(200);
			response.addHeader("login", "suc");
		}
		else{
			response.setStatus(400);
			response.addHeader("login", "fal");
		}
		
		
		
		
		// TODO Auto-generated method stub
/*
		HttpSession session = request.getSession();

		String sessionId = (String) session.getAttribute("sessionId");
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals("sessionId")) {//쿠키에서 sessionId을 키로 하는 쿠키 찾기

					if (cookie.getValue().equals(sessionId)) {//sessionId키값이 세션에서의 sessionId의 값과 같은지 확인
						response.setStatus(200);
					} else {
						response.setStatus(400);
					}
				}
			}
		}

		response.setStatus(400);
*/
		/*
		 * Cookie[] co = request.getCookies(); try{ for (int i = 0; i <
		 * co.length; i++) { if (co[i].getName().equals("id")) { Cookie cookie =
		 * new Cookie("test", "succ"); response.addCookie(cookie);
		 * response.setStatus(200); return; } } }catch(Exception e){ Cookie
		 * cookie = new Cookie("test", "notsend"); response.addCookie(cookie);
		 * response.setStatus(401); }
		 */

		/*
		 * String result = request.getParameter("id");
		 * 
		 * if(result.equals("Good")){ response.setStatus(200); }
		 * 
		 * response.setStatus(400);
		 */
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
