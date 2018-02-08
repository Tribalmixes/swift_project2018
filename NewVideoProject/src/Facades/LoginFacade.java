package Facades;

import DataAccessLayer.MovieDAO;
import DataAccessLayer.UserDAO;
import DataObjects.Movie;
import DataObjects.User;

public class LoginFacade {

    private UserDAO userDAO = new UserDAO();

    public User login(String firstName, String lastName, String password) {
        User user = userDAO.findUser(firstName, lastName, password);

        return user;
    }

    private MovieDAO movieDAO = new MovieDAO();

    public Movie close() {
        Movie movie = movieDAO.closeConnection();

        return movie;
    }
}
