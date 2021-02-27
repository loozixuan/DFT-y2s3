<%-- 
    Document   : ComputeBMI
    Created on : Feb 19, 2021, 7:44:38 PM
    Author     : zixuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BMI Calculation</title>
    </head>
    <body>
        <%@page import="domain.BMI"%>

        <%
            double weight = Double.parseDouble(request.getParameter("weight"));
            double height = Double.parseDouble(request.getParameter("height"));

            BMI bmi = new BMI(weight, height);

        %>

        <h2>BMI Calculation</h2>
        Height: <%= String.format("%.2f", height)%>m <br />
        Weight: <%= String.format("%.2f", weight)%>kg <br />
        BMI: <%= String.format("%.2f", bmi.getBMI())%> <br />
        Category: <%= bmi.getStatus()%>
    </body>
</html>
