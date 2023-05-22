package metier.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Produit { 
    @Id @GeneratedValue (strategy=GenerationType.IDENTITY)
	private long id_produit;
	private String nom;
	private double prix;
	
	@ManyToOne
	@JoinColumn (name="CAT_ID")
	private Categorie categorie;

	@OneToMany (mappedBy="produit") 
	private List<Commande> commandes;
	
	public Produit() {
		super();
	}

	 public Produit(String nom, double prix, Categorie cat) {
			super();
			this.nom = nom;
			this.prix = prix;
			this.setCategorie(cat);
		}

	public long getId() {
		return id_produit;
	}

	public void setId(long id_produit) {
		this.id_produit = id_produit;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	@Override
	public String toString() {
		return "Produit [id_produit=" + id_produit +  ", nom=" + nom + ", prix=" + prix + "]";
	}
	


}
