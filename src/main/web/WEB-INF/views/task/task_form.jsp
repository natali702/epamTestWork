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
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${requestScope.task != null}">
            <form action="<%=request.getContextPath()%>update" method="post">
                </c:if>
                <c:if test="${requestScope.task == null}">
                </c:if>
                <caption>
                    <h2>
                        <c:if test="${requestScope.task != null}">
                            Edit task
                        </c:if>
                        <c:if test="${requestScope.task == null}">
                            Add New task
                        </c:if>
                    </h2>
                </caption>

                <c:if test="${requestScope.task != null}">
                <input type="hidden" name="id" value="<c:out value='${requestScope.task.id}' />"/>
                </c:if>

                <fieldset class="form-group">
                    <label>Task Title</label> <input type="text"
                                                     value="<c:out value='${requestScope.task.title}' />"
                                                     class="form-control"
                                                     name="title" required="required" minlength="5">
                </fieldset>

                <fieldset class="form-group">
                    <label>Task Description</label> <input type="text"
                                                           value="<c:out value='${requestScope.task.description}' />"
                                                           class="form-control"
                                                           name="description" minlength="4">
                </fieldset>

                <fieldset class="form-group">
                    <label>Task Status</label> <select class="form-control"
                                                       name="isDone">
                    <option value="false">In Progress</option>
                    <option value="true">Complete</option>
                </select>
                </fieldset>

                <fieldset class="form-group">
                    <label>Task Date</label>
                    <input type="date"
                           value="<c:out value='${requestScope.task.taskDate}' />"
                           class="form-control"
                           name="taskDate" required="required">
                </fieldset>
                <c:if test="${requestScope.task == null}">
                <fieldset class="form-group">
                    <label>Goal id</label>
                    <input type="number"
                           value="<c:out value='${requestScope.task.goal_id}'/>"
                           class="form-control"
                           name="goal_id">
                </fieldset>
                </c:if>
                <button type="submit" class="btn btn-success">Save</button>
        </div>
    </div>
</div>
</body>
</html>