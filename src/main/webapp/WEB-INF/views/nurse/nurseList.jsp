<%--
  Created by IntelliJ IDEA.
  User: shun-minchang
  Date: 2018/3/11
  Time: 上午3:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h3>護士列表</h3>
        <hr>
        <div class="form-group">
            <a href=".." role="button" class="btn btn-sm btn-light col-sm-1">返回</a>
        </div>
        <c:if test="${empty nurseList}">
            <div class="alert alert-warning" role="alert">
                <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>User表是空的，請<a
                    href="/nurse/addNurse" role="button" class="btn btn-primary btn-sm">新增</a>
            </div>
        </c:if>
        <c:if test="${!empty nurseList}">
            <table class="table table-bordered table-striped">
                <tr>
                    <th>員工編號</th>
                    <th>姓名</th>
                    <th>更新日期</th>
                    <th>建立日期</th>
                    <th></th>
                </tr>

                <c:forEach items="${nurseList}" var="task">
                    <tr>
                        <td>${task.id}</td>
                        <td>${task.name}</td>
                        <td>${task.updateTime}</td>
                        <td>${task.createTime}</td>
                        <td>
                            <a href="/nurse/show/${task.id}" role="button" class="btn btn-sm btn-success">檢視</a>
                            <a href="/nurse/delete/${task.id}" role="button" class="btn btn-sm btn-danger">刪除</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
