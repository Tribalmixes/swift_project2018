package Facades;

import DataAccessLayer.MovieDAO;
import DataObjects.Movie;

import java.util.List;

public class MovieFacade {

    private MovieDAO movieDAO = new MovieDAO();

    public Movie addMovie(String title, String year, String movieLength) {
        Movie movie = movieDAO.insertMovie(title, year, movieLength);

        return movie;
    }

    public Movie deleteMovie(String title) {
        Movie movie = movieDAO.deleteMovie(title);

        return movie;
    }

    public List<Movie> listMovies(){
        List<Movie> movies = movieDAO.selectMovies();

        return movies;
    }

    public Movie rentMovie(String movieId, String clientId, String rentalTime){
        Movie movie = movieDAO.rentMovie(movieId, clientId, rentalTime);

        return movie;
    }

    public Movie returnMovie(String clientId, String movieId, String returnTime){
        Movie movie = movieDAO.returnMovie(clientId, movieId, returnTime);

        return movie;
    }

    public Movie checkFines(String clientId){
        Movie movie = movieDAO.checkFines(clientId);

        return movie;
    }
}
