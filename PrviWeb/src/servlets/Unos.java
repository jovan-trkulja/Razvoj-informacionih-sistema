package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Posetilac;

/**
 * Servlet implementation class Unos
 */
@WebServlet("/Unos")
public class Unos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Unos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Posetilac p = new Posetilac();
		
		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		
		p.setIme(ime);
		p.setPrezime(prezime);
		
		if(Controller.dodajPosetioca(p) != null) {
			response.getWriter().write("Uspesno ste upisali posetioca");
			response.getWriter().flush();
		} else {
			response.getWriter().write("Greska prilikom dodavanja");
			response.getWriter().flush();
		}
	}

}
