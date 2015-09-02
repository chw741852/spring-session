<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Created by cai on 2015/9/2 13:54. --%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<a href="${pageContext.request.contextPath}/user/add">添加</a><br/>
<form action="${pageContext.request.contextPath}/user/get" method="get">
    ID: <input name="id">
    <button>查询</button>
</form>

<form action="${pageContext.request.contextPath}/user/addRedisValue" method="post">
    value: <input name="value">
    <button>添加</button>
</form>
<a href="${pageContext.request.contextPath}/user/getRedisValue">get redis values</a>
</body>
</html>
