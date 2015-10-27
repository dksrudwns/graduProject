package chart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.JsonNumber;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;

import daoto.AddressCodeDAO;

/**
 * Servlet implementation class getSido
 */
@WebServlet("/getSido.do")
public class getSido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getSido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONArray sidoList = new JSONArray();
		AddressCodeDAO aDAO = new AddressCodeDAO();
		sidoList = aDAO.selectSido();
		System.out.println(sidoList);
		PrintWriter out = null;
		response.setContentType("application/x-json; charset=UTF-8");

		out = response.getWriter();
		out.print(sidoList);
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
