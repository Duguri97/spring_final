<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/inc/header.jsp" %>
<title></title>
</head>
<body>
<div class="col-sm-6">
			<div class="team-member">
				<img src="data:image/jpeg;base64,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" class="img-responsive img-circle" alt="칸">
				<h4>한 창 희</h4>
				<div class="text-left">
					<ul title="경력">
						<li>전임 강사</li>
						<li>강의 2년</li>
					</ul>
					<ul title="강의">
						<li>JAVA, JSP, SPRING,DB,<br> Android, Arduino <br>
						</li>
					</ul>
					<ul title="자격">
						<li>정보처리기사</li>
						<li>직업능력개발 훈련교사(정보기술운영관리,무선통신구축,정보기술개발)</li>
					</ul>
				
					<ul title="실무경력" style="list-style-type:circle;">
						<li>미츠비시전기 나고야 제작소  e-Factory개발</li>
					</ul>
				</div>
			</div>
		</div>
</body>



</html>