<%--
  Created by IntelliJ IDEA.
  User: toshihirokuboi
  Date: 2019-05-26
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customers</title>
</head>
Customers
message: ${message}
<table>
<c:forEach items="${customers}" var="customer">
    <tr>
        <td>ID: ${customer.id}</td>
        <td>Name: ${customer.name}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
