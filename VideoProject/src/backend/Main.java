package backend;

import backend.Classes.JDBCMovieDAO;
import backend.Classes.Movie;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Movie movie = new Movie();

        JDBCMovieDAO jdbcMovieDao = new JDBCMovieDAO();
        jdbcMovieDao.getConnection();
        jdbcMovieDao.startMenu();

    }
}

