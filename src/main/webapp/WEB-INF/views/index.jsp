<%--
  Created by IntelliJ IDEA.
  User: bashir
  Date: 3/4/2020
  Time: 11:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Picstagram</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/css/home.css" type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Rokkitt" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jquery.js"></script>


</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Picstagram</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
            aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">

        <form class="form-inline my-2 my-lg-0 align-self-center">
            <input class="form-control mr-sm-2" type="search" placeholder="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>

        <ul class="navbar-nav mr-auto mt-2 mt-lg-0 float-right">

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
                                                                                                src="${pageContext.request.contextPath}/images/avatar/${user.avatar}"
                                                                                                width="30px"
                                                                                                height="30px"></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <svg aria-label="Find People" fill="#262626" height="22" viewBox="0 0 48 48" width="22">
                        <path clip-rule="evenodd"
                              d="M24 .5C37 .5 47.5 11 47.5 24S37 47.5 24 47.5.5 37 .5 24 11 .5 24 .5zm0 44c11.3 0 20.5-9.2 20.5-20.5S35.3 3.5 24 3.5 3.5 12.7 3.5 24 12.7 44.5 24 44.5zm-4.4-23.7c.3-.5.7-.9 1.2-1.2l14.8-8.1c.3-.1.6-.1.8.1.2.2.3.5.1.8l-8.1 14.8c-.3.5-.7.9-1.2 1.2l-14.8 8.1c-.3.1-.6.1-.8-.1-.2-.2-.3-.5-.1-.8l8.1-14.8zm6.2 5l4.3-7.8-7.8 4.3 3.5 3.5z"
                              fill-rule="evenodd"></path>
                    </svg>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <svg aria-label="Activity Feed" fill="#262626" height="22" viewBox="0 0 48 48" width="22">
                        <path clip-rule="evenodd"
                              d="M34.3 3.5C27.2 3.5 24 8.8 24 8.8s-3.2-5.3-10.3-5.3C6.4 3.5.5 9.9.5 17.8s6.1 12.4 12.2 17.8c9.2 8.2 9.8 8.9 11.3 8.9s2.1-.7 11.3-8.9c6.2-5.5 12.2-10 12.2-17.8 0-7.9-5.9-14.3-13.2-14.3zm-1 29.8c-5.4 4.8-8.3 7.5-9.3 8.1-1-.7-4.6-3.9-9.3-8.1-5.5-4.9-11.2-9-11.2-15.6 0-6.2 4.6-11.3 10.2-11.3 4.1 0 6.3 2 7.9 4.2 3.6 5.1 1.2 5.1 4.8 0 1.6-2.2 3.8-4.2 7.9-4.2 5.6 0 10.2 5.1 10.2 11.3 0 6.7-5.7 10.8-11.2 15.6z"
                              fill-rule="evenodd"></path>
                    </svg>
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">
                    <svg aria-label="Logout" fill="#262626" height="22" viewBox="0 0 48 48" width="22">
                        <path clip-rule="evenodd"
                              d="M34.3 3.5C27.2 3.5 24 8.8 24 8.8s-3.2-5.3-10.3-5.3C6.4 3.5.5 9.9.5 17.8s6.1 12.4 12.2 17.8c9.2 8.2 9.8 8.9 11.3 8.9s2.1-.7 11.3-8.9c6.2-5.5 12.2-10 12.2-17.8 0-7.9-5.9-14.3-13.2-14.3zm-1 29.8c-5.4 4.8-8.3 7.5-9.3 8.1-1-.7-4.6-3.9-9.3-8.1-5.5-4.9-11.2-9-11.2-15.6 0-6.2 4.6-11.3 10.2-11.3 4.1 0 6.3 2 7.9 4.2 3.6 5.1 1.2 5.1 4.8 0 1.6-2.2 3.8-4.2 7.9-4.2 5.6 0 10.2 5.1 10.2 11.3 0 6.7-5.7 10.8-11.2 15.6z"
                              fill-rule="evenodd"></path>
                    </svg>
                </a>
            </li>


        </ul>


    </div>
</nav>

