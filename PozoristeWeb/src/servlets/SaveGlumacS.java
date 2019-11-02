package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.Managers;
import model.Glumac;
import model.Predstava;

/**
 * Servlet implementation class SaveGlumacS
 */
@WebServlet("/SaveGlumacS")
public class SaveGlumacS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveGlumacS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ime = request.getParameter("ime");
		String prez = request.getParameter("prez");
		String jmbg = request.getParameter("jmbg");
		
		Glumac g = Managers.saveGlumac(ime, prez, jmbg);
		
		if(g == null) {
			request.getSession().setAttribute("saveFailed", true);
			request.getRequestDispatcher("/straniceGlumac/saveGlumcaNovo.jsp").forward(request, response);
		} else {
			
			List<Predstava> lista = Managers.getPredstave();	
			request.getSession().setAttribute("glumac", g);
			request.getSession().setAttribute("predstave", lista);
			request.getRequestDispatcher("/straniceGlumac/saveGlumcaNovo.jsp").forward(request, response);
		}
	}

}
