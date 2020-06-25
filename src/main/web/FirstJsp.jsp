<%--
  Created by IntelliJ IDEA.
  User: lexse
  Date: 07.06.2020
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>First jsp</title>
</head>
<body>
    <h1>Testing jsp</h1>
        <p>
            <%@page import="java.util.Date, logic.TestClass" %>
            <%= "FirstJsp starting.."%>
            <%= "<p>" + "Hello world! " + new Date() + "</p>"%>
            <%TestClass testClass = new TestClass(); %>
            <%= testClass.getInfo()%>
            <%String name = request.getParameter("name"); %>
            <%= "<p>" +"hi "  + name + "</p>"%>
        </p>
</body>
</html>
