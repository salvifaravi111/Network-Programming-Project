<%-- 
    Document   : logout
    Created on : Apr 15, 2019, 12:51:49 AM
    Author     : Sifat Ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    int userid = -1;
    String username="";
    session.invalidate();
    
    response.sendRedirect("index.jsp");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logging out!</title>
    </head>
    <body>
        
    </body>
</html>
