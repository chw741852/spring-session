<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Created by cai on 2015/9/2 15:15. --%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<h1>User Detail</h1>
id: ${user.id}<br/>
username: ${user.username}<br/>
password: ${user.password}<br/>
name: ${user.name}<br/>
age: ${user.age}<br/>
sex: ${user.sex}<br/>
<a href="${pageContext.request.contextPath}/user/edit?id=${user.id}">修改</a>
</body>
</html>
