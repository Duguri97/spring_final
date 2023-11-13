<%@page import="com.study.free.vo.FreeBoardVO" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.Connection" %>
<%@page import="java.sql.SQLException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/inc/header.jsp" %>
    <title>자유게시판 - 글 보기</title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>

<div class="container">
    <div class="page-header">
        <h3>
            자유게시판 - <small>글 보기</small>
        </h3>
    </div>
    <table class="table table-striped table-bordered">
        <tbody>
        <tr>
            <th>글번호</th>
            <td>${freeBoard.boNo }</td>
        </tr>
        <tr>
            <th>글제목</th>
            <td>${freeBoard.boTitle }</td>
        </tr>
        <tr>
            <th>글분류</th>
            <td>${freeBoard.boCategoryNm }</td>
        </tr>
        <tr>
            <th>작성자명</th>
            <td>${freeBoard.boWriter }</td>
        </tr>
        <!-- 비밀번호는 보여주지 않음  -->
        <tr>
            <th>내용</th>
            <td><textarea rows="10" name="boContent" class="form-control input-sm" readonly="readonly">
                ${freeBoard.boContent }
            </textarea></td>
        </tr>

        <tr>
            <th>조회수</th>
            <td>${freeBoard.boHit }</td>
        </tr>
        <tr>
            <th>최근등록일자</th>
            <td>${freeBoard.boRegDate}</td>
        </tr>
        <tr>
            <th>삭제여부</th>
            <td>${freeBoard.boDelYn }</td>
        </tr>

        <tr>
            <th>첨부파일</th>
            <td>
                <c:forEach var="f" items="${freeBoard.attaches}" varStatus="st">
                    <div> 파일 ${st.count} <a href="<c:url value='/attach/download/${f.atchNo}' />" target="_blank">
                        <span class="glyphicon glyphicon-save" aria-hidden="true"></span> ${f.atchOriginalName}
                    </a> Size : ${f.atchFancySize} Down : ${f.atchDownHit}
                    </div>
                </c:forEach>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <div class="pull-left">
                    <a href="freeList.wow" class="btn btn-default btn-sm"> <span class="glyphicon glyphicon-list"
                                                                                 aria-hidden="true"></span> &nbsp;&nbsp;목록
                    </a>
                </div>
                <div class="pull-right">
                    <a href="freeEdit.wow?boNo=${freeBoard.boNo }" class="btn btn-success btn-sm"> <span
                            class="glyphicon glyphicon-pencil" aria-hidden="true"></span> &nbsp;&nbsp;수정
                    </a>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<!-- container -->


<div class="container">
    <!-- reply container -->
    <!-- // START : 댓글 등록 영역  -->
    <div class="panel panel-default">
        <div class="panel-body form-horizontal">
            <form name="frm_reply" action="<c:url value='/reply/replyRegist' />"
                  method="post" onclick="return false;">
                <input type="hidden" name="reParentNo" value="${freeBoard.boNo}">
                <input type="hidden" name="reCategory" value="FREE"> <input
                    type="hidden" name="reMemId" value="${USER_INFO.userId }">
                <input type="hidden" name="reIp"
                       value="<%=request.getRemoteAddr()%>">
                <div class="form-group">
                    <label class="col-sm-2  control-label">댓글</label>
                    <div class="col-sm-8">
							<textarea rows="3" name="reContent" class="form-control"
                            ${USER_INFO ==null ? "readonly='readonly'" : ""}  ></textarea>
                    </div>
                    <div class="col-sm-2">
                        <button id="btn_reply_regist" type="button"
                                class="btn btn-sm btn-info">등록
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!-- // END : 댓글 등록 영역  -->


    <!-- // START : 댓글 목록 영역  -->
    <div id="id_reply_list_area">
        <div class="row">
            <div class="col-sm-2 text-right">홍길동</div>
            <div class="col-sm-6">
                <pre>내용</pre>
            </div>
            <div class="col-sm-2">12/30 23:45</div>
            <div class="col-sm-2">
                <button name="btn_reply_edit" type="button"
                        class=" btn btn-sm btn-info" >수정
                </button>
                <button name="btn_reply_delete" type="button"
                        class="btn btn-sm btn-danger">삭제
                </button>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-2 text-right">그댄 먼곳만 보네요</div>
            <div class="col-sm-6">
                <pre> 롤링롤링롤링롤링</pre>
            </div>
            <div class="col-sm-2">11/25 12:45</div>
            <div class="col-sm-2"></div>
        </div>
    </div>

    <div class="row text-center" id="id_reply_list_more">
        <a id="btn_reply_list_more"
           class="btn btn-sm btn-default col-sm-10 col-sm-offset-1"> <span
                class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
            더보기
        </a>
    </div>
    <!-- // END : 댓글 목록 영역  -->


    <!-- START : 댓글 수정용 Modal -->
    <div class="modal fade" id="id_reply_edit_modal" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <form name="frm_reply_edit"
                      action="<c:url value='/reply/replyModify' />" method="post"
                      onclick="return false;">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">×</button>
                        <h4 class="modal-title">댓글수정</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="reNo" value="">
                        <textarea rows="3" name="reContent" class="form-control"></textarea>
                        <input type="hidden" name="reMemId" value="${USER_INFO.userId }">
                    </div>
                    <div class="modal-footer">
                        <button id="btn_reply_modify" type="button"
                                class="btn btn-sm btn-info">저장
                        </button>
                        <button type="button" class="btn btn-default btn-sm"
                                data-dismiss="modal">닫기
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- END : 댓글 수정용 Modal -->

