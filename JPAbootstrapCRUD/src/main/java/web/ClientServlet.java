package web;

import java.io.IOException;
import java.util.List;
import org.apache.catalina.connector.Response;

import dao.CategorieDaoImpl;
import dao.ClientDaoImpl;
import dao.ICategorieDao;
import dao.IClientDao;
import dao.IInformationDao;
import dao.InformationDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.entities.Categorie;
import metier.entities.Client;
import metier.entities.Information;
import metier.entities.Produit;


@WebServlet (name="clientServ",urlPatterns= {"/clients","/saisieClient",
		"/saveClient","/supprimerClient","/editerClient","/updateClient"})
public class ClientServlet extends HttpServlet {

	 IClientDao metier;
	 IInformationDao metierInf;
	 @Override
	public void init() throws ServletException {

		metier = new ClientDaoImpl();
		metierInf = new InformationDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			             HttpServletResponse response) 
			            throws ServletException, IOException {
		String path=request.getServletPath();

		System.out.println("PATH "+path);
		if (path.equals("/clients"))
		{

		ClientModele model= new ClientModele();

		List<Client> clients = metier.getAllClients();
		model.setClients(clients);
		request.setAttribute("model", model);
		request.getRequestDispatcher("clients.jsp").forward(request,response);
		}

		else if (path.equals("/saisieClient")  )
		{
			List<Information> infs =metierInf.getAllInformations();
			InformationModele model=new InformationModele();
			model.setInformations(infs);
			request.setAttribute("infModel", model);
			
			request.getRequestDispatcher("saisieClient.jsp").forward(request,response);
			
			
		}

		else if (path.equals("/saveClient")  && request.getMethod().equals("POST"))
		{
		    String nom=request.getParameter("nom");
		    Long informationId =Long.parseLong(request.getParameter("information"));
		    String prenom=request.getParameter("prenom");
		    Information inf = metierInf.getInformation(informationId);

			Client client = metier.save(new Client(nom,prenom,inf));
			request.setAttribute("client", client);
			response.sendRedirect("clients");
			
		}
		else if (path.equals("/supprimerClient"))
		{
		    Long id= Long.parseLong(request.getParameter("id"));
		    metier.deleteClient(id);
		    response.sendRedirect("clients");
		}
		else if (path.equals("/editerClient")  )
		{
			Long id= Long.parseLong(request.getParameter("id"));
		    Client client = metier.getClient(id);
		    request.setAttribute("client", client);

		    List<Information> infs =metierInf.getAllInformations();
			InformationModele model=new InformationModele();
			model.setInformations(infs);
			request.setAttribute("infModel", model);
			request.getRequestDispatcher("editerClient.jsp").forward(request,response);
			
		}
		else if (path.equals("/updateClient")  )
		{
			 Long id = Long.parseLong(request.getParameter("id"));
			 String nom=request.getParameter("nom");
			 String prenom=request.getParameter("prenom");
			 Long informationId =Long.parseLong(request.getParameter("information"));
			 Client client = new Client();
			 client.setId(id);
			 client.setNom(nom);
			 client.setPrenom(prenom);

			 Information inf = metierInf.getInformation(informationId);
			 client.setInformation(inf);
		     metier.updateClient(client);
			 response.sendRedirect("clients");		
			 

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