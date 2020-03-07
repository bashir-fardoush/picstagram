<%--
  Created by IntelliJ IDEA.
  User: bashir
  Date: 3/6/2020
  Time: 5:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Profile page</title>
</head>
<body>
<h2>Profile page</h2>
    <h5>${user.username}</h5>
    <h5>${user.fullName}</h5>
    <h5>${user.bio}</h5>
    <h5>${user.email}</h5>
    <h5>${user.phone}</h5>

<a href="${pageContext.request.contextPath}/user/profile-edit">Edit Profile</a>

</body>
</html>
