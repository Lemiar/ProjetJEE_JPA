package web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.catalina.connector.Response;


import dao.ClientDaoImpl;
import dao.CommandeDaoImpl;
import dao.IClientDao;
import dao.ICommandeDao;
import dao.IProduitDao;
import dao.ProduitDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.entities.Client;
import metier.entities.Commande;
import metier.entities.Produit;


@WebServlet (name="comServ",urlPatterns= {"/commandes","/saisieCommande",
		"/saveCommande","/supprimerCommande","/editerCommande","/updateCommande"})
public class CommandeServlet extends HttpServlet {

	 ICommandeDao metier;
	 IClientDao metierClt;
	 IProduitDao metierProd;
	 @Override
	public void init() throws ServletException {

		metier = new CommandeDaoImpl();
		metierClt = new ClientDaoImpl();
		metierProd = new ProduitDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			             HttpServletResponse response) 
			            throws ServletException, IOException {
		String path=request.getServletPath();

		System.out.println("PATH "+path);
		if (path.equals("/commandes"))
		{

		CommandeModele model= new CommandeModele();

		List<Commande> commandes = metier.getAllCommandes();
		model.setCommandes(commandes);
		request.setAttribute("model", model);
		request.getRequestDispatcher("commandes.jsp").forward(request,response);
		}

		else if (path.equals("/saisieCommande")  )
		{
			List<Client> clients =metierClt.getAllClients();
			ClientModele model=new ClientModele();
			model.setClients(clients);
			request.setAttribute("cltModel", model);
			
			List<Produit> produits =metierProd.getAllProduits();
			ProduitModele model1=new ProduitModele();
			model1.setProduits(produits);
			request.setAttribute("prodModel", model1);
			
			request.getRequestDispatcher("saisieCommande.jsp").forward(request,response);
		}

		else if (path.equals("/saveCommande")  && request.getMethod().equals("POST"))
		{
			
			Long clientId =Long.parseLong(request.getParameter("client"));
			Long produitId =Long.parseLong(request.getParameter("produit"));
			Date dateDebut= new Date();
		    String pattern = "yyyy-MM-dd";
		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		    try {
		    	dateDebut = simpleDateFormat.parse(request.getParameter("dateDebut"));
			} catch (ParseException e) {

				e.printStackTrace();
			}
		    Client clt = metierClt.getClient(clientId);
		    Produit prod = metierProd.getProduit(produitId);

		    Commande commande = metier.save(new Commande(dateDebut,clt,prod));
			request.setAttribute("commande", commande);
			response.sendRedirect("commandes");
		}
		else if (path.equals("/supprimerCommande"))
		{
		    Long id= Long.parseLong(request.getParameter("id"));
		    metier.deleteCommande(id);
		    response.sendRedirect("commandes");		
		}
		else if (path.equals("/editerCommande")  )
		{
			Long id= Long.parseLong(request.getParameter("id"));
			Commande commande = metier.getCommande(id);
		    request.setAttribute("commande", commande);
		    
		    List<Client> clts =metierClt.getAllClients();
		    List<Produit> prods =metierProd.getAllProduits();
			ClientModele model=new ClientModele();
			ProduitModele model1=new ProduitModele();
			model.setClients(clts);
			model1.setProduits(prods);
			request.setAttribute("cltModel", model);
			request.setAttribute("prodModel", model1);
		    
			request.getRequestDispatcher("editerCommande.jsp").forward(request,response);
			
		}
		else if (path.equals("/updateCommande")  )
		{
			 Date dateDebut= new Date();
			 Long id = Long.parseLong(request.getParameter("id"));
			 Long clientId =Long.parseLong(request.getParameter("client"));
			 Long produitId =Long.parseLong(request.getParameter("produit"));
			 Commande commande = new Commande();
			 commande.setId(id);
			 String pattern = "yyyy-MM-dd";
			 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

			    try {
			    	dateDebut = simpleDateFormat.parse(request.getParameter("dateDebut"));
				} catch (ParseException e) {

					e.printStackTrace();
				}
			 commande.setDateDebut(dateDebut);
			 Client clt = metierClt.getClient(clientId);
			 Produit prod = metierProd.getProduit(produitId);
			 commande.setClient(clt);
			 commande.setProduit(prod);

		     metier.updateCommande(commande);
			 response.sendRedirect("commandes");	

		}
		else
		{
			response.sendError(Response.SC_NOT_FOUND);		
		}	
	}
	@Override
	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}}