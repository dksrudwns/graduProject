package mainMissing;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import daoto.AddressCodeDAO;
import daoto.AddressCodeDTO;
import daoto.MissingDAO;
import daoto.MissingDTO;
import daoto.MissingpeDAO;
import daoto.MissingpeDTO;
import daoto.PeopleInDAO;
import daoto.PeopleInDTO;
import daoto.PictureDAO;
import daoto.PictureDTO;

/**
 * Servlet implementation class missingRegister
 */
@WebServlet("/missingRegister.do")
public class missingRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public missingRegister() {
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
		// TODO Auto-generated method stub
		// for post 방식 인코딩
		request.setCharacterEncoding("utf-8");

		MultipartRequest multi = null;
		String uname = null; // 올릴때 이름
		String rname = null;// 저장된 이름
		int maxsize = 4 * 1024 * 1024;
		String savePath = "/opt/apache-tomcat-8.0.24/webapps/FID/Images";

		// 개인정보
		String peopleNum = null;
		String zipCode = null;
		String sido = null;
		String sigungu = null;
		String detailAddr = null;
		String detail = null;
		String rank = null;
		String missDate = null;
		String faceid = null;
		String pro_peopleNum = null;
		String pro_phon = null;

		try {

			File Cfile = new File(savePath + "/" + peopleNum + ".jpg");
			Cfile.delete();

			multi = new MultipartRequest(request, savePath, maxsize, "utf-8", new DefaultFileRenamePolicy());

			Enumeration<?> param = multi.getParameterNames();

			// 사람들 정보 get
			peopleNum = multi.getParameter("idNum1") + multi.getParameter("idNum2");
			rank = multi.getParameter("rank");
			missDate = multi.getParameter("missingDate");
			zipCode = multi.getParameter("zip_code");
			sido = multi.getParameter("sido");
			sigungu = multi.getParameter("sigungu");
			detail = multi.getParameter("detail_1");
			detailAddr = detail + ' ' + multi.getParameter("detail_2");
			pro_peopleNum = multi.getParameter("pro_idNum1") + multi.getParameter("pro_idNum2");
			pro_phon = multi.getParameter("pro_phon");

			if (zipCode == null||zipCode == ""||peopleNum == "" || peopleNum == null || pro_peopleNum == null || pro_peopleNum == "") {
				response.setCharacterEncoding("EUC-KR");
				PrintWriter writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('정보를 다시 확인하세요.');");
				writer.println("history.go(-1);");
				writer.println("</script>");
				writer.flush();
				return;
			}

			Enumeration<?> files = multi.getFileNames();

			while (files.hasMoreElements()) {
				uname = (String) files.nextElement();
				rname = multi.getFilesystemName(uname);

			}

			// 파일명 변경 주민번호로
			File tname = new File(savePath + "/" + rname);
			File Rename = new File(savePath + "/" + peopleNum + ".jpg");
			tname.renameTo(Rename);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			HttpClient httpclientD = HttpClients.createDefault();
			try {
				URIBuilder builderD = new URIBuilder("https://api.projectoxford.ai/face/v0/detections");

				builderD.setParameter("analyzesFaceLandmarks", "false");
				builderD.setParameter("analyzesAge", "false");
				builderD.setParameter("analyzesGender", "false");
				builderD.setParameter("analyzesHeadPose", "false");

				URI uriD = builderD.build();
				HttpPost requestD = new HttpPost(uriD);

				requestD.addHeader("Ocp-Apim-Subscription-Key", "1111f659524d43d2af638b48f3fb5f3c");

				File file = new File(savePath + "/" + peopleNum + ".jpg");
				FileEntity reqEntityD = new FileEntity(file, ContentType.create("application/octet-stream"));
				requestD.setEntity(reqEntityD);

				HttpResponse responseD = httpclientD.execute(requestD);
				HttpEntity entityD = responseD.getEntity();

				if (entityD != null) {
					JSONArray ja = new JSONArray(EntityUtils.toString(entityD));
					JSONObject obj = ja.getJSONObject(0);
					faceid = obj.getString("faceId");
					System.out.println(faceid);
				}

				MissingDAO mDAO = new MissingDAO();
				MissingDTO mDTO = new MissingDTO(rank, peopleNum, missDate, null, zipCode, null, null);
				mDAO.missingInsert(mDTO);

				AddressCodeDTO aDTO = new AddressCodeDTO(zipCode, sido, sigungu, detail);
				AddressCodeDAO aDAO = new AddressCodeDAO();
				aDAO.insertAddressCode(aDTO);

				PictureDAO pDAO = new PictureDAO();
				PictureDTO pDTO = new PictureDTO(peopleNum, faceid);
				pDAO.insertPicture(pDTO);

				MissingpeDTO mpDTO = new MissingpeDTO(peopleNum, pro_peopleNum, pro_phon);
				MissingpeDAO mpDAO = new MissingpeDAO();
				mpDAO.missingpeInsert(mpDTO);

				response.sendRedirect("main.jsp");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
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
