<%--
  Created by IntelliJ IDEA.
  User: bashir
  Date: 3/7/2020
  Time: 4:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>write post</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/css/mainstyle.css" type="text/css"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="#">Picstagram</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

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
        <div class="row">

            <div class="col-sm-12">

                  <form:form action="${pageContext.request.contextPath}/post/write-post" method="post" modelAttribute="userpost" class="form-horizontal" role="form" enctype="multipart/form-data">

                      <div class="form-group">
                          <label class="col-lg-3 control-label">Write something:</label>
                          <div class="col-lg-8">
                              <form:textarea path="postText" class="form-control" type="text" />

                          </div>
                      </div>
                      <div class="form-group">
                          <div class="col-lg-8 col-sm-12">

                              <h3>Select pictures..</h3>
                              <input type="file" name="files" class="form-control" multiple required="true">
                          </div>
                      </div>

                      <div class="form-group">
                          <label class="col-md-3 control-label"></label>
                          <div class="col-md-8">
                              <input type="submit" class="btn btn-primary" value="Post"/>
                              <span></span>
                              <a class="btn btn-warning" href="${pageContext.request.contextPath}/index">Cancel</a>
                          </div>
                          </div>
                      </div>

                  </form:form>

            </div>

        </div>
    </div>
</section>

</body>
</html>
