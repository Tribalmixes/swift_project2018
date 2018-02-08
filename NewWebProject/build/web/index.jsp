<%@page import="Facades.*"%>
<%@page import="DataObjects.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Project</title>
    </head>
    <body>
        <h2>Hi there! Please login.</h2>
        
                <form method="post" action="index.jsp">
            <label>Enter First Name: </label>
            <input type="text" name="firstName" value="" />
            </br>
            <label>Enter Last Name: </label>
            <input type="text" name="lastName" value="" />
            </br>
            <label>Enter Password: </label>
            <input type="password" name="password" value="" />
            </br>
            <input type="submit" value="Login" />
        </form>
        
        <%
            Boolean failed = (Boolean) session.getAttribute("authentication-failed");
            
            if (failed != null && failed) {
                out.write("Incorect Username or Password!");
            }
        %>
        
        <%
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            
            LoginFacade loginFacade = new LoginFacade();
            
            User user = loginFacade.login(firstName, lastName, password);
            
            if (user != null) {
                session.setAttribute("fullName", user.getFullName());
                
                response.sendRedirect("home.jsp");
                session.setAttribute("authentication-failed", false);
            } else {
                session.setAttribute("authentication-failed", true);
            }
        %>
    </body>
</html>
