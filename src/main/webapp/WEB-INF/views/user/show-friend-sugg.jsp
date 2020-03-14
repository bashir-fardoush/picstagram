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
          href="${pageContext.request.contextPath }/css/mainstyle.css" type="text/css"/>
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
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="#">Picstagram</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>

        <ul class="nav navbar-nav navbar-right">
            <%--<li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>--%>
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/index">
                    <svg aria-label="Home" fill="#262626" height="22" viewBox="0 0 48 48" width="22">
                        <path d="M45.3 48H30c-.8 0-1.5-.7-1.5-1.5V34.2c0-2.6-2-4.6-4.6-4.6s-4.6 2-4.6 4.6v12.3c0 .8-.7 1.5-1.5 1.5H2.5c-.8 0-1.5-.7-1.5-1.5V23c0-.4.2-.8.4-1.1L22.9.4c.6-.6 1.5-.6 2.1 0l21.5 21.5c.4.4.6 1.1.3 1.6 0 .1-.1.1-.1.2v22.8c.1.8-.6 1.5-1.4 1.5zm-13.8-3h12.3V23.4L24 3.6l-20 20V45h12.3V34.2c0-4.3 3.3-7.6 7.6-7.6s7.6 3.3 7.6 7.6V45z"></path>
                    </svg>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/post/write-post">
                    <svg aria-label="write post" fill="#262626" height="22" viewBox="0 0 48 48" width="22">
                        <path clip-rule="evenodd"
                              d="M47.5 46.1l-2.8-11c1.8-3.3 2.8-7.1 2.8-11.1C47.5 11 37 .5 24 .5S.5 11 .5 24 11 47.5 24 47.5c4 0 7.8-1 11.1-2.8l11 2.8c.8.2 1.6-.6 1.4-1.4zm-3-22.1c0 4-1 7-2.6 10-.2.4-.3.9-.2 1.4l2.1 8.4-8.3-2.1c-.5-.1-1-.1-1.4.2-1.8 1-5.2 2.6-10 2.6-11.4 0-20.6-9.2-20.6-20.5S12.7 3.5 24 3.5 44.5 12.7 44.5 24z"
                              fill-rule="evenodd"></path>
                    </svg>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/user/profile"><img alt="profile picture"
                                                                                                src="${pageContext.request.contextPath}${user.avatar}"
                                                                                                width="30px" height="30px"></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout
                    <i class="fa fa-sign-out-alt" style="font-size: 48px" ></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <i class="fa fa-sign">
                        <security:authentication property="principal.username"/>
                    </i>
                </a>
            </li>
        </ul>
    </div>
</nav>

<section class="content">
    <div class="container">
        <%--list of non friends user--%>
        <div class="row">
            <div class="col-lg-6">
                <div class="card card-primary">
                    <div class="card-title p-1">
                        <div class="row">
                            <div class="col-md-8">
                                <span>Suggestions for you</span>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <c:forEach items="${user_list }" var="userFriend">

                            <div  id="suggestion_${userFriend.id}">
                                <div class="media m-0">
                                    <div class="d-flex mr-3">
                                        <a href=""><img class="img-fluid rounded-circle"
                                                        src="${pageContext.request.contextPath }${userFriend.avatar}"
                                                        alt="User"></a>
                                    </div>
                                    <div class="media-body">
                                        <div class="row">
                                            <div class="col-md-8">
                                                <a href="${pageContext.request.contextPath}/user/profile?username=${userFriend.username}" class="m-0">${userFriend.fullName}</a></br>
                                                <small><span><i class="icon ion-md-time"></i>  ${userFriend.followedBy} Follower</span>
                                                </small>
                                            </div>

                                            <div class="col-md-4">
                                                <a onclick="followFriend(${user.id}, ${userFriend.id})">Follow</a></br>

                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <span>${userFriend.bio}</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
