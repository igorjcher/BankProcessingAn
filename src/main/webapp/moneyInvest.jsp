<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Money Invest</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/mainMenu.jspf" %>
        <h1>Money Invest</h1>
        
        <div>
            <form action="MoneyInvesting" method="POST">
                Amount of money to invest<input type="text" name="moneytoinvest"><br>
                <input type="submit" value="Increase Balance">    
            </form>
            </div>
        
    </body>
</html>
