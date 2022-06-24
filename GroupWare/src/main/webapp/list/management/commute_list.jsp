<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mvc.model.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mvc.model.CommuteDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member_list</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="<c:url value="/resources/css/commute_list.css?ver=2"/>">
</head>
<body>
<%
	ArrayList<CommuteDTO> commutelist = (ArrayList<CommuteDTO>) request.getAttribute("commutelist");
	ArrayList<MemberDTO> memberlist = (ArrayList<MemberDTO>) request.getAttribute("memberlist");
	
	String[][] commute_true = (String[][]) request.getAttribute("commute_true");
	
	/* SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	String today = formatter.format(new java.util.Date()); */
%>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
	<div class="container">

        
        <table class="table">
            <tr class="commute_true table_header">
				<th>순서</th>
                <th>사원번호</th>
                <th>이름</th>
                <th>부서</th>
                <th>직위</th>
                <th>주소</th>
                <th>휴대전화</th>
                <th>출근시간</th>
            </tr>
            <%
            	for(int i = 0; i < commute_true.length; i++){
            		MemberDTO dto = null;
            		for(int j = 0; j < memberlist.size(); j++){
            			if(commute_true[i][0].equals(memberlist.get(j).getNumber())){
            				dto = memberlist.get(j);
            			}
            		}
            %>
            <tr>
                <td><%=i+1%></td>
                <td><%=commute_true[i][0]%></td>
                <td><%=dto.getName() %></td>
				<td><%=dto.getPosition() %></td>
                <td><%=dto.getDepartment() %></td>
				<td><%=dto.getAddress() %></td>
				<td><%=dto.getPhone() %></td>
                <td><%=commute_true[i][1]%></td>
            </tr>
            <%
            	}
            %>
        </table>
                <table class="table">
        	<tr class="commute_false table_header">
				<th>순서</th>
                <th>사원번호</th>
                <th>이름</th>
                <th>부서</th>
                <th>직위</th>
                <th>주소</th>
                <th>휴대전화</th>
            </tr>
            <%
           		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
            	for(int i = 0; i < memberlist.size(); i++){
            		MemberDTO dto = null;
            		for(int j = 0; j < commute_true.length; j++){
            			if(commute_true[j][0].equals(memberlist.get(i).getNumber())){
            				dto = memberlist.get(i);
            				list.add(dto);
            			}
            		}
            	}
            	
            	list.remove(memberlist);
            	
            	for(int i = 0; i < list.size(); i++){
            		MemberDTO dto = null;
           			dto = list.get(i);
            %>
            
            <tr>
                <td><%=i+1%></td>
                <td><%=dto.getNumber()%></td>
                <td><%=dto.getName()%></td>
				<td><%=dto.getPosition()%></td>
                <td><%=dto.getDepartment()%></td>
				<td><%=dto.getAddress()%></td>
				<td><%=dto.getPhone()%></td>
            </tr>
            <%
            	}
            %>
        </table>

<!--         <div align="center">
            <b>
                <a href="#">[1]</a>
                <a href="#">[2]</a>
                <a href="#">[3]</a>
            </b>
        </div>
        <div class="button" align="right">
        	<a href="./member_update.jsp" class="boardAdd btn btn-primary">직원추가</a>
        </div> -->
        <div align="center" class="board_search">
            <form method="post">
                <table>
                    <tr>
                        <td>
                            <select class="search_select">
                                <option value="number">사원번호</option>
                                <!--<option value="pw">패스워드</option>-->
                                <option value="name">이름</option>
                                <option value="adress">주소</option>
                                <option value="phone">휴대전화</option>
                                <option value="positon">직위</option>
                                <option value="department">부서</option>
                                <option value="email">이메일</option>
                                <option value="join_date">입사일</option>
                                <!--<option value="hourlywage">시급</option>-->
                            </select>
                        </td>
                        <td class="search">
                            <input class="search_content" type="text" name="text" />
                            <input type="submit" class="search_btn" value="검색" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>