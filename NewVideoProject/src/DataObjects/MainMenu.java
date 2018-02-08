package DataObjects;

import DataAccessLayer.BaseDAO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MainMenu extends BaseDAO {

    public MainMenu() {

    }

    Scanner sc = new Scanner(System.in);

    public User insertClient() {
        String sql = "INSERT INTO videostoredb.clients (first_name, last_name, address, email, password) VALUES (?, ?, ?, ?, ?)";

        User user = null;

        try (Connection con = getConnection();

             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.println("Please enter the client you would like to add and his/her first name, last name, address, email and password");

            String firstName = sc.next();
            String lastName = sc.next();
            String address = sc.next();
            String email = sc.next();
            String password = sc.next();

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, address);
            ps.setString(4, email);
            ps.setString(5, password);

            ps.executeUpdate();
            ps.close();

            System.out.printf("%s %s was added to our database", firstName, lastName);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User deleteClient() {
        String sql = "DELETE FROM videostoredb.clients WHERE client_id = ?";

        User user = null;

        try (Connection con = getConnection();

             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.println("Please enter the client id you want to remove");

            String userId = sc.next();

            ps.setString(1, userId);

            ps.executeUpdate();
            ps.close();

            System.out.printf("%s was removed from our database", userId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> selectClients() {

        List<User> users = new LinkedList<>();

        User user = null;


        try (Connection con = getConnection()) {

            Statement ps = con.createStatement();
            ResultSet resultSet = ps.executeQuery("SELECT * FROM videostoredb.clients");


            if (resultSet.next()) {
                user = new User();
                user.setClientId(resultSet.getString("client_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAddress(resultSet.getString("address"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));

                users.add(user);

                while (resultSet.next()) {
                    System.out.printf("%d %s %s %s %s %s %n",
                            resultSet.getInt("client_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("address"),
                            resultSet.getString("email"),
                            resultSet.getString("password"));

                }
            }
            resultSet.close();
            ps.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Movie insertMovie() {
        String sql = "INSERT INTO videostoredb.movies (title, year, movie_length) VALUES (?, ?, ?)";

        Movie movie = null;

        try (Connection con = getConnection();


             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.println("Please enter the movie you want to add, it's production year and length");

            String title = sc.next();
            int year = sc.nextInt();
            int movieLength = sc.nextInt();

            ps.setString(1, title);
            ps.setString(2, String.valueOf(year));
            ps.setString(3, String.valueOf(movieLength));

            ps.executeUpdate();
            ps.close();

            System.out.printf("%s was added to our database", title);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public Movie deleteMovie() {
        String sql = "DELETE FROM videostoredb.movies WHERE title = ?";

        Movie movie = null;

        try (Connection con = getConnection();

             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.println("Please enter the movie name you want to remove");

            String title = sc.next();

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

    public Movie rentMovie() {
        String sql = "INSERT INTO videostoredb.clients_movies (client_id, movie_id, rental_time, status) VALUES (?, ?, ?, ?)";

        Movie movie = null;

        try {
            String status = "taken";

            try (Connection con = getConnection();


                 PreparedStatement ps = con.prepareStatement(sql)) {

                System.out.println("Please enter the movie id, your id, the rental date (YYYY-MM-DD format) and the status of the movie");
                String movieId = sc.next();
                String clientId = sc.next();
                String rentalTime = "" + sc.next();

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

    public Movie returnMovie() {

        Movie movie = null;

        try (Connection con = getConnection()) {

            System.out.println("Please enter your id, the movie id and the return date");
            int clientId = sc.nextInt();
            int movieId = sc.nextInt();
            String returnTime = "" + sc.next();
            String dateDiff;

            PreparedStatement preparedStatement = con.prepareStatement("UPDATE videostoredb.clients_movies SET status = 'returned', return_time = ? WHERE client_id = ? AND movie_id = ?");
            preparedStatement.setString(1, returnTime);
            preparedStatement.setString(2, String.valueOf(clientId));
            preparedStatement.setString(3, String.valueOf(movieId));

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

    public Movie checkFines() {

        Movie movie = null;

        try (Connection con = getConnection()) {

            System.out.println("Please enter your client id: ");
            int clientId = sc.nextInt();

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

    public void startMenu() {
        try {
            System.out.println("Welcome to our video store! Please enter a number to select an action:");
            System.out.println("Press 1 to view all our movies");
            System.out.println("Press 2 to view all our customers");
            System.out.println("Press 3 to rent a movie");
            System.out.println("Press 4 to return a movie");
            System.out.println("Press 5 to check your fines");
            System.out.println("Press 6 to add a movie");
            System.out.println("Press 7 to add a client");
            System.out.println("Press 8 to remove a movie");
            System.out.println("Press 9 to remove a client");
            System.out.println("Press 0 to close our menu");

            Scanner sc = new Scanner(System.in);

            String action = sc.nextLine();

            switch (action) {
                case "1":
                    selectMovies();
                    break;

                case "2":
                    selectClients();
                    break;

                case "3":
                    rentMovie();
                    break;

                case "4":
                    returnMovie();
                    break;

                case "5":
                    checkFines();
                    break;

                case "6":
                    insertMovie();
                    break;

                case "7":
                    insertClient();
                    break;

                case "8":
                    deleteMovie();
                    break;

                case "9":
                    deleteClient();
                    break;

                case "0":
                    closeConnection();
                    break;


            }
        } catch (Exception e) {
            //do nothing
        }

    }
}
