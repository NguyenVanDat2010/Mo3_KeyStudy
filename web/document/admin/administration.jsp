<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: VCOM
  Date: 28/06/2020
  Time: 5:02 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administration</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_compose.css">
</head>
<body>
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
                <a class="nav-link" href="/users?action=home">
                    <i class="fa fa-home"></i>
                    Home
                    <span class="sr-only">(current)</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <i class="fa fa-envelope-o">
                        <span class="badge badge-danger">11</span>
                    </i>
                    Compose
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="#">
                    <i class="fa fa-envelope-o">
                        <span class="badge badge-danger">11</span>
                    </i>
                    Inbox
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="#">
                    <i class="fa fa-envelope-o">
                        <span class="badge badge-warning">11</span>
                    </i>
                    Sent
                </a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-envelope-o">
                        <span class="badge badge-primary">11</span>
                    </i>
                    Category
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">All document</a>
                    <a class="dropdown-item" href="#">Draft</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Administration</a>
                </div>
            </li>
        </ul>
        <ul class="navbar-nav ">
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <i class="fa fa-bell">
                        <span class="badge badge-info">11</span>
                    </i>
                    Test
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <i class="fa fa-globe">
                        <span class="badge badge-success">11</span>
                    </i>
                    Notification
                </a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="button">Search</button>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link " href="/users?action=signin">
                        <i class="fa fa-sign-out">
                            <%--                            <span class="badge badge-warning">11</span>--%>
                        </i>
                        Sign out
                    </a>
                </li>
            </ul>
        </form>

    </div>
</nav>


<div id="main">
    <div id="s-body">
        <div id="left">
            <h4>Administration</h4>
            <ul>
                <li><a href="#">User list</a></li>
                <li><a href="#">Document list</a></li>
            </ul>
        </div>
        <div id="content">
            <h1>Welcome</h1>
            <p>Là đơn vị tham chiếu tỷ lệ so với một phần tử mẹ của nó dựa vào kích thước. Ví dụ bạn có một cái khung với kích thước là 500px và khung bên trong có kích thước là 50% thì nó sẽ là 250px. Nếu bạn sử dụng đơn vị phần trăm này để gán kích thước cho thẻ <html> trên website thì nó sẽ thay đổi theo kích thước màn hình hoặc/cửa sổ website.</p>
            <h3>Test</h3>
            <p>Trong bài này mình chỉ đề cập đến các đơn vị thường dùng.</p>
        </div>
    </div>
    <footer id="footer"></footer>
</div>
</body>
</html>
