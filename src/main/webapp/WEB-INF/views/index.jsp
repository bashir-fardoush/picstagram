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
<!DOCTYPE html>
<html>
<head>
    <title>Picstagram</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/css/style.css" type="text/css"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jquery.js"></script>


</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Picstagram</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">

        <form class="form-inline my-2 my-lg-0 align-self-center">
            <input class="form-control mr-sm-2" type="search" placeholder="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>

        <ul class="navbar-nav mr-auto mt-2 mt-lg-0 float-right">

            <li class="nav-item active">
                <a class="nav-link" href="#"><svg aria-label="Home" fill="#262626" height="22" viewBox="0 0 48 48" width="22"><path d="M45.3 48H30c-.8 0-1.5-.7-1.5-1.5V34.2c0-2.6-2-4.6-4.6-4.6s-4.6 2-4.6 4.6v12.3c0 .8-.7 1.5-1.5 1.5H2.5c-.8 0-1.5-.7-1.5-1.5V23c0-.4.2-.8.4-1.1L22.9.4c.6-.6 1.5-.6 2.1 0l21.5 21.5c.4.4.6 1.1.3 1.6 0 .1-.1.1-.1.2v22.8c.1.8-.6 1.5-1.4 1.5zm-13.8-3h12.3V23.4L24 3.6l-20 20V45h12.3V34.2c0-4.3 3.3-7.6 7.6-7.6s7.6 3.3 7.6 7.6V45z"></path></svg></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><svg aria-label="Find People" fill="#262626" height="22" viewBox="0 0 48 48" width="22"><path clip-rule="evenodd" d="M24 .5C37 .5 47.5 11 47.5 24S37 47.5 24 47.5.5 37 .5 24 11 .5 24 .5zm0 44c11.3 0 20.5-9.2 20.5-20.5S35.3 3.5 24 3.5 3.5 12.7 3.5 24 12.7 44.5 24 44.5zm-4.4-23.7c.3-.5.7-.9 1.2-1.2l14.8-8.1c.3-.1.6-.1.8.1.2.2.3.5.1.8l-8.1 14.8c-.3.5-.7.9-1.2 1.2l-14.8 8.1c-.3.1-.6.1-.8-.1-.2-.2-.3-.5-.1-.8l8.1-14.8zm6.2 5l4.3-7.8-7.8 4.3 3.5 3.5z" fill-rule="evenodd"></path></svg></a>
            </li>
             <li class="nav-item">
                <a class="nav-link" href="#"><svg aria-label="Activity Feed"  fill="#262626" height="22" viewBox="0 0 48 48" width="22"><path clip-rule="evenodd" d="M34.3 3.5C27.2 3.5 24 8.8 24 8.8s-3.2-5.3-10.3-5.3C6.4 3.5.5 9.9.5 17.8s6.1 12.4 12.2 17.8c9.2 8.2 9.8 8.9 11.3 8.9s2.1-.7 11.3-8.9c6.2-5.5 12.2-10 12.2-17.8 0-7.9-5.9-14.3-13.2-14.3zm-1 29.8c-5.4 4.8-8.3 7.5-9.3 8.1-1-.7-4.6-3.9-9.3-8.1-5.5-4.9-11.2-9-11.2-15.6 0-6.2 4.6-11.3 10.2-11.3 4.1 0 6.3 2 7.9 4.2 3.6 5.1 1.2 5.1 4.8 0 1.6-2.2 3.8-4.2 7.9-4.2 5.6 0 10.2 5.1 10.2 11.3 0 6.7-5.7 10.8-11.2 15.6z" fill-rule="evenodd"></path></svg></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/post/write-post">
                  <svg aria-label="write post" fill="#262626" height="22" viewBox="0 0 48 48" width="22"><path clip-rule="evenodd" d="M47.5 46.1l-2.8-11c1.8-3.3 2.8-7.1 2.8-11.1C47.5 11 37 .5 24 .5S.5 11 .5 24 11 47.5 24 47.5c4 0 7.8-1 11.1-2.8l11 2.8c.8.2 1.6-.6 1.4-1.4zm-3-22.1c0 4-1 7-2.6 10-.2.4-.3.9-.2 1.4l2.1 8.4-8.3-2.1c-.5-.1-1-.1-1.4.2-1.8 1-5.2 2.6-10 2.6-11.4 0-20.6-9.2-20.6-20.5S12.7 3.5 24 3.5 44.5 12.7 44.5 24z" fill-rule="evenodd"></path></svg>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/user/profile"><img alt="profile picture"  src="${pageContext.request.contextPath}/images/avatar/${user.avatar}" width="30px" height="30px"></a>
            </li>

        </ul>


    </div>
</nav>

<section class="content">
    <div class="container">

        <c:forEach items="${post_list }" var="posts">


            <div class="row">

                <div class="card">

                    <%--Post Woner info --%>

                    <div class="row">
                        <div class="col-lg-1 text-center">
                            <img src="${pageContext.request.contextPath }/images/avatar/defaultavatar.jpg" alt="post image" width="20px" height="20px"/>
                        </div>
                        <div class="col-lg-11 float-left">
                            <table>
                                <tr>
                                    <td>
                                        <p>${posts.user.fullName}</p>
                                    </td>

                                </tr>
                            </table>
                        </div>
                    </div>
                    <%-- END Post Woner info --%>

                    <%--post section--%>
                    <div class="row">
                        <c:forEach items="${posts.postImages}" var="postImage">
                            <img class="card-img-top" src="${pageContext.request.contextPath }${postImage}" alt="post image"/>

                        </c:forEach>
                        <%--<img class="card-img-top" src="${pageContext.request.contextPath }/images/post_images/..." width="100px" height="100px" alt="post image">--%>
                        <div class="card-body">
                            <p class="card-text">${posts.postText}</p>
                            <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                        </div>
                    </div>

                    <%--post section END --%>

                    <%--reaction row--%>
                    <div class="row">
                        <div class="col-sm-12">
                            <a href="${pageContext.request.contextPath}/post/like"><img src="/images/avatar/defaultavatar.jpg" width="15dp" height="15dp"/></a>
                        </div>
                    </div>
                    <%--reaction row END--%>

                        <div class="row">
                                <div class="col-sm-12">

                                    <form:form action="${pageContext.request.contextPath}/post/comment" method="post">
                                        <label for="comment">Comment:</label>
                                        <textarea class="form-control" rows="1" id="comment"></textarea>
                                        <input name="submit" type="submit" value="Submit"/>
                                    </form:form>
                                </div>
                        </div>

                </div> <%--card end--%>


            </div>

            </br>

        </c:forEach>

        </div>

</section>

</body>
</html>
