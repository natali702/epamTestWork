<%--
  Created by IntelliJ IDEA.
  User: lexse
  Date: 06.07.2020
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My super project!</title>
    <link rel="stylesheet"  href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
</head>
<style>
    body,h1 {font-family: "Raleway", sans-serif}
    body, html {height: 100%}
    .bgimg {
        background-image: url('/images/back.jpg');
        min-height: 100%;
        background-position: center;
        background-size: cover;
    }
</style>
<body>
<h1>this from login.jsp</h1>
<div class="bgimg w3-display-container w3-animate-opacity w3-text-white">

    <div class="w3-display-middle w3-padding w3-hide-small" style="width:30%">
        <div class="w3-white w3-opacity w3-hover-opacity-off w3-padding-large w3-round-large">
            <h1 class="w3-xlarge w3-center">SIGN IN</h1>
            <hr class="w3-opacity">
            <form action="/login" method="post">
                <p><input class="w3-input w3-border" type="text" name="username" placeholder="Enter e-mail" required></p>
                <p><input class="w3-input w3-border" type="password" name="password" placeholder="Enter password" required></p>


                <p><button class="w3-button w3-block w3-green w3-round" type="submit" onclick="document.getElementById('signIn')
            .style.display='block'">Sign in <i class="fa fa-android">
                </i> <i class="fa fa-apple"></i> <i class="fa fa-windows">
                </i></button></p>
            </form>
        </div>
    </div>
</div>

</body>
</html>