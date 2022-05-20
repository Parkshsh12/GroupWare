<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js"></script>
<script>
$(document).ready(function() {
	$("li.menu_list").on('click', function() {
		$(this).siblings('li').find('>a').css('background-color','#3c4b64').end().find('ul').slideUp();
		$(this).find('>a').css('background-color','#303c50').end().find('ul').slideDown();
	});
	$("li.menu_list ul").hide();
	return false;
});
</script>
<%
	String number = (String)session.getAttribute("number");
	String name = (String) session.getAttribute("name");
%>
    <nav class="menu">
        <div class="menu_login">
            <div>
                <img src="./resources/images/logo.png" alt="" width="100px">
            </div>
            <%
            	if(((String)session.getAttribute("name")) != null){
            %>
            <div style="margin:auto">
            <div class="login_text">
                <b style="color:#247ffb"><%= name %></b>님<p>환영합니다.
            </div>
            <div class="logout">
            	<a href="<c:url value="/logout.do"/>" class="btn btn-primary">로그아웃</a>
            </div>
            </div>
            <%
	            }
            %>
        </div>
        <a class="home_menu" href="<c:url value="/home.do"/>">홈 화면</a>
        <ul class="menu_main" id="menu">
            <li class="menu_list"><a href="#">인사 관리</a>
           		<ul>
                	<li class="menu_item"><a href="<c:url value="/member_list.do"/>">임직원 관리</a></li>
                	<li class="menu_item"><a href="<c:url value="/attendance_admin.do"/>">근태 관리</a></li>
            	</ul>
            </li>
            <li class="menu_list"><a href="#">영업 관리</a>
           		<ul>
               		<li class="menu_item"><a href="<c:url value="/business_search.do"/>">월별 매출 조회</a></li>
               		<li class="menu_item"><a href="<c:url value="/business_company.do"/>">거래처 조회</a></li>
               		<li class="menu_item"><a href="<c:url value="/purchase_main.do"/>">매입 관리</a></li>
               		<li class="menu_item"><a href="<c:url value="/sales_main.do"/>">매출 관리</a></li>
            	</ul>
            </li>
            <li class="menu_list"><a href="#">게시판</a>
            	<ul>
                	<li class="menu_item"><a href="<c:url value="/notice_main.do"/>">공지사항</a></li>
                	<li class="menu_item"><a href="<c:url value="/board_main.do"/>">자유게시판</a></li>
            	</ul>
            </li>
            <li class="menu_list"><a href="#">캘린더</a>
            	<ul>
                	<li class="menu_item"><a href="<c:url value="/schedule_all.do"/>">전체 일정</a></li>
                	<li class="menu_item"><a href="<c:url value="/schedule_dep.do"/>">부서 일정</a></li>
            	</ul>
           	</li>
            <li class="menu_list"><a href="#">주소록</a>
            	<ul>
                	<li class="menu_item"><a href="<c:url value="/contact.do"/>">주소록</a></li>
                	<li class="menu_item"><a href="#">조직도</a></li>
            	</ul>
            </li>
            <li class="menu_list"><a href="#">마이페이지</a>
            	<ul class="menu_drop drop6">
                	<li class="menu_item"><a href="<c:url value="/manager_pay.do"/>">급여 관리</a></li>
                	<li class="menu_item"><a class="link" href="<c:url value="/attendance_user.do"/>">근태 조회</a></li>
                	<c:set var="number" value="<%=number %>"/>
                	<li class="menu_item"><a href="<c:url value="/my_information.do?number=${number}"/>">내 정보 관리</a></li>
            	</ul>
          	</li>
        </ul>
    </nav>

