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
<%@include file="/WEB-INF/inc/top.jsp" %>

<ul class="nav nav-tabs" role="tablist" id="myTab">
  <li role="presentation" class="active"><a href="chProfile" aria-controls="chProfile" role="tab" data-toggle="tab">창희프로필</a></li>
  <li role="presentation"><a href="chPiano" aria-controls="chPiano" role="tab" data-toggle="tab">창희의 피아노</a></li>
</ul>

<div class="tab-content">   <!-- 여기다가 piano나 프로필 내용이 보이게 -->

	
</div>

</body>

<script>
    // chProfile이나 chPiano 눌렀을 때 ajax로 요청해서 html 얻고
    // 그 html을 div class='tab-content'에다가 $(".tab-content").html(얻은html);

    // $.ajax( { url : "chProfile.wow", success : function(data){ $(".tab-content").html(얻은html) }   });
        // ==> $(".tab-content").load("chProfile.wow");
    $(document).ready(function () {
        $("#myTab a").on("click", function (e) {
           // alert($(this).attr("href"));
            let page = $(this).attr("href");
            $(".tab-content").load(page + ".wow")
        }); // a태그 클릭
    }); // ready


</script>



</html>