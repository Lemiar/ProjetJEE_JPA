package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Commande;
import util.JPAutil;

public class CommandeDaoImpl implements ICommandeDao {

	private EntityManager entityManager=JPAutil.getEntityManager("JPAbootstrapCRUD");

	@Override
	public Commande save(Commande commande ) {
		EntityTransaction tx = entityManager.getTransaction();
	 	tx.begin();
	 	entityManager.persist(commande);
	 	tx.commit();

		return commande;
	}

	@Override
	public Commande getCommande(Long id) {
     	return entityManager.find(Commande.class, id);
	}

	@Override
	public Commande updateCommande(Commande commande) {
		EntityTransaction tx = entityManager.getTransaction();
	 	tx.begin();
	 	entityManager.merge(commande);
	 	tx.commit();
		return commande;
	}

	@Override
	public void deleteCommande(Long id) {
		Commande commande = entityManager.find(Commande.class, id);

		 entityManager.getTransaction().begin();
		 entityManager.remove(commande);
		 entityManager.getTransaction().commit();

	}

	@Override
	public List<Commande> getAllCommandes() {
		List<Commande> commandes =
        entityManager.createQuery("select c from Commande c").getResultList();

	return commandes;
	}

}