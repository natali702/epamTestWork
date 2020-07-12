<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main page</title>
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
        <h3 class="text-center">Task page</h3>
        <hr>
        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/task/new"
               class="btn btn-success">Add Task</a>
        </div>
        <br>
        <h2>there are all tasks</h2>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Task id</th>
                <th>Task title</th>
                <th>Task description</th>
                <th>Task Date</th>
                <th>Task Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="task" items="${requestScope.listTask}">
                <tr>
                    <td><c:out value="${task.id}"/></td>
                    <td><c:out value="${task.title}"/></td>
                    <td><c:out value="${task.description}"/></td>
                    <td><c:out value="${task.taskDate}"/></td>
                    <td><c:out value="${task.status}"/></td>
                    <td><a href="<%=request.getContextPath()%>/task/edit?id=<c:out value='${task.id}' />">Edit</a>
                        <a href="<%=request.getContextPath()%>/task/delete?id=<c:out value='${task.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>