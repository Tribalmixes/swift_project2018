package backend.DAO;

import backend.Classes.Client;

import java.util.List;

public interface ClientDAO {

    List<Client> selectClients();
    void insertClient();
    void deleteClient();
}
