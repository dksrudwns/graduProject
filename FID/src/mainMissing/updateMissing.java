package mainMissing;

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
import daoto.MissingDAO;
import daoto.MissingDTO;

/**
 * Servlet implementation class updateMissing
 */
@WebServlet("/updateMissing.do")
public class updateMissing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateMissing() {
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
		String code = (String) request.getParameter("mrank");
		String pri = (String) request.getParameter("pri");
		String mzipcode = (String) request.getParameter("m_zip_code");
		String mdate = (String) request.getParameter("mdate");
		String fzipcode = (String) request.getParameter("f_zip_code");
		String fdate = (String) request.getParameter("fdate");
		String msido = null;
		String msigungu = null;
		String mdetail = null;
		msido=(String)request.getParameter("m_sido");
		msigungu=(String)request.getParameter("m_sigungu");
		mdetail=(String)request.getParameter("m_detail_1");
		String fsido = null;
		String fsigungu = null;
		String fdetail = null;
		fsido=(String)request.getParameter("f_sido");
		fsigungu=(String)request.getParameter("f_sigungu");
		fdetail=(String)request.getParameter("f_detail_1");
		
		
		AddressCodeDAO aDAO = new AddressCodeDAO();
		AddressCodeDTO maDTO = new AddressCodeDTO(mzipcode, msido, msigungu, mdetail);
		aDAO.insertAddressCode(maDTO);
		AddressCodeDTO faDTO = new AddressCodeDTO(fzipcode, fsido, fsigungu, fdetail);
		aDAO.insertAddressCode(faDTO);
		
		MissingDTO mDTO = new MissingDTO(code, num, mdate, fdate, mzipcode, fzipcode, pri);
		MissingDAO mDAO = new MissingDAO();
		mDAO.updateMissing(mDTO);
		
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
