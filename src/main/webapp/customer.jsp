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
    <style>
      table {
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 100%;
      }

      td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
      }

      tr:nth-child(even) {
        background-color: #dddddd;
      }
    </style>
</head>
<body>
Message:
<p>${message}</p>
<table>
    <thead>Customers</thead>
    <tr><th>id</th><th>name</th></tr>
<c:forEach items="${customers}" var="customer">
    <tr>
        <td>${customer.id}</td>
        <td>${customer.name}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
