<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Created by cai on 2015/9/2 13:56. --%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<h1>修改USER</h1>
<form action="${pageContext.request.contextPath}/user/update" method="post">
    <input type="hidden" name="id" value="${user.id}"/><br/>
    username: <input name="username" value="${user.username}"/><br/>
    password: <input type="password" value="${user.password}" name="password"/><br/>
    name: <input name="name" value="${user.name}"/><br/>
    age: <input name="age" value="${user.age}"/><br/>
    sex: <input name="sex" value="${user.sex}"/><br/>
    <button>submit</button>
</form>
</body>
</html>
