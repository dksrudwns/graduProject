package mainPeople;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.Enumeration;

import javax.servlet.ServletContext;
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

import daoto.PeopleInDAO;
import daoto.PeopleInDTO;

/**
 * Servlet implementation class peopleInrol
 */
@WebServlet("/peopleInrol.do")
public class peopleInrol extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public peopleInrol() {
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
		// for post 방식 인코딩
		request.setCharacterEncoding("utf-8");

		MultipartRequest multi = null;
		String uname = null; // 올릴때 이름
		String rname = null;// 저장된 이름
		int maxsize = 4 * 1024 * 1024;
		String savePath = "/opt/apache-tomcat-8.0.24/webapps/FID/Images";

		// 개인정보
		String peopleNum = null;
		String name = null;
		String zipCode = null;
		String sido = null;
		String sigungu = null;
		String detailAddr = null;
		String detail = null;
		String faceid = null;

		try {

			multi = new MultipartRequest(request, savePath, maxsize, "utf-8", new DefaultFileRenamePolicy());

			Enumeration<?> param = multi.getParameterNames();

			// 사람들 정보 get
			name = multi.getParameter("name");
			peopleNum = multi.getParameter("idNum1") + multi.getParameter("idNum2");
			zipCode = multi.getParameter("zip_code");
			sido = multi.getParameter("sido");
			sigungu = multi.getParameter("sigungu");
			detail = multi.getParameter("detail_1");
			detailAddr = detail + ' ' + multi.getParameter("detail_2");
			if (zipCode == null || zipCode == "" || peopleNum == "" || peopleNum == null) {
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
				PeopleInDAO pDAO = new PeopleInDAO();
				PeopleInDTO pDTO = new PeopleInDTO(peopleNum, name, zipCode, sido, sigungu, detailAddr, faceid, detail);
				pDAO.insertPeopleIn(pDTO);

				response.sendRedirect("main.jsp");
			} catch (Exception e) {
				// TODO: handle exception
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
