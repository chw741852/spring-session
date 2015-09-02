<%--
  Created by Cai on 2015/6/10 11:24.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>index</title>
</head>
<body>
<h1>index</h1>
<form action="${pageContext.request.contextPath}/login" method="post">
    name: <input type="text" name="name"><br/>
    pass: <input type="text" name="pass"><br/>
    <input type="submit" value="submit"/>
</form>
<a href="${pageContext.request.contextPath}/user/index">user index</a>
</body>
</html>
