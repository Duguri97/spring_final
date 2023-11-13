<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/inc/header.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/inc/top.jsp" %>
<div class="container">
    <h3>회원가입</h3>
    <form action="memberRegist.wow" method="post">
        <table class="table table-striped table-bordered">
            <tbody>
            <tr>
                <th>아이디</th>
                <td><input type="text" name="memId" class="form-control input-sm"
                           required="required" pattern="\w{4,}" title="알파벳과 숫자로 4글자 이상 입력">
                    <button type="button" id="idCheck">중복확인</button>
                </td>
            </tr>
            <tr>
                <th>비밀번호</th>
                <td><input type="password" name="memPass" class="form-control input-sm"
                           required="required" pattern="\w{4,}" title="알파벳과 숫자로 4글자 이상 입력"></td>
            </tr>
            <tr>
                <th>회원명</th>
                <td><input type="text" name="memName" class="form-control input-sm"
                           required="required" pattern="[가-힣]{2,}" title="한글로 2글자 이상 입력">
                </td>
            </tr>
            <tr>
                <th>우편번호</th>
                <td><input type="text" name="memZip" class="form-control input-sm"></td>
            </tr>
            <tr>
                <th>주소</th>
                <td><input type="text" name="memAdd1" class="form-control input-sm">
                    <input type="text" name="memAdd2" class="form-control input-sm">
                </td>
            </tr>
            <tr>
                <th>생일</th>
                <td><input type="date" name="memBir" class="form-control input-sm"></td>
            </tr>
            <tr>
                <th>메일</th>
                <td><input type="email" name="memMail" class="form-control input-sm">
                    <button id="email" type="button">이메일 확인</button>
                </td>
            </tr>
            <tr>
                <th>핸드폰</th>
                <td><input type="tel" name="memHp" class="form-control input-sm"></td>
            </tr>
            <tr>
                <th>직업</th>
                <td>
                    <select name="memJob" class="form-control input-sm" required="required">
                        <option value="">-- 직업 선택 --</option>
                        <c:forEach items="${jobList}" var="job">
                            <option value="${job.commCd}">${job.commNm}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th>취미</th>
                <td>
                    <select name="memHobby" class="form-control input-sm" required="required">
                        <option value="">-- 취미 선택 --</option>
                        <c:forEach items="${hobbyList}" var="hobby">
                            <option value="${hobby.commCd}">${hobby.commNm}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit" class="btn btn-primary">
                        <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                        &nbsp;회원가입
                    </button>
                    <a href="memberList.wow" class="btn btn-info btn-sm">
                        <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
                        &nbsp;목록
                    </a>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

</body>

<script>
    let isIdChecked = false;
    $("input[name='memId']").on("change", function (e) {
        isIdChecked = false;
    });
    // idCheck
    $("#idCheck").on("click", function (e) {
        // 중복확인버튼 눌렀는데 id가 빈값일 경우.. id 길이가 3자 이상이 아니면 중복확인버튼 활성화 x
        // 중복확인버튼을 눌렀는데 id가 빈값일떄 중복확인 버튼 누르고, 회원가입하다 아이디를 바꿖을경우
        // 다시 중복확인버튼 누르게 해야된다. 중복확인이 안되어있는데 회원가입 버튼 누르면 아이디 확인하세요 뜨도록.

        // id 길이나 뭐 한글허용은 상관없음.. html 정규표현식 이용하기.
        let curId = $("input[name='memId']").val();
        $.ajax({
            url: "/join/idCheck.wow"
            , type: "get"
            , data: {"id": curId}
            , success: function (data) {
                alert(data);
                isIdChecked = true;
            }
            , error: function (err) {
                alert("에러발생");
            }
        }); // ajax
    }); // 중복 확인 버튼


    //     이메일 인증버튼
    $("#email").on("click", function (e) {
        let mailAddress = $("input[name='memMail']").val();
        $.ajax({
            url: "/join/emailSend.wow"
            , type: "get"
            , data: {"email": mailAddress}
            , success: function (data) {
                alert("메일을 확인하시오.");
                //     시간제한 + 인증번호
                //     랜덤값 6개를 DB에 저장해서
            }
            , error: function (err) {
                alert("에러발생");
            }
        });
    }); // 이메일 인증버튼


</script>
</html>