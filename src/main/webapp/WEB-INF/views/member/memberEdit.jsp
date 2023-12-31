<%@page import="com.study.member.vo.MemberVO" %>
<%@page import="java.sql.Connection" %>
<%@page import="java.sql.SQLException" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.PreparedStatement" %>
<%@ page import="com.study.member.service.IMemberService" %>
<%@ page import="com.study.member.service.MemberServiceImpl" %>
<%@ page import="java.lang.reflect.Member" %>
<%@ page import="com.study.exception.BizNotFoundException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/inc/header.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp" %>

<%--<%--%>
<%--    String memId = request.getParameter("memId");--%>
<%--    IMemberService memberService = new MemberServiceImpl();--%>
<%--    try {--%>
<%--        MemberVO member = memberService.getMember(memId);--%>
<%--        request.setAttribute("member", member);--%>
<%--    } catch (BizNotFoundException e) {--%>
<%--        request.setAttribute("bnf", e);--%>
<%--    }--%>
<%--%>--%>

<%--<c:if test="${bnf != null}">--%>

<%--    <div class="alert alert-warning">해당 멤버를 찾을 수 없습니다</div>--%>
<%--    <a href="memberList.wow" class="btn btn-default btn-sm"> <span class="glyphicon glyphicon-list"--%>
<%--                                                                   aria-hidden="true"></span> &nbsp;목록--%>
<%--    </a>--%>
<%--</c:if>--%>


<%--<c:if test="${bnf == null}">--%>
<div class="container">
    <h3>회원 정보 수정</h3>
    <form action="memberModify.wow" method="post">
        <table class="table table-striped table-bordered">
            <tbody>
            <tr>
                <th>아이디</th>
                <td>아이디는 못바꾸지만 데이터는 넘어가야지 <input type="hidden" name="memId" value="${member.memId}"></td>
            </tr>
            <tr>
                <th>비밀번호</th>
                <td><input type="password" name="memPass" class="form-control input-sm"
                           pattern="\w{4,}" title="알파벳과 숫자로 4글자 이상 입력"></td>
            </tr>
            <tr>
                <th>회원명</th>
                <td><input type="text" name="memName" class="form-control input-sm" value="${member.memName}"
                           required="required" pattern="[가-힣]{2,}" title="한글로 2글자 이상 입력"></td>
            </tr>
            <tr>
                <th>우편번호</th>
                <td><input type="text" name="memZip" class="form-control input-sm" value='${member.memZip}'></td>
            </tr>
            <tr>
                <th>주소</th>
                <td><input type="text" name="memAdd1" class="form-control input-sm" value='${member.memAdd1}'>
                    <input type="text" name="memAdd2" class="form-control input-sm" value='${member.memAdd2}'>
                </td>
            </tr>
            <tr>
                <th>생일</th>
                <td><input type="date" name="memBir" class="form-control input-sm" value='${member.memBir}'></td>
            </tr>
            <tr>
                <th>메일</th>
                <td><input type="email" name="memMail" class="form-control input-sm" required="required"
                           value='${member.memMail}'></td>
            </tr>
            <tr>
                <th>핸드폰</th>
                <td><input type="tel" name="memHp" class="form-control input-sm" value='${member.memHp}'></td>
            </tr>
            <tr>
                <th>직업</th>
                <td>

                    <select name="memJob" class="form-control input-sm">
                        <option value="">-- 직업 선택 --</option>
                        <c:forEach items="${jobList}" var="code">
                            <option value="${code.commCd}" ${member.memJob eq code.commCd ? "selected='selected'" : ''} >${code.commNm}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th>취미</th>
                <td>

                    <select name="memHobby" class="form-control input-sm">
                        <option value="">-- 취미 선택 --</option>
                        <c:forEach items="${hobbyList}" var="code">
                            <option value="${code.commCd}" ${member.memHobby eq code.commCd ? "selected='selected'" : ""} >${code.commNm}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th>마일리지</th>
                <td>${member.memMileage}</td>
            </tr>
            <tr>
                <th>탈퇴여부</th>
                <td>${member.memDelYn}</td>
            </tr>
            <tr>
                <td colspan="2">
                    <a href="memberList.wow" class="btn btn-info btn-sm">
                        <span class="glyphicon glyphicon-list" aria-hidden="true"></span>
                        &nbsp;목록으로
                    </a>
                    <button type="submit" class="btn btn-primary">
                        <span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
                        &nbsp;&nbsp; 저장
                    </button>

                    <button type="submit" formaction="memberDelete.wow" class="btn btn-danger btn-sm">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                        &nbsp;&nbsp; 삭제
                    </button>


                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
<%--</c:if>--%>

</body>
</html>