<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: VCOM
  Date: 30/06/2020
  Time: 2:18 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_compose.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/compose.css">

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
                <a class="nav-link" href="/users?action=homeAdmin">
                    <i class="fa fa-home"></i>
                    Home
                    <span class="sr-only">(current)</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/document?action=composeAdmin">
                    <i class="fa fa-envelope-o">
                        <span class="badge badge-danger"></span>
                    </i>
                    Compose
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="/document?action=inboxAdmin">
                    <i class="fa fa-envelope-o">
                        <span class="badge badge-danger">
                            <c:if test="${requestScope['messInbox']!=null}">
                                <span style="color: white" class="message">${requestScope["messInbox"]}</span>
                            </c:if>
                        </span>
                    </i>
                    Inbox
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="/document?action=sentAdmin">
                    <i class="fa fa-envelope-o">
                        <span class="badge badge-warning">
                            <c:if test="${requestScope['messSent']!=null}">
                                <span style="color: white" class="message">${requestScope["messSent"]}</span>
                            </c:if>
                        </span>
                    </i>
                    Sent
                </a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle active" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-envelope-o">
                        <span class="badge badge-primary">
                            <c:if test="${requestScope['messAllAddDraft']!=null}">
                                <span style="color: white" class="message">${requestScope["messAllAddDraft"]}</span>
                            </c:if>
                        </span>
                    </i>
                    Category
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/document?action=allDcmtAdmin">
                        All document
                        <span class="badge badge-warning">
                            <c:if test="${requestScope['messAllDoc']!=null}">
                                <span style="color: white" class="message">${requestScope["messAllDoc"]}</span>
                            </c:if>
                        </span>
                    </a>
                    <a class="dropdown-item" href="/document?action=draftAdmin">
                        <span class="badge badge-warning">
                            <c:if test="${requestScope['messDraft']!=null}">
                                <span style="color: white" class="message">${requestScope["messDraft"]}</span>
                            </c:if>
                        </span>
                        Draft
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item active" href="/users?action=admin">Administration</a>
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


<form method="post" action="/users?action=addUserByAdmin" id="main">
    <div id="s-body">
        <div id="left">
            <h2>Add user by Admin</h2>
            <c:if test="${requestScope['message']!=null}">
                <span style="color: red" class="message">${requestScope["message"]}</span>
            </c:if>
        </div>
        <div id="content">
            <table>
<%--                <tr>--%>
<%--                    <td><label>User Id: </label></td>--%>
<%--                    <td><input type="text" name="id" value="${requestScope['user'].getId()}" class="form-control" disabled></td>--%>
<%--                </tr>--%>
                <tr>
                    <td><label>User Name: </label></td>
                    <td><input type="text" name="name" value="${requestScope['user'].getName()}" class="form-control"></td>
                </tr>
                <tr>
                    <td><label>Email: </label></td>
                    <td><input type="text" name="email" value="${requestScope['user'].getEmail()}" class="form-control"></td>
                </tr>
                <tr>
                    <td><label>Password: </label></td>
                    <td><input type="password" name="password" value="${requestScope['user'].getPassword()}" class="form-control"></td>
                </tr>
                <tr>
                    <td><label>Birthday: </label></td>
                    <td>
                        <input type="date" name="birthday" style="width: 420px" value="${requestScope['user'].getBirthday()}" class="form-control">
                    </td>
                </tr>
                <tr>
                    <td>Gender: </td>
                    <td>
                        <select style="width: 420px" class="form-control" name="gender">
                            <%--                            <option value="">Choose gender</option>--%>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                            <option value="other">Other</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><button type="submit" class="btn btn-warning">Submit</button></td>
                </tr>
            </table>
        </div>
    </div>
    <footer id="footer"></footer>
</form>

</body>
</html>
