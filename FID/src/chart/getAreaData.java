package chart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import daoto.CrimeDAO;
import daoto.MissingDAO;
import daoto.TraceDAO;

/**
 * Servlet implementation class getAreaData
 */
@WebServlet("/getAreaData.do")
public class getAreaData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAreaData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sido = (String)request.getParameter("sido");
		JSONObject jtype = new JSONObject();
		//범죄get
		CrimeDAO cDAO = new CrimeDAO();
		JSONArray jCrime = cDAO.selectCountArea(sido);
		
		//수배get
		TraceDAO tDAO = new TraceDAO();
		JSONArray jTrace = tDAO.selectCount();
		
		//실종get
		MissingDAO mDAO = new MissingDAO();
		JSONArray jMissing = mDAO.selectCountArea(sido);
		try {
			jtype.put("Crime", jCrime);
			jtype.put("Trace", jTrace);
			jtype.put("Missing", jMissing);
			System.out.println(jtype);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			PrintWriter out = null;
			response.setContentType("application/x-json; charset=UTF-8");
			out=response.getWriter();
			out.print(jtype);
			out.flush();
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
