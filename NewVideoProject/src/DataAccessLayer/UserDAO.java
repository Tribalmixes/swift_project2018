package DataAccessLayer;

import DataObjects.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDAO extends BaseDAO {

    public UserDAO() {

    }

    public User findUser(String firstName, String lastName, String password) {
        String sql = "SELECT first_name, last_name FROM videostoredb.clients WHERE first_name = ? AND last_name = ? AND password = ?";

        User user = null;

        try (Connection con = getConnection();

             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String firstName1 = rs.getString("first_name");
                String lastName1 = rs.getString("last_name");

                user = new User(firstName1, lastName1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User insertClient(String firstName, String lastName, String address, String email, String password) {
        String sql = "INSERT INTO videostoredb.clients (first_name, last_name, address, email, password) VALUES (?, ?, ?, ?, ?)";

        User user = null;

        try (Connection con = getConnection();

             PreparedStatement ps = con.prepareStatement(sql)) {

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

    public User deleteClient(String userId) {
        String sql = "DELETE FROM videostoredb.clients WHERE client_id = ?";

        User user = null;

        try (Connection con = getConnection();

             PreparedStatement ps = con.prepareStatement(sql)) {

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

    

}
