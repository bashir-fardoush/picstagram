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
    <title>Registration</title>
    <link href="https://fonts.googleapis.com/css?family=Indie+Flower|Overpass+Mono" rel="stylesheet"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/css/login.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jquery.js"></script>
</head>
<body>

<div id="wrapper" style="height: 70%">
    <div class="main-content" style="height: 60%">
        <div class="header">
            <img src="https://i.imgur.com/zqpwkLQ.png" />
        </div>
        <form:form action="${pageContext.request.contextPath}/register"  modelAttribute="user" method="POST" >
            <div class="l-part">
                <form:input  path="fullName" type="text"  placeholder="Full name" class="input-1" />
                <form:input  path="username" type="text" placeholder="User name" class="input-1" />
                <form:input  path="email"   type="email"  placeholder="Email address" class="input-1" />
                <form:input  path="phone" type="tel" placeholder="Phone number" pattern="[0-9]{11}" class="input-1" />
                <form:input  path="password" type="password"  placeholder="password" class="input-1" />
                <input type="submit" value="Sign Up" class="btn btn-info" />
            </div>
        </form:form>

    </div>
    <div class="sub-content">
        <div class="s-part">
            Already have an account?<a href="${pageContext.request.contextPath}/login">Log In</a>
        </div>
    </div>
</div>

</body>

</html>
