package web;

import java.util.ArrayList;
import java.util.List;
import metier.entities.Commande;

public class CommandeModele {
	List<Commande> commandes = new ArrayList<>();

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}
}