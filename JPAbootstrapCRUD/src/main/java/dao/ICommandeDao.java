package dao;

import java.util.List;
import metier.entities.Commande;

public interface ICommandeDao {

public Commande save(Commande commande);
public Commande getCommande(Long id);
public Commande updateCommande(Commande commande);
public void deleteCommande(Long id);
public List<Commande> getAllCommandes();
}