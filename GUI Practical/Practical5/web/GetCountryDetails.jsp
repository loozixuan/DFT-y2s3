<%-- 
    Document   : SaveCountryDetails
    Created on : Feb 19, 2021, 8:21:30 PM
    Author     : zixuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Display Country Details</title>
    </head>
    <body>
        <%@page import="domain.Country" %>

        <!-- id refer to object name/instance -->
        <jsp:useBean id="country" class="domain.Country" scope="session"></jsp:useBean> 
        <jsp:setProperty name="country" property="*" />

        <h2>Add Country</h2>
        <%
            if (country.getName() == null || country.getFullName() == null || country.getCapital() == null) {
                out.println("Country name, full name and capital is required");
                return;
            }
        %>

        <p>You entered the following data</p>
        <p>Country Name: <%= country.getName()%></p>
        <p>Official Name: <%= country.getFullName()%></p>
        <p>Capital: <%= country.getCapital()%></p>

        <!-- Set the action for processing the answers -->
        <form method="post" action="SaveCountryDetails.jsp">
            <input type="submit" value="Confirm">
        </form>
    </body>
</html>
