<%@page import="java.util.List"%>
<%@page import="Facades.*"%>
<%@page import="DataObjects.*"%>
<%@page import="DataAccessLayer.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Action completed!</title>
    </head>
    <body>
        <% 
            String bgColor = (String) session.getAttribute("background-color");
        %>
        
        <div style="background-color: <%=bgColor%>">
            <h1>Thank you !
            Your action was completed</h1>            
        </div>
    </body>
</html>
