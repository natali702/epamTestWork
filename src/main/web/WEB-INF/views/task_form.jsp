<%--
  Created by IntelliJ IDEA.
  User: lexse
  Date: 05.07.2020
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User Management Application</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Tasks</a></li>
        </ul>

        <ul class="navbar-nav navbar-collapse justify-content-end">
            <li><a href="<%=request.getContextPath()%>/logout"
                   class="nav-link">Logout</a></li>
        </ul>
    </nav>
</header>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${requestScope.task != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${requestScope.task == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${requestScope.task != null}">
                                Edit task
                            </c:if>
                            <c:if test="${task == null}">
                                Add New task
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${requestScope.task != null}">
                        <input type="hidden" name="id" value="<c:out value='${requestScope.task.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Task Title</label> <input type="text"
                                                         value="<c:out value='${requestScope.task.title}' />" class="form-control"
                                                         name="title" required="required" minlength="5">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Task Decription</label> <input type="text"
                                                              value="<c:out value='${requestScope.task.description}' />" class="form-control"
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
                        <label>Task Date</label> <input type="date"
                                                               value="<c:out value='${requestScope.task.taskDate}' />" class="form-control"
                                                               name="taskDate" required="required">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>