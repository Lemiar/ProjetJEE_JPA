package dao;

import java.util.List;
import metier.entities.Client;

public interface IClientDao {

public Client save(Client client);
public Client getClient(Long id);
public Client updateClient(Client client);
public void deleteClient(Long id);
public List<Client> getAllClients();
}