package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Client;
import util.JPAutil;

public class ClientDaoImpl implements IClientDao {
 // TP6_JEE à replacer par votre persistence unit, consultez votre
//fichier persistence.xml <persistence-unit name="TP6_JEE">
	private EntityManager entityManager=JPAutil.getEntityManager("JPAbootstrapCRUD");

	@Override
	public Client save(Client client ) {
		EntityTransaction tx = entityManager.getTransaction();
	 	tx.begin();
	 	entityManager.persist(client);
	 	tx.commit();

		return client;
	}

	@Override
	public Client getClient(Long id) {
     	return entityManager.find(Client.class, id);
	}

	@Override
	public Client updateClient(Client client) {
		EntityTransaction tx = entityManager.getTransaction();
	 	tx.begin();
	 	entityManager.merge(client);
	 	tx.commit();
		return client;
	}

	@Override
	public void deleteClient(Long id) {
		Client client = entityManager.find(Client.class, id);

		 entityManager.getTransaction().begin();
		 entityManager.remove(client);
		 entityManager.getTransaction().commit();

	}

	@Override
	public List<Client> getAllClients() {
		List<Client> clients =
        entityManager.createQuery("select c from Client c").getResultList();

	return clients;
	}

}