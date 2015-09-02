<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Created by cai on 2015/9/2 13:56. --%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<h1>添加USER</h1>
<form action="${pageContext.request.contextPath}/user/save" method="post">
    id: <input name="id"/><br/>
    username: <input name="username"/><br/>
    password: <input type="password" name="password"/><br/>
    name: <input name="name"/><br/>
    age: <input name="age"/><br/>
    sex: <input name="sex"/><br/>
    <button>submit</button>
</form>
</body>
</html>
