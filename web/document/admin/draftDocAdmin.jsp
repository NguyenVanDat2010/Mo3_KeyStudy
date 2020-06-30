<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: VCOM
  Date: 30/06/2020
  Time: 2:35 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Draft of Admin</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_compose.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">

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
                        <span class="badge badge-warning">
                            <c:if test="${requestScope['messAllDoc']!=null}">
                                <span style="color: white" class="message">${requestScope["messAllDoc"]}</span>
                            </c:if>
                        </span>
                        All document
                    </a>
                    <a class="dropdown-item active" href="/document?action=draftAdmin">
                        <span class="badge badge-warning">
                            <c:if test="${requestScope['messDraft']!=null}">
                                <span style="color: white" class="message">${requestScope["messDraft"]}</span>
                            </c:if>
                        </span>
                        Draft
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/users?action=admin">Administration</a>
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

<form id="main">
    <div id="s-body">
        <div id="left">
            <h2>List Draft</h2>
            <span class="badge badge-warning">
                <h6> Number of records:
                    <c:if test="${requestScope['messDraft']!=null}">
                        <span style="color: white" class="message">${requestScope["messDraft"]}</span>
                    </c:if>
                </h6>
            </span>
        </div>
        <div id="content">
            <table >
                <tr>
                    <th>Document Name</th>
                    <th>Description</th>
                    <th>Date Create</th>
                    <th>Category</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <c:forEach items="${requestScope['documents']}" var="doc">
                    <tr>
                        <td>${doc.getTextName()}</td>
                        <td ><p class="hide">${doc.getDescription()}</p></td>
                        <td>${doc.getDateCreate()}</td>
                        <td>${doc.getCategoryName()}</td>
                        <td>
                            <a href="/document?action=editDoc&id=${doc.getTextId()}">
                                <button type="button">Edit</button>
                            </a>
                        </td>
                        <td>
                            <a href="/document?action=deleteDoc&id=${doc.getTextId()}">
                                <button type="button">Delete</button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <footer id="footer"></footer>
</form>

</body>
</html>