<section class="hero">
    <div class="container-fluid">

        <div class="row">
            <%--post section--%>
            <div class="col-lg-6 offset-lg-3">
                <c:forEach items="${post_list }" var="posts">
                    <div class="row">
                        <div>
                            <div class="cardbox shadow-lg bg-white">
                                <div class="cardbox-heading">
                                    <!-- START dropdown-->
                                    <div class="dropdown float-right">
                                        <button class="btn btn-flat btn-flat-icon" type="button" data-toggle="dropdown"
                                                aria-expanded="false">
                                            <em class="fa fa-ellipsis-h"></em>
                                        </button>
                                        <div class="dropdown-menu dropdown-scale dropdown-menu-right" role="menu"
                                             style="position: absolute; transform: translate3d(-136px, 28px, 0px); top: 0px; left: 0px; will-change: transform;">
                                            <a class="dropdown-item" href="#">Hide post</a>
                                            <a class="dropdown-item" href="#">Stop following</a>
                                            <a class="dropdown-item" href="#">Report</a>
                                        </div>
                                    </div><!--/ dropdown -->
                                    <div class="media m-0">
                                        <div class="d-flex mr-3">
                                            <a href=""><img class="img-fluid rounded-circle"
                                                            src="${pageContext.request.contextPath }${posts.user.avatar}"
                                                            alt="User"></a>
                                        </div>
                                        <div class="media-body">
                                            <p class="m-0">${posts.user.fullName}</p>
                                            <small><span><i class="icon ion-md-pin"></i> Dhaka, Banagladesh</span>
                                            </small>
                                            <small><span><i class="icon ion-md-time"></i> 10 min ago</span></small>
                                        </div>
                                    </div><!--/ media -->
                                </div><!--/ cardbox-heading -->
                                <div class="cardbox-item ml-3">
                                    </hr>
                                    <h3>${posts.postText}</h3>
                                    </hr>

                                </div>

                                <div class="cardbox-item">
                                    <c:forEach items="${posts.postImages}" var="postImage">
                                        <div>
                                            <img class="img-fluid" src="${pageContext.request.contextPath }${postImage}"
                                                 alt="Image">
                                        </div>
                                    </c:forEach>


                                </div><!--/ cardbox-item -->
                                <div class="cardbox-base">
                                    <c:set var="colorAttr" value="#8d8d8d"/>
                                    <c:if test="${posts.isLiked}">
                                        <c:set var="colorAttr" value="#44d0b0"/>
                                    </c:if>

                                    <ul class="float-right">
                                        <li><a><i class="fa fa-comments"></i></a></li>
                                        <li><a><em class="mr-5">${posts.totalComment}</em></a></li>
                                            <li><a><i class="fa fa-share-alt"></i></a></li>
                                        <li><a><em class="mr-3">03</em></a></li>
                                    </ul>
                                    <ul>
                                        <li><a>
                                            <i onclick="addLike(${posts.id},${user.id},this)" class="fa fa-thumbs-up"
                                               style="color: ${colorAttr}"></i></a></li>
                                        <li><a><span> ${posts.totalLike} Likes</span></a></li>
                                    </ul>
                                </div><!--/ cardbox-base -->
                                <div class="cardbox-comments">
			                      <span class="comment-avatar float-left">
                                      <a href=""><img class="rounded-circle"  src="${pageContext.request.contextPath}${user.avatar}" alt="..."></a>
                                  </span>
                                    <div class="search">
                                        <input id="comment_${posts.id}" placeholder="Write a comment" type="text">
                                        <button onclick="addComment(${posts.id},${user.id})"><i class="fa fa-telegram-plane"></i>
                                        </button>
                                    </div><!--/. Search -->

                                        <%--show comment list--%>
                                    <div class="text-left" id="comments_${posts.id}">
                                        <c:forEach items="${posts.comments}" var="comment">
                                            <div class="media m-0">
                                                <div class="d-flex mr-3">
                                                    <a href=""><img class="img-fluid rounded-circle"
                                                                    src="${pageContext.request.contextPath }${comment.woner.avatar}"
                                                                    alt="User"></a>
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


                                </div><!--/ cardbox-like -->

                            </div><!--/ cardbox -->

                        </div><!--/ col-lg-6 -->
                    </div>
                    <!--/ row -->
                </c:forEach>
            </div>
                <%--END post section--%>


            <div class="col-lg-3 col-sm-12">

                <div class="cardbox shadow-lg bg-white">
                    <h4>Friend suggestion for you</h4>

                    <c:forEach items="${user_list }" var="userFriend">
                        <div class="row" id="suggestion_${userFriend.id}">
                            <div class="col-sm-12 cardbox-heading ml-1" >
                                <a class="btn btn-success float-right" onclick="addFriend(${user.id}, ${userFriend.id})"><i class="fa fa-user-plus"></i></a>

                                <div class="media m-0">
                                    <div class="d-flex mr-3">
                                        <a href=""><img class="img-fluid rounded-circle"
                                                        src="${pageContext.request.contextPath }${userFriend.avatar}"
                                                        alt="User"></a>
                                    </div>
                                    <div class="media-body">
                                        <p class="m-0">${userFriend.fullName}</p>
                                        <small><span><i
                                                class="icon ion-md-time"></i> ${userFriend.email}</span>
                                        </small>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </c:forEach>
                </div>



            </div>

        </div>


    </div><!--/ container -->
</section>
<script>

    function addFriend(userId,friendId) {
        console.log(userId);
        console.log(friendId);

        var url = "${pageContext.request.contextPath}/api/v1/user/addfriend";

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

            if (response > 0) {
                // liked state
                $(element).css("color", "#44d0b0");
            } else {
                // unliked state
                $(element).css("color", "#8d8d8d");
            }


        }).fail(function () {

            console.log("failed to save like");
        });

    }


    function addComment(postId, userId) {
        console.log(postId);
        console.log(userId);
        console.log($('#comment_' + postId).val());
        var commentText = $('#comment_' + postId).val();

        var url = "${pageContext.request.contextPath}/api/v1/post/addcomment";


        $.ajax({
            method: "POST",
            url: url,
            data: {postId: postId, userId: userId, commentText: commentText}
        }).done(function (response) {
            console.log(response)
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


        }).fail(function () {

        });


    }


</script>
</body>
</html>
