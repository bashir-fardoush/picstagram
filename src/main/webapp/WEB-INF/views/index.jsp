<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Picstagram</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/css/mainstyle.css" type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Rokkitt" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
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
                <a class="nav-link" href="#">
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
<section class="hero">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-2">

                <c:forEach items="${post_list }" var="posts">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card card-primary">
                                <div class="cardbox card-body">
                                    <div class="cardbox-heading">
                                        <div class="dropdown float-right">
                                            <button class="btn btn-flat btn-flat-icon" type="button"
                                                    data-toggle="dropdown"
                                                    aria-expanded="false">
                                                <em class="fa fa-ellipsis-h"></em>
                                            </button>
                                            <div class="dropdown-menu dropdown-scale dropdown-menu-right" role="menu"
                                                 style="position: absolute; transform: translate3d(-136px, 28px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                <a class="dropdown-item" href="#">Hide post</a>
                                                <a class="dropdown-item" href="#">Stop following</a>
                                                <a class="dropdown-item" href="#">Report</a>
                                            </div>
                                        </div>
                                        <!--/ dropdown -->
                                        <div class="media m-0">
                                            <div class="d-flex mr-3">
                                                <a href="${pageContext.request.contextPath}/user/profile?username=${posts.woner.username}">
                                                    <img class="img-fluid rounded-circle"
                                                         src="${pageContext.request.contextPath }${posts.woner.avatar}"
                                                         alt="User">
                                                </a>
                                            </div>
                                            <div class="media-body">
                                                <p class="m-0">${posts.woner.fullName}</p>
                                                <small><span><i class="icon ion-md-pin"></i> Dhaka, Bangladesh</span>
                                                </small>
                                                <small><span><i class="icon ion-md-time"></i> 10 hours ago</span>
                                                </small>
                                            </div>
                                        </div>
                                        <!--/ media -->
                                    </div>
                                    <!--/ cardbox-heading -->
                                        <%--POST TEXT--%>
                                    <div class="cardbox-item ml-3">
                                        </hr>
                                        <h3>${posts.postText}</h3>
                                        </hr>

                                    </div>

                                    <div class="cardbox-item">
                                        <div class="row">

                                            <c:set var="count" value="0" scope="page" />
                                            <c:forEach items="${posts.postImages}" var="postImage">
                                                <c:set var="count" value="${count + 1}" scope="page"/>
                                            </c:forEach>
                                            <c:if test="${count > 1}">
                                                <c:forEach items="${posts.postImages}" var="postImage">
                                                    <div class="col-md-6 post-items">
                                                        <img class="img-fluid"
                                                             src="${pageContext.request.contextPath }${postImage}"
                                                             alt="Image">
                                                    </div>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${count == 1}">
                                                <c:forEach items="${posts.postImages}" var="postImage">
                                                <div class="col-md-12 post-items">
                                                    <img class="img-fluid"
                                                         src="${pageContext.request.contextPath }${postImage}"
                                                         alt="Image">
                                                </div>
                                                </c:forEach>

                                            </c:if>



                                        </div>

                                    </div>
                                    <!--/ cardbox-item -->
                                    <div class="cardbox-base">
                                        <c:set var="colorAttr" value="#8d8d8d"/>
                                        <c:if test="${posts.isLiked}">
                                            <c:set var="colorAttr" value="#44d0b0"/>
                                        </c:if>

                                        <ul class="float-right">
                                            <li><a><i class="fa fa-comments"></i></a></li>
                                            <li><a><em class="mr-5"
                                                       id="comment_count_${posts.id}">${posts.totalComment}</em></a>
                                            </li>
                                            <li><a><i class="fa fa-share"></i></a></li>
                                            <li><a><em class="mr-3">03</em></a></li>
                                        </ul>
                                        <ul>
                                            <li>
                                                <a>
                                                    <i onclick="addLike(${posts.id},${user.id},this)"
                                                       class="fa fa-thumbs-up"
                                                       style="color: ${colorAttr}">

                                                    </i>
                                                </a>
                                            </li>
                                            <li>
                                                <a>
                                                    <em class="mr-5" id="like_count_${posts.id}">${posts.totalLike}</em>
                                                    Likes
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                    <!--/ cardbox-base -->
                                    <div class="cardbox-comments">
                            <span class="comment-avatar float-left">
                               <a href="#">
                                   <img class="rounded-circle" src="${pageContext.request.contextPath}${user.avatar}"
                                        alt="...">
                               </a>
                             </span>
                                        <div class="search">
                                            <input id="comment_${posts.id}" placeholder="Write a comment" type="text">
                                            <button onclick="addComment(${posts.id},${user.id})">
                                                <span><i class="fa fa-paper-plane"></i></span>
                                            </button>
                                        </div>
                                    </div>

                                        <%--comment listing--%>
                                    <div class="text-left" id="comments_${posts.id}">
                                        <c:forEach items="${posts.comments}" var="comment">
                                            <div class="media m-0">
                                                <div class="d-flex mr-3">
                                                    <a href="">
                                                        <img class="img-fluid rounded-circle"
                                                             src="${pageContext.request.contextPath }${comment.woner.avatar}"
                                                             alt="User">
                                                    </a>
                                                </div>
                                                <div class="media-body">
                                                    <p class="m-0">${comment.commentText}</p>
                                                    <small><span><i
                                                            class="icon ion-md-time"></i> ${comment.woner.fullName}</span>
                                                    </small>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>

                                </div>
                            </div>

                        </div>
                    </div>
                    </br>
                </c:forEach>

            </div>
            <%--END of Posts--%>

            <%--seggestion section--%>
            <div class="col-lg-4">
                <div class="card card-primary">
                    <div class="card-title p-1">
                        <div class="row">
                            <div class="col-md-8">
                                <span>Suggestions for you</span>
                            </div>
                            <div class="col-md-4">
                                <span><a
                                        href="${pageContext.request.contextPath}/user/friendSuggestion">See All</a></span>
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
                                                <a onclick="followFriend(${user.id}, ${userFriend.id})">Follow</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <a  class="btn btn-default btn-circle btn-md btn-lateral float-left"
                            href="${pageContext.request.contextPath}/index?pageId=${pageId}&requestType=prev">
                            <i class="fa fa-angle-left"></i> Prev
                        </a>

                        <a  class="btn btn-default btn-circle btn-md btn-lateral float-right align-bottom"
                            href="${pageContext.request.contextPath}/index?pageId=${pageId}&requestType=next">
                            Next <i class="fa fa-angle-right"></i>
                        </a>
                    </div>
                </div>


            </div>
            <!--/ row -->
        </div>
    </div>
    <!--/ container -->
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
    function addLike(postId, userId, element) {

        var url = "${pageContext.request.contextPath}/api/v1/post/like";

        //console.log(this);
        $.ajax({
            method: "POST",
            url: url,
            data: {postId: postId, userId: userId}
        }).done(function (response) {
            console.log(response);
            console.log("like saved");

            var totalLikeStr = document.getElementById("like_count_"+postId).innerHTML;
            var totalLike = parseInt(totalLikeStr);

            if (response > 0) {
                // liked state
                $(element).css("color", "#44d0b0");
                totalLike++;
            } else {
                // unliked state
                $(element).css("color", "#8d8d8d");
                totalLike--;
            }

            document.getElementById("like_count_"+postId).innerHTML = totalLike;


        }).fail(function () {

            console.log("failed to save like");
        });

    }
    function addComment(postId, userId) {

        var commentText = $('#comment_' + postId).val();
        var url = "${pageContext.request.contextPath}/api/v1/post/addcomment";


        $.ajax({
            method: "POST",
            url: url,
            data: {postId: postId, userId: userId, commentText: commentText}
        }).done(function (response) {

            console.log(response);
            if (response.id <= 0){
                console.log("Invalid comment id")
                return;
            }

            $('#comment_' + postId).val('');

            $('#comments_' + postId).append(" </hr> <div >\n" +
                "                <div class=\"media m-0\">\n" +
                "                 <div class=\"d-flex mr-3\">\n" +
                "                    <a href=\"\"><img class=\"img-fluid rounded-circle\"\n" +
                "                          src=\"${pageContext.request.contextPath }${user.avatar}\"\n" +
                "                     alt=\"User\"></a>\n" +
                "                </div>\n" +
                "\n" +
                "                <div class=\"media-body\">\n" +
                "                  <p class=\"m-0\">" + commentText + "</p>\n" +
                "                     <small><span><i class=\"icon ion-md-pin\"></i>${user.fullName}</span></small>\n" +
                "            </div>\n" +
                "            </div>\n" +
                "            </div>");

            // end of ammend


            var totalCommentStr = document.getElementById("comment_count_"+postId).innerHTML;
            var totalComment = parseInt(totalCommentStr);
            totalComment++;
            document.getElementById("comment_count_"+postId).innerHTML = totalComment;


        }).fail(function () {

        });


    }
</script>
</body>
</html>