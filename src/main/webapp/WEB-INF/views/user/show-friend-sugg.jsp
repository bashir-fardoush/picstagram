<%--
  Created by IntelliJ IDEA.
  User: bashir
  Date: 3/11/2020
  Time: 3:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add friends</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css"/>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/css/font-awesome.min" type="text/css"/>

    <link href="https://fonts.googleapis.com/css?family=Rokkitt" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jquery.js"></script>
</head>
<body>

<section class="content">
    <div class="container">
        <%--list of non friends user--%>
        <div class="row">
            <div col-sm-12>
                <div class="card" style="margin-top: 10px">
                    <div class="card-header">People you may connect...</div>
                    <div class="card-body">

                        <c:forEach items="${user_list }" var="userFriend">
                            <div class="row" id="suggestion_${userFriend.id}">
                                <div class="col-sm-12 cardbox-heading ml-1" >
                                    <a class="btn btn-success float-right" onclick="followFriend(${user.id}, ${userFriend.id})"><i class="fa fa-user-plus"></i></a>

                                    <div class="media m-0">
                                        <div class="d-flex mr-3">
                                            <a href=""><img class="img-fluid img-md rounded-circle" style="width: 64px; height: 64px"
                                                            src="${pageContext.request.contextPath }${userFriend.avatar}"
                                                            alt="User"></a>
                                        </div>
                                        <div class="media-body">
                                            <p class="m-0">${userFriend.fullName}</p>
                                            <small><span><i
                                                    class="icon ion-md-time"></i> ${userFriend.bio}</span>
                                            </small></br>
                                            <small><span><i
                                                    class="icon ion-md-time"></i> Following: ${userFriend.following}, Followed By: ${userFriend.followedBy}</span>
                                            </small>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <hr style="height:1px;  border:none; color:#fefff3; background-color:rgba(51,51,51,0.19);" />

                        </c:forEach>

                    </div>
                </div>


            </div>
        </div>
        <%-- END list of non friends user--%>

        <%--prev and next section--%>
        <div class="row">
            <div class="col-sm-12">

                <a  class="btn btn-info btn-circle btn-lateral float-left"
                    href="${pageContext.request.contextPath}/user/friendSuggestion?pageId=${pageId}&requestType=prev">
                    <i class="fa fa-angle-left"></i> Prev
                </a>

                <a  class="btn btn-info btn-circle btn-lateral float-right"
                    href="${pageContext.request.contextPath}/user/friendSuggestion?pageId=${pageId}&requestType=next">
                    Next <i class="fa fa-angle-right"></i>
                </a>

            </div>
        </div>
    </div>

</section>

<script>
    function followFriend(userId, friendId) {
        console.log(userId);
        console.log(friendId);

        var url = "${pageContext.request.contextPath}/api/v1/user/followfriend";

        //console.log(this);
        $.ajax({
            method: "POST",
            url: url,
            data: {userId: userId, friendId: friendId}
        }).done(function (response) {

            console.log(response);

            $("#suggestion_"+friendId).remove();


        }).fail(function () {

            console.log("failed to save friend");
        });


    }

</script>

</body>
</html>
