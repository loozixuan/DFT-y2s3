<%-- 
    Document   : DisplayFactorialTable
    Created on : Feb 19, 2021, 7:11:09 PM
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

            <% for (int i = 1; i < 10; i++) {%>
            <tr>
                <td><%= i%></td>
                <td><%= computeFactorial(i)%></td>
            </tr>
            <% }%>
        </table>

        <%! private long computeFactorial(int n) {
                if (n == 0) {
                    return 1;
                } else {
                    return n * computeFactorial(n - 1); // 
                }
            }
        %>
    </body>
</html>
