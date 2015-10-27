
package fidservlet;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

@WebServlet({ "/allInrollInDB.do" })
public class allInrollInDBservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs1 = null;
	int rs2 = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpClient httpclient = HttpClients.createDefault();
		try {
			String jdbcUrl = "jdbc:mysql://14.63.217.25:3306/project-fid";
			String dbId = "root";
			String dbPass = "5284";

			Class.forName("com.mysql.jdbc.Driver");
			conn = ((Connection) DriverManager.getConnection(jdbcUrl, dbId, dbPass));

			String sql1 = "select * from picture";
			pstmt1 = conn.prepareStatement(sql1);
			rs1 = pstmt1.executeQuery();

			URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/face/v0/detections");

			builder.setParameter("analyzesFaceLandmarks", "false");
			builder.setParameter("analyzesAge", "false");
			builder.setParameter("analyzesGender", "false");
			builder.setParameter("analyzesHeadPose", "false");

			URI uri = builder.build();
			HttpPost requestToFace = new HttpPost(uri);

			requestToFace.addHeader("Ocp-Apim-Subscription-Key", "1111f659524d43d2af638b48f3fb5f3c");

			while (rs1.next()) {
				String pi = rs1.getString("URL");// 9000905121212
				String URL = pi+ ".jpg";// 9000905121212.jpg
				String FaceID = rs1.getString("FaceID");
				
				System.out.println(FaceID);
				System.out.println(URL);
				System.out.println(pi);

				File file = new File("/opt/apache-tomcat-8.0.24/webapps/FID/Images/" + URL);
				//File file = new File("C:/Users/Akj/Desktop/projest/work/FID/WebContent/" + URL);
				

				FileEntity fentity = new FileEntity(file, ContentType.create("application/octet-stream"));
				requestToFace.setEntity(fentity);

				HttpResponse responseFromFace = httpclient.execute(requestToFace);
				HttpEntity entity = responseFromFace.getEntity();

				if (entity != null) {
					JSONArray ja = new JSONArray(EntityUtils.toString(entity));
					JSONObject obj = ja.getJSONObject(0);

					String faceid = "\"" + obj.get("faceId").toString() + "\"";
					String sql2 = "UPDATE picture SET FaceID =" + faceid + " WHERE URL =" + pi;
					pstmt2 = conn.prepareStatement(sql2);
					rs2 = pstmt2.executeUpdate();
				}
					
			}
			response.sendRedirect("/main.jsp");

		} catch (Exception e) {
			e.printStackTrace();

			response.sendRedirect("www.naver.com");
		}
	}
}