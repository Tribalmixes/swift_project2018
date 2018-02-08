package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/videostoredb?useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    private Connection connection;

    public BaseDAO() {

    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection != null) {

            System.out.println("Connection successful!");

            return connection;
        }

        Class.forName("com.mysql.jdbc.Driver");

        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);


        return connection;

    }

}
