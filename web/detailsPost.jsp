<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.iitu.DB.User" %>
<%@ page import="kz.iitu.DB.Post" %>
<%--
  Created by IntelliJ IDEA.
  User: Меруерт
  Date: 09.04.2021
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp" %>
    <script type="text/javascript" src="WEB-INF/lib/tinymce/tinymce.min.js"></script>
    <script type="text/javascript">tinymce.init({ selector:'textarea' });</script>
    <title>Profile</title>
</head>

<body>

<%
    User user = (User) request.getAttribute("user");
    Post post = (Post) request.getAttribute("post");

%>

</body>
</html>
