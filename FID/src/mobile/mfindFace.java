
package mobile;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import daoto.PictureDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

@WebServlet({ "/search.do" })
public class mfindFace extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int maxImageSize = 4*1024*1024;

		String savePath = "/opt/apache-tomcat-8.0.24/webapps/FID/tempImage/";
		String imageName = null;
		MultipartRequest getImage = null;

		String faceid = null;
		try {
			getImage = new MultipartRequest(request, savePath, maxImageSize, "UTF-8", new DefaultFileRenamePolicy());
			imageName = "temp.jpg";
		} catch (Exception localException1) {
		}

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

			File file = new File(savePath + imageName);
			FileEntity reqEntityD = new FileEntity(file, ContentType.create("application/octet-stream"));
			requestD.setEntity(reqEntityD);

			HttpResponse responseD = httpclientD.execute(requestD);
			HttpEntity entityD = responseD.getEntity();
			file.delete();

			if (entityD != null) {
				JSONArray ja = new JSONArray(EntityUtils.toString(entityD));
				JSONObject obj = ja.getJSONObject(0);
				faceid = obj.getString("faceId");
				System.out.println(faceid);
				//response.addHeader("FaceID", "D" + faceid);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			HttpClient httpclientC = HttpClients.createDefault();
			try {
				URIBuilder builderC = new URIBuilder("https://api.projectoxford.ai/face/v0/findsimilars");

				URI uriC = builderC.build();
				HttpPost requestC = new HttpPost(uriC);
				requestC.addHeader("Ocp-Apim-Subscription-Key", "1111f659524d43d2af638b48f3fb5f3c");
				requestC.addHeader("Content-Type", "application/json");

				JSONObject body = new JSONObject();
				String FaceID = faceid;
				body.put("faceId", FaceID);

				PictureDAO pdao = new PictureDAO();
				JSONArray Facelist = pdao.PictureIdSelect();

				body.put("faceIds", Facelist);

				StringEntity reqEntityC = new StringEntity(body.toString());
				requestC.setEntity(reqEntityC);

				HttpResponse responseC = httpclientC.execute(requestC);
				HttpEntity entityC = responseC.getEntity();

				if (entityC != null) {
					JSONArray ja = new JSONArray(EntityUtils.toString(entityC));
					JSONObject obj = ja.getJSONObject(0);
					faceid = obj.getString("faceId");
					System.out.println(faceid);
					response.addHeader("FaceID", faceid);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}