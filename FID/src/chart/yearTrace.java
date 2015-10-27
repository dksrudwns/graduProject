package chart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;

import daoto.MissingDAO;
import daoto.TraceDAO;

/**
 * Servlet implementation class yearTrace
 */
@WebServlet("/yearTrace.do")
public class yearTrace extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public yearTrace() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String year = (String) request.getParameter("year");
		// System.out.println(year);
		TraceDAO tDAO = new  TraceDAO();
		JSONObject monthList = tDAO.selectTraceYearInfo(year);
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
