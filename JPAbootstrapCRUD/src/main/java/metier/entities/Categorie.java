package metier.entities;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Categorie {
	@Id @GeneratedValue (strategy=GenerationType.IDENTITY)
	private long id_categorie;
	private String libelle;
	

	@OneToMany (mappedBy="categorie") 
	private List<Produit> produits;

	public Categorie() {
		super();
	}
	
	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
	}

	public long getId() {
		return id_categorie;
	}

	public void setId(long id_categorie) {
		this.id_categorie = id_categorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	@Override
	public String toString() {
		return "Categorie [id_categorie=" + id_categorie + ", libelle=" + libelle + "]";
	}
	
	
    

}
