package backend.Classes;

import backend.DAO.ClientDAO;
import backend.DAO.MovieDAO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class JDBCMovieDAO implements MovieDAO, ClientDAO {

    private static final String DMBS_CONN_STRING = "jdbc:mysql://localhost:3306/videostoredb?useSSL=true";
    private static final String DBMS_USERNAME = "root";
    private static final String DBMS_PASSWORD = "1234";

    Connection connection;

    Scanner sc = new Scanner(System.in);

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DMBS_CONN_STRING, DBMS_USERNAME, DBMS_PASSWORD);
            System.out.println("Data connection success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }

    @Override
    public void insertMovie() {
        try {
            System.out.println("Please enter the movie you want to add, it's production year and length");

            String title = sc.next();
            int year = sc.nextInt();
            int movieLength = sc.nextInt();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO videostoredb.movies (title, year, movie_length) VALUES (?, ?, ?)");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, String.valueOf(year));
            preparedStatement.setString(3, String.valueOf(movieLength));

            preparedStatement.executeUpdate();
            preparedStatement.close();

            System.out.printf("%s was added to our database", title);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMovie() {
        try {
            System.out.println("Please enter the movie you want to remove");

            String title = sc.next();

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM videostoredb.movies WHERE title = ?");
            preparedStatement.setString(1, title);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            System.out.printf("%s was removed from our database", title);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Movie> selectMovies() {

        List<Movie> movies = new LinkedList<>();

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM videostoredb.movies");

            Movie movie = null;
            if (resultSet.next()) {
                movie = new Movie();
                movie.setMovie_id(Integer.parseInt(resultSet.getString("movie_id")));
                movie.setTitle((resultSet.getString("title")));
                movie.setYear(Integer.parseInt(resultSet.getString("year")));
                movie.setMovie_length(Integer.parseInt(resultSet.getString("movie_length")));

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public void insertClient() {
        try {
            System.out.println("Please enter the client you would like to add and his/her first name, last name, address and email");

            String firstName = sc.next();
            String lastName = sc.next();
            String address = sc.next();
            String email = sc.next();


            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO videostoredb.clients (first_name, last_name, address, email) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, email);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            System.out.printf("%s %s was added to our database", firstName, lastName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClient() {
        try {
            System.out.println("Please enter the client you want to remove");

            String clientId = sc.next();

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM videostoredb.clients WHERE client_id = ?");
            preparedStatement.setString(1, clientId);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            System.out.printf("%s was removed from our database", clientId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> selectClients() {

        List<Client> clients = new LinkedList<>();

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM videostoredb.clients");

            Client client = null;
            if (resultSet.next()) {
                client = new Client();
                client.setClientId(Integer.parseInt(resultSet.getString("client_id")));
                client.setFirstName((resultSet.getString("first_name")));
                client.setLastName((resultSet.getString("last_name")));
                client.setAddress((resultSet.getString("address")));
                client.setEmail(resultSet.getString("email"));

                clients.add(client);

                while (resultSet.next()) {
                    System.out.printf("%d %s %s %s %s %n",
                            resultSet.getInt("client_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("address"),
                            resultSet.getString("email"));
                }
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;


    }

    @Override
    public void rentMovie() {
        try {
            System.out.println("Please enter the movie id, your id, the rental date (YYYY-MM-DD format) and the status of the movie");
            int movieId = sc.nextInt();
            int clientId = sc.nextInt();
            String rentalTime = "" + sc.next();
            String status = "taken";

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO videostoredb.clients_movies (client_id, movie_id, rental_time, status) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, String.valueOf(movieId));
            preparedStatement.setString(2, String.valueOf(clientId));
            preparedStatement.setString(3, rentalTime);
            preparedStatement.setString(4, status);

            System.out.printf("Movie %d was rented by client %d on %s and is currently %s", movieId, clientId, rentalTime, status);

            preparedStatement.execute();

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnMovie() {
        try {
            System.out.println("Please enter the movie id, your id and the return date");
            int movieId = sc.nextInt();
            int clientId = sc.nextInt();
            String returnTime = "" + sc.next();
            String dateDiff;

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE videostoredb.clients_movies SET status = 'returned', return_time = ? WHERE client_id = ? AND movie_id = ?");
            preparedStatement.setString(1, String.valueOf(returnTime));
            preparedStatement.setString(2, String.valueOf(clientId));
            preparedStatement.setString(3, String.valueOf(movieId));

            preparedStatement.execute();

            PreparedStatement preparedStatement1 = connection.prepareStatement("UPDATE clients_movies SET date_diff = datediff(return_time, rental_time) WHERE return_time IS NOT NULL AND rental_time IS NOT NULL;");

            preparedStatement1.execute();

            preparedStatement.close();
            preparedStatement1.close();

            System.out.printf("Movie %d was returned by client %d on %s", movieId, clientId, returnTime);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkFines() {

        try {
            System.out.println("Please enter your client id: ");
            int clientId = sc.nextInt();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(date_diff) AS Fines FROM clients_movies WHERE client_id = ? AND date_diff > 5");
            preparedStatement.setString(1, String.valueOf(clientId));

            ResultSet rs = preparedStatement.executeQuery();

            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT COUNT(date_diff) AS occurrences FROM clients_movies WHERE client_id = ? AND date_diff > 5");
            preparedStatement1.setString(1, String.valueOf(clientId));

            ResultSet rs1 = preparedStatement1.executeQuery();

            if (rs.next() && rs1.next()) {
                int fines = rs.getInt("fines");
                int occurrences = rs1.getInt("occurrences");
                int result = fines - (5 * occurrences);
                System.out.println(result);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            //do nothing
        }
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