package chart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;

import daoto.CrimeDAO;
import daoto.MissingDAO;

/**
 * Servlet implementation class yearMissing
 */
@WebServlet("/yearMissing.do")
public class yearMissing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public yearMissing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String year = (String) request.getParameter("year");
				// System.out.println(year);
				MissingDAO mDAO = new MissingDAO();
				JSONObject monthList = mDAO.selectMissingYearInfo(year);
				System.out.println(monthList);
				response.setContentType("application/x-json; charset=UTF-8");
				PrintWriter out = null;
				out = response.getWriter();
				out.print(monthList);
				out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
