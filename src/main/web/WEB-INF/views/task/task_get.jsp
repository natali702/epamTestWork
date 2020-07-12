<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Get task</title>
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
<div class="container col-md-5">
    <h2>Information:</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Description</th>
            <th>Username</th>
            <th>Task Date</th>
            <th>Task Status</th>
            <th>Goal</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td><c:out value="${requestScope.task.id}" /></td>
                <td><c:out value="${requestScope.task.title}" /></td>
                <td><c:out value="${requestScope.task.description}" /></td>
                <td><c:out value="${requestScope.task.username}" /></td>
                <td><c:out value="${requestScope.task.taskDate}" /></td>
                <td><c:out value="${requestScope.task.status}" /></td>
                <td><c:out value="${requestScope.task.goal.title}" /></td>
            </tr>
        </tbody>

    </table>
</div>
</body>
</html>
