package DataAccessLayer;

import DataObjects.Movie;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MovieDAO extends BaseDAO {

    public MovieDAO() {

    }

    public Movie insertMovie(String title, String year, String movieLength) {
        String sql = "INSERT INTO videostoredb.movies (title, year, movie_length) VALUES (?, ?, ?)";

        Movie movie = null;

        try (Connection con = getConnection();

             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, year);
            ps.setString(3, movieLength);

            ps.executeUpdate();
            ps.close();

            System.out.printf("%s was added to our database", title);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public Movie deleteMovie(String title) {
        String sql = "DELETE FROM videostoredb.movies WHERE title = ?";

        Movie movie = null;

        try (Connection con = getConnection();

             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);

            ps.executeUpdate();
            ps.close();

            System.out.printf("%s was removed from our database", title);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public List<Movie> selectMovies() {

        List<Movie> movies = new LinkedList<>();

        Movie movie = null;

        try (Connection con = getConnection()) {

            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM videostoredb.movies");

            if (resultSet.next()) {
                movie = new Movie();
                movie.setMovieId(resultSet.getString("movie_id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setYear(resultSet.getString("year"));
                movie.setMovieLength(resultSet.getString("movie_length"));

                movies.add(movie);

                while (resultSet.next()) {
                    System.out.printf("%d %s %d %d %n",
                            resultSet.getInt("movie_id"),
                            resultSet.getString("title"),
                            resultSet.getInt("year"),
                            resultSet.getInt("movie_length"));
                }
            }
            resultSet.close();
            statement.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public Movie rentMovie(String movieId, String clientId, String rentalTime) {
        String sql = "INSERT INTO videostoredb.clients_movies (client_id, movie_id, rental_time, status) VALUES (?, ?, ?, ?)";

        Movie movie = null;

        try {
            String status = "taken";

            try (Connection con = getConnection();

                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, movieId);
                ps.setString(2, clientId);
                ps.setString(3, rentalTime);
                ps.setString(4, status);

                System.out.printf("Movie %s was rented by client %s on %s and is currently %s", movieId, clientId, rentalTime, status);

                ps.execute();

                ps.close();

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public Movie returnMovie(String clientId, String movieId, String returnTime) {

        Movie movie = null;

        try (Connection con = getConnection()) {
            String dateDiff;

            PreparedStatement preparedStatement = con.prepareStatement("UPDATE videostoredb.clients_movies SET status = 'returned', return_time = ? WHERE client_id = ? AND movie_id = ?");
            preparedStatement.setString(1, returnTime);
            preparedStatement.setString(2, clientId);
            preparedStatement.setString(3, movieId);

            preparedStatement.execute();

            PreparedStatement preparedStatement1 = con.prepareStatement("UPDATE clients_movies SET date_diff = datediff(return_time, rental_time) WHERE return_time IS NOT NULL AND rental_time IS NOT NULL;");

            preparedStatement1.execute();

            preparedStatement.close();
            preparedStatement1.close();

            System.out.printf("Movie %s was returned by client %s on %s", movieId, clientId, returnTime);


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public Movie checkFines(String clientId) {

        Movie movie = null;

        try (Connection con = getConnection()) {

            PreparedStatement preparedStatement = con.prepareStatement("SELECT SUM(date_diff) AS Fines FROM clients_movies WHERE client_id = ? AND date_diff > 5");
            preparedStatement.setString(1, String.valueOf(clientId));

            ResultSet rs = preparedStatement.executeQuery();

            PreparedStatement preparedStatement1 = con.prepareStatement("SELECT COUNT(date_diff) AS occurrences FROM clients_movies WHERE client_id = ? AND date_diff > 2");
            preparedStatement1.setString(1, String.valueOf(clientId));

            ResultSet rs1 = preparedStatement1.executeQuery();

            if (rs.next() && rs1.next()) {
                int fines = rs.getInt("fines");
                int occurrences = rs1.getInt("occurrences");
                int result = fines - (2 * occurrences);
                System.out.printf("User number %s currently owns %s leva", clientId, result);

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public Movie closeConnection() {
        try (Connection con = getConnection()) {
            if (con != null) {
                System.out.println("Bye bye!");
                con.close();
            }
        } catch (Exception e) {
            //do nothing
        }
        return null;
    }
}

