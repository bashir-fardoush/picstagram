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
<html>
<head>
    <title>write post</title>
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

<section class="content">
    <div class="container">
        <div class="row">

            <div class="col-sm-12">

                  <form:form action="${pageContext.request.contextPath}/user/write-post" method="post" modelAttribute="post" class="form-horizontal" role="form" enctype="multipart/form-data">

                      <div class="form-group">
                          <label class="col-lg-3 control-label">Bio:</label>
                          <div class="col-lg-8">
                              <form:textarea path="bio" class="form-control" type="text" />
                          </div>
                      </div>
                      <div class="form-group">
                          <div class="col-sm-12 text-center">

                              <h3>Select pictures..</h3>
                              <input type="file" name="file" class="form-control">
                          </div>
                      </div>

                      <div class="form-group">
                          <label class="col-md-3 control-label"></label>
                          <div class="col-md-8">
                              <input type="submit" class="btn btn-primary" value="Post">
                              <span></span>
                              <input type="reset" class="btn btn-default" value="Cancel">
                          </div>
                      </div>

                  </form:form>


            </div>

        </div>
    </div>
</section>

</body>
</html>
