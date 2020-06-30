<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: VCOM
  Date: 29/06/2020
  Time: 12:25 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Admin</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">

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
            <li class="nav-item active">
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
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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
                        Draft
                        <span class="badge badge-warning">
                            <c:if test="${requestScope['messDraft']!=null}">
                                <span style="color: white" class="message">${requestScope["messDraft"]}</span>
                            </c:if>
                        </span>
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


<div id="main">
    <div id="s-body">
        <div id="left">
            <h4>Category</h4>
            <ul>
                <li><a href="/document?action=composeAdmin">Compose</a></li>
                <li><a href="/document?action=inboxAdmin">Inbox</a></li>
                <li><a href="/document?action=sentAdmin">Sent</a></li>
<%--                <li><a href="/document?action=allDcmtAdmin">All document</a></li>--%>
                <li><a href="/document?action=draftAdmin">Draft</a></li>
                <li><a href="/users?action=admin">Administration</a></li>
            </ul>
        </div>
        <div id="content">
            <h1>Welcome</h1>
            <p>A document is a written, drawn, presented, or memorialized representation of thought, often the manifestation of non-fictional, as well as fictional, content. The word originates from the Latin Documentum, which denotes a "teaching" or "lesson": the verb doceō denotes "to teach". In the past, the word was usually used to denote written proof useful as evidence of a truth or fact. In the computer age, "document" usually denotes a primarily textual computer file, including its structure and format, e.g. fonts, colors, and images. Contemporarily, "document" is not defined by its transmission medium, e.g., paper, given the existence of electronic documents. "Documentation" is distinct because it has more denotations than "document". Documents are also distinguished from "realia", which are three-dimensional objects that would otherwise satisfy the definition of "document" because they memorialize or represent thought; documents are considered more as 2-dimensional representations. While documents can have large varieties of customization, all documents can be shared freely and have the right to do so, creativity can be represented by documents, also. History, events, examples, opinions, etc. all can be expressed in documents.</p>
<%--            <h3>Test</h3>--%>
            <p>The concept of "document" has been defined by Suzanne Briet as "any concrete or symbolic indication, preserved or recorded, for reconstructing or for proving a phenomenon, whether physical or mental."</p>
            <p>The document had the right to change within 5 days on the date of registration</p>
        </div>
        <div id="right">
            <p>
                Xin chào:
                <c:if test="${requestScope['message']!=null}">
                    <span style="color: blue" class="message">${requestScope["message"]}</span>
                </c:if>
            </p>
        </div>
    </div>
    <footer id="footer"></footer>
</div>

</body>
</html>
