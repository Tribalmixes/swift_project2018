<%@page import="Facades.*"%>
<%@page import="DataObjects.*"%>
<%@page import="DataAccessLayer.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Project</title>
    </head>
    <body>
        <% 
            String fullName = (String) session.getAttribute("fullName"); 
        %>
            <h1>Welcome <%=fullName%> !</h1>            
        <h3>Add a movie:    </h3>
        <form method="post" action="home.jsp">
            <label>Enter movie title: </label>
            <input type="text" name="title" value="" />
            </br>
            <label>Enter movie year: </label>
            <input type="text" name="year" value="" />
            </br>
            <label>Enter movie length: </label>
            <input type="text" name="movie_length" value="" />
            </br>
            <input type="submit" value="Add Movie" />
        </form>
            
            <%
            String title = request.getParameter("title");
            String year = request.getParameter("year");
            String movie_length = request.getParameter("movie_length");
            
            MovieFacade movieFacade = new MovieFacade();
                        
            Movie movie = movieFacade.addMovie(title, year, movie_length);
            
            if (title != null && year != null && movie_length != null) {
            
            response.sendRedirect("complete.jsp");}
        %>
        
        <h3>Delete a movie:    </h3>
        <form method="post" action="home.jsp">
            <label>Enter movie title: </label>
            <input type="text" name="title1" value="" />
            </br>
            <input type="submit" value="Delete movie" />
        </form>
            
            <%
            String title1 = request.getParameter("title1");

            
            MovieFacade movieFacade1 = new MovieFacade();
                        
            Movie movie1 = movieFacade1.deleteMovie(title1);
            
            if (title1 != null) {
            
            response.sendRedirect("complete.jsp");}
        %>
        
        <h3>List all movies:    </h3>
        <form method="post" action="home.jsp">
            <label>Enter password: </label>
            <input type="password" name="password1" value="" />
            </br>
            <input type="submit" value="List movies" />
        </form>
            
            <%
           String pass = request.getParameter("password1");
                
            MovieFacade movieFacade2 = new MovieFacade();
                        
            movieFacade2.listMovies();
            
            if (pass != null) {
            
            session.setAttribute("list", movieFacade2.listMovies());    
                
            response.sendRedirect("complete.jsp");
            out.println(movieFacade2.listMovies());
            }
        %>
        
        <h3>Add a client:    </h3>
        <form method="post" action="home.jsp">
            <label>Enter first name: </label>
            <input type="text" name="firstName" value="" />
            </br>
            <label>Enter last name: </label>
            <input type="text" name="lastName" value="" />
            </br>
            <label>Enter address: </label>
            <input type="text" name="address" value="" />
            </br>
            <label>Enter email: </label>
            <input type="text" name="email" value="" />
            </br>
            <label>Enter password: </label>
            <input type="password" name="password2" value="" />
            </br>
            <input type="submit" value="Add Client" />
        </form>
            
            <%
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String password = request.getParameter("password2");
            
            UserFacade userFacade = new UserFacade();
            
            User user = userFacade.insertUser(firstName, lastName, address, email, password);
            
            if (firstName != null && lastName != null && address != null && email != null && password != null) {
            
            response.sendRedirect("complete.jsp");}
        %>
        
        <h3>Delete a client:    </h3>
        <form method="post" action="home.jsp">
            <label>Enter client id: </label>
            <input type="text" name="clientId" value="" />
            </br>
            <input type="submit" value="Delete client" />
        </form>
            
            <%

            String clientId = request.getParameter("clientId");
            
            UserFacade userFacade1 = new UserFacade();

            User user1 = userFacade1.deleteUser(clientId);
            
            if (clientId != null) {
            
            response.sendRedirect("complete.jsp");}
        %>
        
        <h3>List all clients:    </h3>
        <form method="post" action="home.jsp">
            <label>Enter password: </label>
            <input type="password" name="password3" value="" />
            </br>
            <input type="submit" value="List clients" />
        </form>
            
            <%
            String pass1 = request.getParameter("password3");
            
            UserFacade userFacade2 = new UserFacade();
            
            userFacade2.listUsers();
                

            
            if (pass1 != null) {
            
            session.setAttribute("list", movieFacade2.listMovies());    
                
            response.sendRedirect("complete.jsp");
            out.println(movieFacade2.listMovies());
            }
        %>
        
        <h3>Rent a movie:    </h3>
        <form method="post" action="home.jsp">
            <label>Enter movie id: </label>
            <input type="text" name="movieId5" value="" />
            </br>
            <label>Enter client id: </label>
            <input type="text" name="clientId5" value="" />
            </br>
            <label>Enter rental date: </label>
            <input type="text" name="rentalDate" placeholder="YYYY-MM-DD" value="" />
            </br>
            <input type="submit" value="Rent Movie" />
        </form>
            
            <%
            String movieId1 = request.getParameter("movieId5");
            String clientId1 = request.getParameter("clientId5");
            String rentalDate = request.getParameter("rentalDate");
            
            MovieFacade movieFacade3 = new MovieFacade();
            
            Movie movie2 = movieFacade3.rentMovie(movieId1, clientId1, rentalDate);
                        
            if (movieId1 != null && clientId1 != null && rentalDate != null) {
            
            response.sendRedirect("complete.jsp");
            }
        %>
        
        <h3>Return a movie:    </h3>
        <form method="post" action="home.jsp">
            <label>Enter client id: </label>
            <input type="text" name="movieId4" value="" />
            </br>
            <label>Enter movie id: </label>
            <input type="text" name="clientId4" value="" />
            </br>
            <label>Enter return date: </label>
            <input type="text" name="returnDate" placeholder="YYYY-MM-DD" value="" />
            </br>
            <input type="submit" value="Return Movie" />
        </form>
            
            <%
            String clientId2 = request.getParameter("clientId4");
            String movieId2 = request.getParameter("movieId4");
            String returnDate = request.getParameter("returnDate");
            
            MovieFacade movieFacade4 = new MovieFacade();
            
            Movie movie3 = movieFacade4.returnMovie(clientId2, movieId2, returnDate);
                        
            if (movieId2 != null && clientId2 != null && returnDate != null) {
            
            response.sendRedirect("complete.jsp");
            }
        %>
        
         <h3>Check fines:    </h3>
         <form method="post" action="home.jsp">
            <label>Enter client id: </label>
            <input type="text" name="clientId3" value="" />
            </br>
            <input type="submit" value="Check fines" />
        </form>
            
            <%
            String clientId3 = request.getParameter("clientId3");
                
            MovieFacade movieFacade5 = new MovieFacade();
            
            Movie movie4 = movieFacade5.checkFines(clientId3);
                                 
            if (clientId3 != null) {
            
                
            response.sendRedirect("complete.jsp");
            out.println(movieFacade5.checkFines(clientId3));
            }
        %>

        </br>

        <a href="index.jsp">Return to Login</a>
    </body>
</html>
