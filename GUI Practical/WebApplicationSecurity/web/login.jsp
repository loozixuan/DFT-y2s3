<%-- 
    Document   : login
    Created on : Mar 17, 2021, 3:44:11 PM
    Author     : zixua
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="j_security_check" method="POST">
            Username: <input type="text" name="j_username"/><br>
            Password: <input type="password" name="j_password"/> <br>
            <input type="submit" value="Login"/>
        </form>
    </body>
</html>
