package Facades;

import DataAccessLayer.UserDAO;
import DataObjects.User;

import java.util.List;

public class UserFacade {

    private UserDAO userDAO = new UserDAO();

    public User insertUser(String firstName, String lastName, String address, String email, String password) {
        User user = userDAO.insertClient(firstName, lastName, address, email, password);

        return user;
    }

    public User deleteUser(String userId){
        User user = userDAO.deleteClient(userId);

        return user;
    }

    public List<User> listUsers(){
        List<User> users = userDAO.selectClients();

        return users;
    }

}
