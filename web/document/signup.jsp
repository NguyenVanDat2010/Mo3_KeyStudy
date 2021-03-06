<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: VCOM
  Date: 27/06/2020
  Time: 8:04 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signup.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">

</head>

<body>
<%-- Navbar--%>
<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">
        <img src="images/logo.png" alt="Err" height="50"/>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <i class="fa fa-home"></i>
                    Home
                    <span class="sr-only">(current)</span>
                </a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-envelope-o">
                        <span class="badge badge-primary">11</span>
                    </i>
                    Dropdown
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
        </ul>

        </ul>
        <form class="form-inline my-2 my-lg-0">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link " href="/users?action=signin" style="text-decoration: none">
                        <i class="fa fa-sign-in"></i>
                        Sign In
                    </a>
                </li>
            </ul>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link " href="/users?action=signup" style="text-decoration: none">
                        <i class="fa fa-registered"></i>
                        Register
                    </a>
                </li>
            </ul>
        </form>
    </div>
</nav>



<div class="registration-form">
    <form method="post" action="/users?action=signup">
        <div class="form-icon">
            <span>
<%--                <span class = "glyphicon glyphicon-user"></span>--%>
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="70" height="70"><path fill-rule="evenodd" d="M12 2.5a5.5 5.5 0 00-3.096 10.047 9.005 9.005 0 00-5.9 8.18.75.75 0 001.5.045 7.5 7.5 0 0114.993 0 .75.75 0 101.499-.044 9.005 9.005 0 00-5.9-8.181A5.5 5.5 0 0012 2.5zM8 8a4 4 0 118 0 4 4 0 01-8 0z"></path></svg>
            </span>
        </div>
        <div class="form-group">
            <input type="text" class="form-control item" name="name" id="name" placeholder="Full Name" required autofocus>
        </div>
        <div class="form-group">
            <input type="password" class="form-control item" name="password" id="password" placeholder="Password">
            <input type="password" class="form-control item" name="cfPassword" id="cfPassword" placeholder="Confirm Password">
        </div>
        <div class="form-group">
            <input type="email" class="form-control item" name="email" id="email" placeholder="Email">
        </div>
        <div class="form-group">
                <select style="padding: 2px 15px" class="form-control item" name="gender">
                    <option value="">Gender</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="other">Other</option>
                </select>
        </div>
        <div class="form-group">
            <input type="date" class="form-control item" name="birthday" id="birth-date" placeholder="Birth Date">
        </div>
        <div class="form-group">
                <c:if test="${requestScope['message']!=null}">
                    <span style="color: red" class="message">${requestScope["message"]}</span>
                </c:if>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block create-account">Register</button>
<%--            <a href="/users" style="text-decoration: none"><button type="button" class="btn btn-primary btn-block create-account">Sign In</button></a>--%>
        </div>
        <hr>
        <div class="form-group">
            <a href="#" style="text-decoration: none"><button type="button" class="btn btn-block create-account btn-google"><i class="fab fa-google mr-2"></i>Sign up with Google</button></a>
            <a href="#" style="text-decoration: none"><button type="button" class="btn btn-block create-account btn-facebook"><i class="fab fa-facebook-f mr-2"></i>Sign up with Facebook</button></a>
        </div>
    </form>
</div>
</body>
</html>