</div>
<!-- reply container -->


</body>
<script type="text/javascript">
    var params = {
        "curPage": 1, "rowSizePerPage": 10
        , "reCategory": "FREE", "reParentNo": ${freeBoard.boNo}
    };

    //ajax 요청해서 댓글리스트를 받아오는 함수.
    function fn_reply_list() {
        $.ajax({
            url: '/reply/replyList.wow'
            , type: 'GET'
            , data: params
            , success: function (data) {
                //Map<String,Object>가 json형태로 변환돼서 옴 ==>data
                $.each(data.data, function (i, reply) {
                    let str = '<div class="row"  data-re-no="' + reply.reNo + '" >'  //reNo => re-no
                        + '<div class="col-sm-2 text-right">' + reply.reMemName + '</div>'
                        + '<div class="col-sm-6">'
                        + '<pre>' + reply.reContent + '</pre>'
                        + '</div>'
                        + '<div class="col-sm-2">' + reply.reRegDate + '</div>'
                        + '<div class="col-sm-2">';
                    if (reply.reMemId == '${USER_INFO.userId}') {
                        str = str + '<button name="btn_reply_edit" type="button"'
                            + 'class=" btn btn-sm btn-info">수정</button> '
                            + ' <button name="btn_reply_delete" type="button" '
                            + 'class="btn btn-sm btn-danger">삭제</button> ';
                    }
                    str = str + ' </div>'
                        + '</div>';

                    $("#id_reply_list_area").append(str);
                });
                params.curPage += 1;
            }
        });//ajax
    }//function fn_reply_list

    $(document).ready(function () { //documnet가 준비될 때
        fn_reply_list();

        //더보기 버튼
        $("#id_reply_list_more").on("click", function (e) {
            fn_reply_list();
        });

        //등록버튼
        $("#btn_reply_regist").on("click", function (e) {
            // form태그안에 input hidden으로 필요한거 넣기
            //가장가까운 form찾은 후 ajax 호출(data는 form.serialize(), )
            //성공 : 등록 글 내용부분 지우기,  댓글영역초기화( list_area.html('), curPage=1, fn_reply_list)
            //실패 : error : req.status==401이면 login으로   location.href
            e.preventDefault();
            $form = $(this).closest("form[name='frm_reply']");
            $.ajax({
                url: "/reply/replyRegist.wow"
                , type: "POST"
                , dataType: "JSON"
                , data: $form.serialize()
                , success: function (data) {
                    console.log(data);
                    $form.find("textarea[name='reContent']").val('');
                    $("#id_reply_list_area").html('');
                    params.curPage = 1;
                    fn_reply_list();
                }
                , error: function (req, st, err) {
                    if (req.status == 401) {
                        location.href = "/login/login.wow";
                    }
                }
            });//ajax

        });//등록버튼


        //수정버튼 : 댓글 영역안에 있는 수정버튼만  이벤트 등록
        $("#id_reply_list_area").on("click", 'button[name="btn_reply_edit"]'
            , function (e) {
                //modal 아이디=id_reply_edit_modal
                //현재 버튼의  상위 div(하나의 댓글 전체)를 찾으세요
                // 그 div에서 현재 댓글의 내용 =modal에 있는 textarea에 복사
                // 그 div태그의 data-re-no에 있는 값   $().data('re-no')
                //=modal에 있는  < input name=reNo>태그에 값으로 복사
                //2개 복사했으면   $('#id_reply_edit_modal').modal('show')
                $btn = $(this);  //수정버튼
                $div = $btn.closest('div.row');   //버튼의 댓글 div
                $modal = $('#id_reply_edit_modal'); //modal div
                $pre = $div.find('pre');
                var content = $pre.html();
                $textarea = $modal.find('textarea');
                $textarea.val(content);
                var reNo = $div.data('re-no');
                $modal.find('input[name=reNo]').val(reNo);
                $modal.modal('show');
            });//수정버튼


        //모달창 저장 버튼
        $("#btn_reply_modify").on("click", function (e) {
            e.preventDefault();
            $form = $(this).closest('form[name="frm_reply_edit"]');
            $.ajax({
                url: "/reply/replyModify.wow"
                , type: "POST"
                , data: $form.serialize()
                , dataType: "JSON"
                , success: function (data) {
                    $modal = $('#id_reply_edit_modal');
                    $modal.modal('hide');

                    var reNo = $modal.find('input[name=reNo]').val();
                    var reContent = $modal.find('textarea').val();
                    $("#id_reply_list_area").find("div[data-re-no='" + reNo + "']").find("pre").html(reContent);
                }
            });//ajax
        });//모달창 저장버튼


        //삭제버튼
        $("#id_reply_list_area").on("click", 'button[name="btn_reply_delete"]'
            , function (e) {
                e.preventDefault();
                $div = $(this).closest('.row');
                reNo = $div.data('re-no');
                reMemId = "${USER_INFO.userId}";
                $.ajax({
                    url: "/reply/replyDelete.wow"
                    , type: "POST"
                    , data: {"reNo": reNo, "reMemId": reMemId}
                    , dataType: 'JSON'
                    , success: function () {
                        $div.remove();
                    }
                });//ajax
            }); //삭제버튼
    });
</script>


</html>