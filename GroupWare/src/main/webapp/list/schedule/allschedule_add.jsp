<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="../../resources/css/depschedule_add.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<jsp:include page="../../main_topbar/main.jsp"/>
	<jsp:include page="../../main_topbar/topbar.jsp"/>
	<jsp:include page="../../main_topbar/contents.jsp"/>
    <div class="update_info">
        <form name="boardAdd" method="post" class="form-horizontal">
            <div class="form-group row">
                <label class="col-sm-2 input-name">일정명</label>
                <div class="col-sm-5">
                    <input class="form-input" type="text" required placeholder="일정명을 입력하세요.">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">일정일시</label>
                <div class="col-sm-5">
                   <input class="input-date" type="date" required> ~ <input class="input-date" type="date" required>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 input-name">일정내용</label>
                <div class="col-sm-5">
                    <textarea cols="50" rows="5"></textarea>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-offset-2 col-sm-10" align="right">
                    <input type="submit" class="btn btn-primary" value="등록">
                    <a href="#" class="btn btn-primary">취소</a>
                </div>
            </div>
        </form>
    </div>
</body>
</html>