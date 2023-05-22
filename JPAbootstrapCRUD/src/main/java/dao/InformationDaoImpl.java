package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Information;
import util.JPAutil;

public class InformationDaoImpl implements IInformationDao {
 // TP6_JEE à replacer par votre persistence unit, consultez votre
//fichier persistence.xml <persistence-unit name="TP6_JEE">
	private EntityManager entityManager=JPAutil.getEntityManager("JPAbootstrapCRUD");

	@Override
	public Information save(Information information ) {
		EntityTransaction tx = entityManager.getTransaction();
	 	tx.begin();
	 	entityManager.persist(information);
	 	tx.commit();

		return information;
	}

	@Override
	public Information getInformation(Long id) {
     	return entityManager.find(Information.class, id);
	}

	@Override
	public Information updateInformation(Information information) {
		EntityTransaction tx = entityManager.getTransaction();
	 	tx.begin();
	 	entityManager.merge(information);
	 	tx.commit();
		return information;
	}

	@Override
	public void deleteInformation(Long id) {
		Information information = entityManager.find(Information.class, id);

		 entityManager.getTransaction().begin();
		 entityManager.remove(information);
		 entityManager.getTransaction().commit();

	}

	@Override
	public List<Information> getAllInformations() {
		List<Information> informations =
        entityManager.createQuery("select c from Information c").getResultList();

	return informations;
	}

}