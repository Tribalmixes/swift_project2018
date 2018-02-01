package backend.DAO;

import backend.Classes.Movie;

import java.util.List;

public interface MovieDAO {

    List<Movie> selectMovies();
    void insertMovie();
    void deleteMovie();
    void rentMovie();
    void returnMovie();
    void checkFines();
}
