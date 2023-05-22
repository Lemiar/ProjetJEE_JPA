package web;

import java.io.IOException;
import java.util.List;
import org.apache.catalina.connector.Response;
import dao.InformationDaoImpl;
import dao.IInformationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.entities.Information;


@WebServlet (name="informationServ",urlPatterns= {"/informations","/saisieInformation",
		"/saveInformation","/supprimerInformation","/editerInformation","/updateInformation"})
public class InformationServlet extends HttpServlet {

	 IInformationDao metier;
	 @Override
	public void init() throws ServletException {

		metier = new InformationDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			             HttpServletResponse response) 
			            throws ServletException, IOException {
		String path=request.getServletPath();

		System.out.println("PATH "+path);
		if (path.equals("/informations"))
		{

		InformationModele model= new InformationModele();

		List<Information> informations = metier.getAllInformations();
		model.setInformations(informations);
		request.setAttribute("model", model);
		request.getRequestDispatcher("informations.jsp").forward(request,response);
		}

		else if (path.equals("/saisieInformation")  )
		{
			request.getRequestDispatcher("saisieInformation.jsp").forward(request,response);
		}

		else if (path.equals("/saveInformation")  && request.getMethod().equals("POST"))
		{
		    String street=request.getParameter("street");
		    String city=request.getParameter("city");
		    String country=request.getParameter("country");

		    Information information = metier.save(new Information(street,city,country));
			request.setAttribute("information", information);
			response.sendRedirect("informations");
		}
		else if (path.equals("/supprimerInformation"))
		{
		    Long id= Long.parseLong(request.getParameter("id"));
		    metier.deleteInformation(id);
		    response.sendRedirect("informations");		
		}
		else if (path.equals("/editerInformation")  )
		{
			Long id= Long.parseLong(request.getParameter("id"));
			Information information = metier.getInformation(id);
		    request.setAttribute("information", information);
			request.getRequestDispatcher("editerInformation.jsp").forward(request,response);
		}
		else if (path.equals("/updateInformation")  )
		{
			 Long id = Long.parseLong(request.getParameter("id"));
			 String street=request.getParameter("street");
			 String city=request.getParameter("city");
			 String country=request.getParameter("country");
			 Information information = new Information();
			 information.setId(id);
			 information.setStreet(street);
			 information.setCity(city);
			 information.setCountry(country);


		     metier.updateInformation(information);
			 response.sendRedirect("informations");		

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