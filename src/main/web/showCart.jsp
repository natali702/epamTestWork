<%--
  Created by IntelliJ IDEA.
  User: lexse
  Date: 09.06.2020
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show cart</title>
</head>
<body>
        <%@page import="SomePackage.Cart" %>
        <%= "showCart.jsp starting.."%>
        <% Cart cart = (Cart)session.getAttribute("cart"); %>
        <p>Name : <%= cart.getName()%></p>
        <p>Quantity : <%= cart.getQuantity()%></p>
</body>
</html>
