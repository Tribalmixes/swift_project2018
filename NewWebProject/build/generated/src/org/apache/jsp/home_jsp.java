package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Facades.*;
import DataObjects.*;
import DataAccessLayer.*;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Web Project</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
 
            String fullName = (String) session.getAttribute("fullName"); 
        
      out.write("\n");
      out.write("            <h1>Welcome ");
      out.print(fullName);
      out.write(" !</h1>            \n");
      out.write("        <h3>Add a movie:    </h3>\n");
      out.write("        <form method=\"post\" action=\"home.jsp\">\n");
      out.write("            <label>Enter movie title: </label>\n");
      out.write("            <input type=\"text\" name=\"title\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <label>Enter movie year: </label>\n");
      out.write("            <input type=\"text\" name=\"year\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <label>Enter movie length: </label>\n");
      out.write("            <input type=\"text\" name=\"movie_length\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <input type=\"submit\" value=\"Add Movie\" />\n");
      out.write("        </form>\n");
      out.write("            \n");
      out.write("            ");

            String title = request.getParameter("title");
            String year = request.getParameter("year");
            String movie_length = request.getParameter("movie_length");
            
            MovieFacade movieFacade = new MovieFacade();
                        
            Movie movie = movieFacade.addMovie(title, year, movie_length);
            
            if (title != null && year != null && movie_length != null) {
            
            response.sendRedirect("complete.jsp");}
        
      out.write("\n");
      out.write("        \n");
      out.write("        <h3>Delete a movie:    </h3>\n");
      out.write("        <form method=\"post\" action=\"home.jsp\">\n");
      out.write("            <label>Enter movie title: </label>\n");
      out.write("            <input type=\"text\" name=\"title1\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <input type=\"submit\" value=\"Delete movie\" />\n");
      out.write("        </form>\n");
      out.write("            \n");
      out.write("            ");

            String title1 = request.getParameter("title1");

            
            MovieFacade movieFacade1 = new MovieFacade();
                        
            Movie movie1 = movieFacade1.deleteMovie(title1);
            
            if (title1 != null) {
            
            response.sendRedirect("complete.jsp");}
        
      out.write("\n");
      out.write("        \n");
      out.write("        <h3>List all movies:    </h3>\n");
      out.write("        <form method=\"post\" action=\"home.jsp\">\n");
      out.write("            <label>Enter password: </label>\n");
      out.write("            <input type=\"password\" name=\"password1\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <input type=\"submit\" value=\"List movies\" />\n");
      out.write("        </form>\n");
      out.write("            \n");
      out.write("            ");

           String pass = request.getParameter("password1");
                
            MovieFacade movieFacade2 = new MovieFacade();
                        
            movieFacade2.listMovies();
            
            if (pass != null) {
            
            session.setAttribute("list", movieFacade2.listMovies());    
                
            response.sendRedirect("complete.jsp");
            out.println(movieFacade2.listMovies());
            }
        
      out.write("\n");
      out.write("        \n");
      out.write("        <h3>Add a client:    </h3>\n");
      out.write("        <form method=\"post\" action=\"home.jsp\">\n");
      out.write("            <label>Enter first name: </label>\n");
      out.write("            <input type=\"text\" name=\"firstName\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <label>Enter last name: </label>\n");
      out.write("            <input type=\"text\" name=\"lastName\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <label>Enter address: </label>\n");
      out.write("            <input type=\"text\" name=\"address\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <label>Enter email: </label>\n");
      out.write("            <input type=\"text\" name=\"email\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <label>Enter password: </label>\n");
      out.write("            <input type=\"password\" name=\"password2\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <input type=\"submit\" value=\"Add Client\" />\n");
      out.write("        </form>\n");
      out.write("            \n");
      out.write("            ");

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String password = request.getParameter("password2");
            
            UserFacade userFacade = new UserFacade();
            
            User user = userFacade.insertUser(firstName, lastName, address, email, password);
            
            if (firstName != null && lastName != null && address != null && email != null && password != null) {
            
            response.sendRedirect("complete.jsp");}
        
      out.write("\n");
      out.write("        \n");
      out.write("        <h3>Delete a client:    </h3>\n");
      out.write("        <form method=\"post\" action=\"home.jsp\">\n");
      out.write("            <label>Enter client id: </label>\n");
      out.write("            <input type=\"text\" name=\"clientId\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <input type=\"submit\" value=\"Delete client\" />\n");
      out.write("        </form>\n");
      out.write("            \n");
      out.write("            ");


            String clientId = request.getParameter("clientId");
            
            UserFacade userFacade1 = new UserFacade();

            User user1 = userFacade1.deleteUser(clientId);
            
            if (clientId != null) {
            
            response.sendRedirect("complete.jsp");}
        
      out.write("\n");
      out.write("        \n");
      out.write("        <h3>List all clients:    </h3>\n");
      out.write("        <form method=\"post\" action=\"home.jsp\">\n");
      out.write("            <label>Enter password: </label>\n");
      out.write("            <input type=\"password\" name=\"password3\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <input type=\"submit\" value=\"List clients\" />\n");
      out.write("        </form>\n");
      out.write("            \n");
      out.write("            ");

            String pass1 = request.getParameter("password3");
            
            UserFacade userFacade2 = new UserFacade();
            
            userFacade2.listUsers();
                

            
            if (pass1 != null) {
            
            session.setAttribute("list", movieFacade2.listMovies());    
                
            response.sendRedirect("complete.jsp");
            out.println(movieFacade2.listMovies());
            }
        
      out.write("\n");
      out.write("        \n");
      out.write("        <h3>Rent a movie:    </h3>\n");
      out.write("        <form method=\"post\" action=\"home.jsp\">\n");
      out.write("            <label>Enter movie id: </label>\n");
      out.write("            <input type=\"text\" name=\"movieId5\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <label>Enter client id: </label>\n");
      out.write("            <input type=\"text\" name=\"clientId5\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <label>Enter rental date: </label>\n");
      out.write("            <input type=\"text\" name=\"rentalDate\" placeholder=\"YYYY-MM-DD\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <input type=\"submit\" value=\"Rent Movie\" />\n");
      out.write("        </form>\n");
      out.write("            \n");
      out.write("            ");

            String movieId1 = request.getParameter("movieId5");
            String clientId1 = request.getParameter("clientId5");
            String rentalDate = request.getParameter("rentalDate");
            
            MovieFacade movieFacade3 = new MovieFacade();
            
            Movie movie2 = movieFacade3.rentMovie(movieId1, clientId1, rentalDate);
                        
            if (movieId1 != null && clientId1 != null && rentalDate != null) {
            
            response.sendRedirect("complete.jsp");
            }
        
      out.write("\n");
      out.write("        \n");
      out.write("        <h3>Return a movie:    </h3>\n");
      out.write("        <form method=\"post\" action=\"home.jsp\">\n");
      out.write("            <label>Enter client id: </label>\n");
      out.write("            <input type=\"text\" name=\"movieId4\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <label>Enter movie id: </label>\n");
      out.write("            <input type=\"text\" name=\"clientId4\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <label>Enter return date: </label>\n");
      out.write("            <input type=\"text\" name=\"returnDate\" placeholder=\"YYYY-MM-DD\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <input type=\"submit\" value=\"Return Movie\" />\n");
      out.write("        </form>\n");
      out.write("            \n");
      out.write("            ");

            String clientId2 = request.getParameter("clientId4");
            String movieId2 = request.getParameter("movieId4");
            String returnDate = request.getParameter("returnDate");
            
            MovieFacade movieFacade4 = new MovieFacade();
            
            Movie movie3 = movieFacade4.returnMovie(clientId2, movieId2, returnDate);
                        
            if (movieId2 != null && clientId2 != null && returnDate != null) {
            
            response.sendRedirect("complete.jsp");
            }
        
      out.write("\n");
      out.write("        \n");
      out.write("         <h3>Check fines:    </h3>\n");
      out.write("         <form method=\"post\" action=\"home.jsp\">\n");
      out.write("            <label>Enter client id: </label>\n");
      out.write("            <input type=\"text\" name=\"clientId3\" value=\"\" />\n");
      out.write("            </br>\n");
      out.write("            <input type=\"submit\" value=\"Check fines\" />\n");
      out.write("        </form>\n");
      out.write("            \n");
      out.write("            ");

            String clientId3 = request.getParameter("clientId3");
                
            MovieFacade movieFacade5 = new MovieFacade();
            
            Movie movie4 = movieFacade5.checkFines(clientId3);
                                 
            if (clientId3 != null) {
            
                
            response.sendRedirect("complete.jsp");
            out.println(movieFacade5.checkFines(clientId3));
            }
        
      out.write("\n");
      out.write("\n");
      out.write("        </br>\n");
      out.write("\n");
      out.write("        <a href=\"index.jsp\">Return to Login</a>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
