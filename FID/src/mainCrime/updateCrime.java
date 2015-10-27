package mainCrime;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoto.AddressCodeDAO;
import daoto.AddressCodeDTO;
import daoto.CrimeDAO;
import daoto.CrimeDTO;

/**
 * Servlet implementation class updateCrime
 */
@WebServlet("/updateCrime.do")
public class updateCrime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateCrime() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String num = (String) request.getParameter("num");
		String date = (String) request.getParameter("date");
		String code = (String) request.getParameter("rank");
		String pri = (String) request.getParameter("pri");
		String zipcode = (String) request.getParameter("c_zip_code");
		String sido = null;
		String sigungu = null;
		String detail = null;
		sido=(String)request.getParameter("c_sido");
		sigungu=(String)request.getParameter("c_sigungu");
		detail=(String)request.getParameter("c_detail_1");
		
		
		AddressCodeDAO aDAO = new AddressCodeDAO();
		AddressCodeDTO aDTO = new AddressCodeDTO(zipcode, sido, sigungu,detail);
		aDAO.insertAddressCode(aDTO);
		
		CrimeDTO cDTO = new CrimeDTO(code, num, zipcode, date, pri);
		CrimeDAO cDAO = new CrimeDAO();
		cDAO.crimeUpdate(cDTO);
		
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
