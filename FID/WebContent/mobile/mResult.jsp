<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" href="../css/ImgInforStyle.css" rel="stylesheet">
</head>
<body>
	<%
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String url = "jdbc:mysql://14.63.217.25:3306/project-fid";
			String dbId = "root";
			String dbPass = "5284";
			String Faceid = request.getParameter("FaceID");
			String faceid = "\""+Faceid+"\"";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbId, dbPass);

			String sql =  "select * from peopleInfo INNER JOIN picture on peopleInfo.peopleNum = picture.URL where picture.FaceID="+faceid;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString("URL"));
				String URL = "../Images/"+rs.getString("URL")+ ".jpg";
	%>
	<div class="Container">
		<h1>검색결과</h1>
		<div class="ImgInfor">
		<form method = "post" action = "../mDetailInforServlet.do">
		<button><span style="display: inline-block;"><img src=<%=URL%> alt="망함!!!"></span></button>
		<input type="hidden" name="num" value=<%=rs.getString("URL") %>>
			<h3><%=rs.getString("name") %></h3>
    	</form>
		
		</div>
	</div>
	<%
		}

		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
</body>
</html>