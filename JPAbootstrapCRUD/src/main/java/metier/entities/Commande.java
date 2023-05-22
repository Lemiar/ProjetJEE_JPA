package metier.entities;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Commande {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal( TemporalType.DATE )
	private Date dateDebut;
	

	@ManyToOne
    @JoinColumn (name="PROD_ID")
	private Produit produit;
	    
	@OneToOne
	public Client client; 
	
    

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Date getDateDebut() {
		return dateDebut;
	}



	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}


	public Produit getProduit() {
		return produit;
	}



	public void setProduit(Produit produit) {
		this.produit = produit;
	}

    

	public Client getClient() {
		return client;
	}



	public void setClient(Client client) {
		this.client = client;
	}



	public Commande() {
		super();
	}
	
	
	public Commande(Date dateDebut, Client client, Produit produit) {
		super();
		this.dateDebut = dateDebut;
		this.setClient(client);
		this.setProduit(produit);
	}



	@Override
	public String toString() {
		return "Commande [ dateDebut=" + dateDebut + "]";
	}

     
	

	
    

}
