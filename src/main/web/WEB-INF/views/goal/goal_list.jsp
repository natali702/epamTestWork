<%--
  Created by IntelliJ IDEA.
  User: lexse
  Date: 08.07.2020
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Goal list</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <style>
        body, h1, h2, h3, h4, h5, h6 {
            font-family: "Raleway", sans-serif
        }
    </style>
</head>
<body class="w3-light-grey w3-content" style="max-width:1600px">
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
<div class="row">
    <div class="container">
        <h3 class="text-center">List of Goals</h3>
        <hr>
        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/goal/new"
               class="btn btn-success">Add Goal</a>
        </div>
        <br>
        <h2>there are all goals</h2>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Goal id</th>
                <th>Goal title</th>
                <th>Goal parent</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="goal" items="${requestScope.listGoal}">
                <tr>
                    <td><c:out value="${goal.id}"/></td>
                    <td><c:out value="${goal.title}"/></td>
                    <td><c:out value="${goal.parent.title}"/></td>
                    <td><a href="<%=request.getContextPath()%>/goal/edit?id=<c:out value='${goal.id}' />">Edit</a>
                        <a href="<%=request.getContextPath()%>/goal/delete?id=<c:out value='${goal.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
