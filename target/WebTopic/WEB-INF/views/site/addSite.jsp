<%--
  Created by IntelliJ IDEA.
  User: shun-minchang
  Date: 2018/3/11
  Time: 上午3:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script
                src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha256-3edrmyuQ0w65f8gfBsqowzjJe2iM6n0nKciPUp8y+7E="
                crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
                integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
                crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
                integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
                crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
              crossorigin="anonymous">
        <title>Title</title>
    </head>
    <body class="container">
        <h3>新增站點</h3>
        <hr>
        <form:form action="/site/addSiteP" method="post" modelAttribute="site" role="form">
            <div class="form-group">
                <a href=".." role="button" class="btn btn-sm btn-light col-sm-1">返回</a>
                <button type="submit" class="btn btn-sm btn-success col-sm-1">新增</button>
            </div>
            <div class="form-group">
                <label for="name">站點名稱:</label>
                <input type="text" class="form-control col-sm-2" id="name" name="name" placeholder="請輸入站點名稱" required/>
            </div>
        </form:form>
    </body>
</html>
