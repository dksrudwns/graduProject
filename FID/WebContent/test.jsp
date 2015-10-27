<%@page import="daoto.peopleInfoDTO"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="memberId" value="${sessionScope.mid}"></c:set>
<c:choose>
	<c:when test="${memberId != null}">
	</c:when>
	<c:otherwise>
		<c:redirect url="Login.jsp"></c:redirect>	
	</c:otherwise>
</c:choose>

