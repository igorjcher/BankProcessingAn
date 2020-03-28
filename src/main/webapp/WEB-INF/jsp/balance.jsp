<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="ru.igor.bankprocessingan.servlets.AttributeConst"%>
<%@page import="ru.igor.bankprocessingan.entities.User"%>

<%
User user = (User) request.getSession().getAttribute(AttributeConst.USER);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Balance</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/mainMenu.jspf" %>
        <h1>Balance</h1>
        <h2>Balance of User <%= user.getName() %> is <%= user.getBalance()%></h2>
        
        <div>You are logged in as <%= user.getName()%> and your role is <%= user.getRole()%></div>
    </body>
</html>
