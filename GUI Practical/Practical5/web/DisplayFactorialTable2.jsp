<%-- 
    Document   : DisplayFactorialTable2
    Created on : Feb 27
    Author     : zixuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Factorial Table</title>
        <style>
            th,td{
                border: 1px solid black;
                border-collapse: collapse;
            }
            table{
                border: 1px solid black;
            }
        </style>
    <body>
        <h2>Factorial Table</h2>
        <table>
            <tr>
                <th>Number</th>
                <th>Factorial</th>
            </tr>

            <%! int result = 1;%>
            <% for (int i = 1; i < 10; i++) {
                    result *= i;
            %>
            <tr>
                <td><%= i%></td>
                <td><%= result%></td>
            </tr>
            <% }%>
        </table>

    </body>
</html>
