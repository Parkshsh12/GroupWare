<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/resources/css/topbar.css"/>">
<body>
    <div class="topbar">
        <h1><%=request.getParameter("id") %></h1>
        <img src="./resources/images/logo.png" alt="" width="100px">
    </div>
</body>
