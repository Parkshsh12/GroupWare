<%@page import="java.text.DecimalFormat"%>
<%@page import="mvc.model.PaymentDAO"%>
<%@page import="mvc.model.MemberDTO"%>
<%@page import="mvc.model.PaymentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	ArrayList<PaymentDTO> paymentList = (ArrayList)request.getAttribute("paymentList");
	MemberDTO member = (MemberDTO)request.getAttribute("member");
	int pageNum = ((Integer)request.getAttribute("pageNum")).intValue();
	int total_page = ((Integer)request.getAttribute("total_page")).intValue();
	String number = (String)session.getAttribute("number");
	int count = 0;
	if(pageNum == 1){
		count = 1;
	} else{
		count = ((pageNum - 1 ) * 5)+1;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/d777ad58b4.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/manager_pay.css?v=1"/>">
</head>
<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
    <div class="container">
        <table class="table">
            <tr class="table_header">
                <th>번호</th>
                <th>귀속년월</th>
                <th>지급일자</th>
                <th>급여구분</th>
            </tr>
            <%
            	for(int i = 0; i<paymentList.size(); i++){
            		PaymentDTO payment = paymentList.get(i);
            %>
            <tr>
                <td class="table_num"><%=count+i %></td>
                <td><%=payment.getImputed_date() %></td>
                <td><%=payment.getPayment_date() %></td>
                <td><button type="button"  id="modal_opne_btn<%=count+i %>" class="btn btn-default btn-sm">보기</button>
                	 <div class="modal" id="modal<%=count+i %>">
				    	<div class="container modal_content">
					        <h2 class="title">5월 급여 명세서
					        	<button type="button" id="modal_close_btn<%=count+i %>"><i class="fa-solid fa-square-xmark"></i></button>
					        </h2>
					        <table class="table">
					            <tr class="table_header">
					                <th>부서</th>
					                <td><%=member.getDepartment() %></td>
					                <th>사번</th>
					                <td><%=member.getNumber() %></td>
					            </tr>
					            <tr class="table_header">
					                <th>직급</th>
					                <td><%=member.getPosition() %></td>
					                <th>성명</th>
					                <td><%=member.getName() %></td>
					            </tr>
					        </table>
					        <table class="table">
								  <tr>
								    <th rowspan="2">지급내역</th>
								    <th>기본급</th>
								    <th>연장근무</th>
								    <th>시간<br>0</th>
								    <th>휴일근무</th>
								    <th>시간<br>0</th>
								    <th>직급수당</th>
								    <th>주휴수당</th>
								    <th>추가수당</th>
								  </tr>
								  <tr>
								  <%
								  	PaymentDAO dao = PaymentDAO.getInstance();
								  	int[] salary = dao.getSalary(member.getHourlywage());
								  	DecimalFormat formatter = new DecimalFormat("###,###,###,###");
								  %>
								    <td><%=formatter.format(salary[0]) %>원</td>
								    <td colspan="2">0원</td>
								    <td colspan="2">0원</td>
								    <td><%=formatter.format(payment.getPosition_pay())%>원</td>
								    <td><%=formatter.format(salary[1]) %>원</td>
								    <td><%=formatter.format(payment.getMargin_pay()) %>원</td>
								  </tr>
							</table>
					        <table class="table">
					            <tr class="table_header">
					                <th rowspan="2">공제내역</th>
					                <th>국민연금</th>
					                <th>건강보험</th>
					                <th>고용보험</th>
					                <th>산재보험</th>
					                <th>소득세</th>
					            </tr>
					            <tr class="table_header">
					                <td><%=formatter.format(salary[2]) %>원</td>
					                <td><%=formatter.format(salary[3]) %>원</td>
					                <td><%=formatter.format(salary[4]) %>원</td>
					                <td><%=formatter.format(salary[5]) %>원</td>
					                <td><%=formatter.format(salary[6]) %>원</td>
					            </tr>
					        </table>
					        <table class="table">
					            <tr class="table_header">
					                <th rowspan="2">합계</th>
					                <th>지급총액</th>
					                <th>공제세액</th>
					                <th>차감지급액</th>
					            </tr>
					            <tr class="table_header">
					                <td><%=formatter.format(salary[7]) %>원</td>
					                <td><%=formatter.format(salary[8])%>원</td>
					                <td><%=formatter.format(salary[7] - salary[8]) %>원</td>
					            </tr>
					        </table>
					    </div>
				    </div>
				<script>
				    document.getElementById("modal_opne_btn<%=count+i %>").onclick = function() {
				        document.getElementById("modal<%=count+i %>").style.display="block";
				    }
				   
				    document.getElementById("modal_close_btn<%=count+i %>").onclick = function() {
				        document.getElementById("modal<%=count+i %>").style.display="none";
				    }   
				</script>
                </td>
                
            </tr>
            
            <%
            	}
            %>
        </table>
        <div align="center">
        	<c:set var="pageNum" value="<%=pageNum %>"/>
        	<c:set var="number" value="<%=number %>"/>
        	<c:forEach var="i" begin="1" end="<%=total_page %>">
            	<a href="./manager_pay.do?pageNum=${i}&number=${number}">
            	<c:choose>
            		<c:when test="${pageNum==i}">
            			<b>[${i}]</b>            		
            		</c:when>
            		<c:otherwise>
            			<font color='4C5317'>[${i}]</font>
            		</c:otherwise>
            	</c:choose>
            	</a>
            </c:forEach>
        </div>
    </div>
   
</body>
</html>