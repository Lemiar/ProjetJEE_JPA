package metier.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Client { 
    @Id @GeneratedValue (strategy=GenerationType.IDENTITY)
	private long id_client;
	private String nom;
	private String prenom;
	
	 @OneToOne
	 public Information information; 
	
	public Client() {
		super();
	}

	
	

	public Client(String nom, String prenom,Information inf) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.setInformation(inf);
	}
	



	public Information getInformation() {
		return information;
	}




	public void setInformation(Information information) {
		this.information = information;
	}




	public long getId() {
		return id_client;
	}


	public void setId(long id_client) {
		this.id_client = id_client;
	}




	public String getNom() {
		return nom;
	}




	public void setNom(String nom) {
		this.nom = nom;
	}




	public String getPrenom() {
		return prenom;
	}




	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}




	@Override
	public String toString() {
		return "Client [id_client=" + id_client + ", nom=" + nom + ", prenom=" + prenom + "]";
	}



    


}
