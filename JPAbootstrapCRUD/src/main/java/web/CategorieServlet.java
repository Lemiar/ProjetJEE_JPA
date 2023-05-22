package web;

import java.io.IOException;
import java.util.List;
import org.apache.catalina.connector.Response;
import dao.CategorieDaoImpl;
import dao.ICategorieDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.entities.Categorie;


@WebServlet (name="catServ",urlPatterns= {"/categories","/saisieCategorie",
		"/saveCategorie","/supprimerCat","/editerCat","/updateCat"})
public class CategorieServlet extends HttpServlet {

	 ICategorieDao metier;
	 @Override
	public void init() throws ServletException {

		metier = new CategorieDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			             HttpServletResponse response) 
			            throws ServletException, IOException {
		String path=request.getServletPath();

		System.out.println("PATH "+path);
		if (path.equals("/categories"))
		{

		CategorieModele model= new CategorieModele();

		List<Categorie> cats = metier.getAllCategories();
		model.setCategories(cats);
		request.setAttribute("model", model);
		request.getRequestDispatcher("categories.jsp").forward(request,response);
		}

		else if (path.equals("/saisieCategorie")  )
		{
			request.getRequestDispatcher("saisieCategorie.jsp").forward(request,response);
		}

		else if (path.equals("/saveCategorie")  && request.getMethod().equals("POST"))
		{
		    String nom=request.getParameter("nom");

			Categorie cat = metier.save(new Categorie(nom));
			request.setAttribute("categorie", cat);
			response.sendRedirect("categories");
		}
		else if (path.equals("/supprimerCat"))
		{
		    Long id= Long.parseLong(request.getParameter("id"));
		    metier.deleteCategorie(id);
		    response.sendRedirect("categories");		
		}
		else if (path.equals("/editerCat")  )
		{
			Long id= Long.parseLong(request.getParameter("id"));
		    Categorie cat = metier.getCategorie(id);
		    request.setAttribute("categorie", cat);
			request.getRequestDispatcher("editerCategorie.jsp").forward(request,response);
		}
		else if (path.equals("/updateCat")  )
		{
			 Long id = Long.parseLong(request.getParameter("id"));
			 String nom=request.getParameter("nom");
			 Categorie cat = new Categorie();
			 cat.setId(id);
			 cat.setLibelle(nom);


		     metier.updateCategorie(cat);
			 response.sendRedirect("categories");		

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