<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="ru.igor.bancprocessingan.entities.User"%>
<%@page import="ru.igor.bancprocessingan.servlets.AttributeConst"%>

<%
List<User> users = (List<User>)request.getAttribute(AttributeConst.USERS);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Users</title>
    </head>
    <body>
        <h1>All Users.</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>E-mail</th>
                <th>Name</th>
                <th>Age</th>
                <th>Balance</th>
                <th>Role</th>
            </tr>
            <% for(User user: users) { %>
                <tr>
                    <td><%= user.getId() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getName() %></td>
                    <td><%= user.getAge() %></td>
                    <td><%= user.getBalance() %></td>
                    <td><%= user.getRole() %></td>
                </tr>
            <% } %>
        </table>
                
                
    </body>
</html>
