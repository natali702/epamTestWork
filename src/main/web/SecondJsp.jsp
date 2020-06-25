<%--
  Created by IntelliJ IDEA.
  User: lexse
  Date: 07.06.2020
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Second JSP</title>
</head>
<body>
    <h1>"this is from second jsp"</h1>
    <p>
        <%= "SecondJsp starting.."%>
        <%  for(int i = 0;  i< 10; i++){
            out.println("<p>" + "hello motherfuker " + i + "</p>");
        }
            %>

    </p>
</body>
</html>
