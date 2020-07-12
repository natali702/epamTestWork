<%@ page import="app.model.User" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:150px;" id="mySidebar"><br>
    <div class="w3-container">
        <a href="#" onclick="w3_close()" class="w3-hide-large w3-right w3-jumbo w3-padding w3-hover-grey"
           title="close menu">
            <i class="fa fa-remove"></i>
        </a>
        <img src="${pageContext.request.contextPath}/images/img_mountains.jpg" alt="avatar.img" class="w3-circle"
             style="height:106px; width:106px"><br><br>
        <h4><b>Hello <%app.model.User user = (User) request.getSession(false).getAttribute("user");%>
            <%= user.getUsername()%>
        </b></h4>
    </div>
    <div class="w3-bar-block">
        <a href="<%=request.getContextPath()%>/logout" onclick="w3_close()" class="w3-bar-item w3-button w3-padding">
            <i class="fa fa-user fa-fw w3-margin-right"></i>Logout</a>
    </div>
</nav>
<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer"
     title="close side menu" id="myOverlay"></div>
<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:150px">
    <!-- Header -->
    <header id="taskboard">
        <a href="#"><img src="${pageContext.request.contextPath}/images/img_mountains.jpg" style="width:65px;"
                         class="w3-circle w3-right w3-margin w3-hide-large w3-hover-opacity" alt="avatar.img"></a>
        <span class="w3-button w3-hide-large w3-xxlarge w3-hover-text-grey" onclick="w3_open()"><i
                class="fa fa-bars"></i></span>
        <div class="w3-container">
            <h1><b>My taskboard</b></h1>
        </div>
        <nav class="navbar navbar-expand-md navbar-light"
             style="background-color: lightsteelblue">
            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/task/list"
                       class="nav-link">Tasks</a></li>
            </ul>
            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/goal/list"
                       class="nav-link">Goals</a></li>
            </ul>
            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/task/all"
                       class="nav-link">All</a></li>
            </ul>
            <form class="form-inline my-2 my-lg-0" action="<%=request.getContextPath()%>/task/get">
                <input class="form-control mr-sm-2" type="search" placeholder="Task id.." name="id" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </nav>
    </header>
</div>