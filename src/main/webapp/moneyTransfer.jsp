<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Money Transfer</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/mainMenu.jspf" %>
        <h1>Money Transfer</h1>
        <div>
            <form action="MoneyTransfer" method="POST">
                Amount <input type="text" name="amount"><br>
                To Account<input type="text" name="accountTo"><br>
                <input type="submit" value="Transfer"><br>
            </form>
        </div>
    </body>
</html>

