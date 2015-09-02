<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Created by cai on 2015/9/2 17:25. --%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
vl: ${vl}
vv: ${vv}
vh: ${vh}
vx: ${vs}
<c:forEach items="${vzss}" var="vzs">
    vzs: ${vzs}
</c:forEach>
</body>
</html>
