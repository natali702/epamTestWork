<%--
  Created by IntelliJ IDEA.
  User: lexse
  Date: 08.07.2020
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create or edit goal</title>
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
            <c:if test="${requestScope.goal != null}">
            <form action="<%=request.getContextPath()%>update" method="post">
                </c:if>
                <c:if test="${requestScope.goal == null}">
                <form action="<%=request.getContextPath()%>insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${requestScope.goal != null}">
                                Edit goal
                            </c:if>
                            <c:if test="${requestScope.goal == null}">
                                Add New goal
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${requestScope.goal != null}">
                        <input type="hidden" name="id" value="<c:out value='${requestScope.goal.id}' />"/>
                        <input type="hidden" name="parent" value="<c:out value='${requestScope.goal.parentId}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Goal Title</label>
                        <input type="text" value="<c:out value='${requestScope.goal.title}' />" class="form-control"
                               name="title" required="required" minlength="3">
                        <c:if test="${requestScope.goal == null}">
                            <label>Id parent goal</label>
                            <input type="text" value="<c:out value='${requestScope.goal.parent}' />"
                                   class="form-control"
                                   name="parent">
                        </c:if>
                    </fieldset>
                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
