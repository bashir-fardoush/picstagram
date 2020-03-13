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
                              <input type="file" name="files" class="form-control" multiple>
                          </div>
                      </div>

                      <div class="form-group">
                          <label class="col-md-3 control-label"></label>
                          <div class="col-md-8">
                              <input type="submit" class="btn btn-primary" value="Post" disabled/>
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

<script>

    $(document).ready(
        function(){
            $('input:file').change(
                function(){
                    if ($(this).val()) {
                        $('input:submit').attr('disabled',false);
                        // or, as has been pointed out elsewhere:
                        // $('input:submit').removeAttr('disabled');
                    }
                }
            );
        });
</script>
</body>
</html>
