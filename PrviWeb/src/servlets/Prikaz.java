package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Izvodjenje;

/**
 * Servlet implementation class Prikaz
 */
@WebServlet("/Prikaz")
public class Prikaz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prikaz() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String naz = request.getParameter("naziv");
		
		List<Izvodjenje> lista = Controller.prikaz(naz);
		
		StringBuilder sb = new StringBuilder();
		
		if(lista != null) {
			
			lista.forEach(e -> {
				sb.append(e.toString() + "\n");
			});
			
			response.getWriter().write(sb.toString());
			response.getWriter().flush();
			
		} else {
			
			response.getWriter().write("Greska prilikom prikazivanja predstava");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
