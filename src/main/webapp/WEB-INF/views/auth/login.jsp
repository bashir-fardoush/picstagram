<%--
  Created by IntelliJ IDEA.
  User: bashir
  Date: 3/6/2020
  Time: 11:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>


    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/css/login.css">

</head>
<body>

<c:if test="${ error}">
    <div class="alert alert-danger text-center" role="alert">
       ${errMsg}
    </div>

</c:if>

<div id="wrapper" >
    <div class="main-content" >
        <div class="header ">
            <img src="${pageContext.request.contextPath}/images/picstagram_image.png" />
        </div>
        <form action="${pageContext.request.contextPath}/login-processing" method="POST">
            <div class="l-part">
                <input type="text" name="username" placeholder="Username" class="input-1"  />

                <div class="overlap-text">
                    <input type="password" name="password"  placeholder="Password" class="input-2" />
                    <a href="#">Forgot?</a>
                </div>
                <input type="submit" value="Log in" class="btn btn-info" />
            </div>
        </form>

    </div>
    <div class="sub-content" >
        <div class="s-part">
            Don't have an account?<a href="${pageContext.request.contextPath}/register">Sign up</a>
        </div>
    </div>
</div>




</body>
</html>
