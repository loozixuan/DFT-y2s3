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
        <title>Save Country Details</title>
    </head>
    <body>
        <%@page import="domain.Country" %>
        <!-- try to find "country" object in session -->
        <jsp:useBean id="country" class="domain.Country" scope="session"></jsp:useBean>
        <jsp:useBean id="countryDA" class="da.CountriesDA" scope="application"></jsp:useBean>

        <%
            try {
                countryDA.addRecord(country);
            } catch (Exception e) {
                e.printStackTrace();
            }

            out.println(country.getName() + " " + country.getFullName() + " " + country.getCapital()
                    + " has been stored in database");
            out.close();
        %>
    </body>
</html>
