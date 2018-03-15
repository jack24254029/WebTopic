<%--
  Created by IntelliJ IDEA.
  User: shun-minchang
  Date: 2018/3/11
  Time: 上午3:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
                integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
                crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
                integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
                crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
              crossorigin="anonymous">
        <script type="application/javascript">
            var submit = function () {
                var nurseId = document.getElementById("id").value;
                var nurseName = document.getElementById("name").value;
                var selectedOptions = document.getElementById("selected").options;
                var selectedSiteIds = [];
                for (var j = 0; j < selectedOptions.length; j++) {
                    selectedSiteIds.push(parseInt(selectedOptions[j].value));
                }
                var json = {
                    nurseId: String(nurseId),
                    nurseName: String(nurseName),
                    selectedSiteIds: selectedSiteIds
                };
                var url = "http://";
                url += $(location).attr('host');
                url += "/nurse/addSiteOfNurse";
                $.ajax({
                    url: url,
                    type: "POST",
                    data: JSON.stringify(json),
                    contentType: "application/json",
                    complete: function () {
                        console.log("complete");
                        window.location.href = "http://" + $(location).attr('host') + "/nurse/nurseList";
                    }
                });
            };
            var addItem = function () {
                $('#unSelected').find('option:selected').remove().appendTo('#selected');
            };
            var removeItem = function () {
                $('#selected').find('option:selected').remove().appendTo('#unSelected');
            };
        </script>
        <title>Title</title>
    </head>
    <body class="container">
        <h3>新增護士</h3>
        <hr>
        <div class="form-group">
            <a href=".." role="button" class="btn btn-sm btn-light col-sm-1">返回</a>
            <button type="button" class="btn btn-sm btn-success col-sm-1" onclick="submit()">新增</button>
        </div>
        <div class="form-group">
            <label for="id">員工編號:</label>
            <input type="text" class="form-control col-sm-2" id="id" name="id" placeholder="請輸入員工編號" required/>
        </div>
        <div class="form-group">
            <label for="name">姓名:</label>
            <input type="text" class="form-control  col-sm-2" id="name" name="name" placeholder="請輸入姓名" required/>
        </div>
        <div class="form-group">
            <label>分配站點</label>
        </div>
        <div>
            <select id="unSelected" class="custom-select  col-sm-2" multiple>
                <c:forEach items="${unSelectedSiteList}" var="site">
                    <option value="${site.id}">${site.name}</option>
                </c:forEach>
            </select>
            <div class="btn-group-vertical">
                <button type="button" class="btn btn-primary" id="add" onclick="addItem()">加入-></button>
                <button type="button" class="btn btn-danger" id="remove" onclick="removeItem()"><-刪除</button>
            </div>
            <select id="selected" class="custom-select col-sm-2" multiple></select>
        </div>
    </body>
</html>
