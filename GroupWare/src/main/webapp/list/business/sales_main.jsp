<%@page import="mvc.model.PStableDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/resources/css/purchase_main.css"/>">
</head>
<body>
<%
	ArrayList list = (ArrayList) request.getAttribute("list");
	int j = list.size();
%>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
    <div class="container">
        <div align="center" class="business_search">
            <form method="post">
                <table>
                    <tr>
                        <td>
                            <select class="search_select">
                                <option value="subject">제목</option>
                                <option value="content">본문 내용</option>
                                <option value="name">글쓴이</option>
                            </select>
                        </td>
                        <td class="search">
                            <input class="search_content" type="text" name="text" />
                            <input type="submit" class="search_btn" value="검색" />
                        </td>
                    </tr>
                </table>
            </form>
            <div class="business_add">
                <a href="/psadd.do?division=s" class="businessAdd btn btn-primary">등록</a>
            </div>
        </div>
        <hr>
        <table class="table">
            <tr class="table_header">
                <th>순서</th>
                <th>업체명</th>
                <th>분류</th>
                <th>품명</th>
                <th>수량</th>
                <th>단위</th>
                <th>가격</th>
                <th>비고</th>
                <th>일자</th>
                <th>변경</th>
            </tr>
            <%
            	for(int i = 0; i < list.size(); i++){
            		PStableDTO dto = (PStableDTO) list.get(i);
            %>
            <tr>
                <td><%=j-- %></td>
                <td><%=dto.getCompany() %></td>
                <td><%=dto.getCategory() %></td>
                <td><%=dto.getName() %></td>
                <td><%=dto.getQty() %></td>
                <td><%=dto.getUnit() %></td>
                <td><%=dto.getPrice() %></td>
                <td><%=dto.getBecause() %></td>
                <td><%=dto.getDate() %></td>
                <td><a href="#">수정</a></td>
            </tr>
            <%
            	}
            %>
        </table>
    </div>
</body>
</html>