package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.Managers;
import model.Glumac;
import model.Predstava;
import model.Uloga;

/**
 * Servlet implementation class PredstavaGlumacS
 */
@WebServlet("/PredstavaGlumacS")
public class PredstavaGlumacS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PredstavaGlumacS() {
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
		
		Integer predstava = Integer.parseInt(request.getParameter("predstave"));
		String uloga = request.getParameter("uloga");
		Glumac g = (Glumac)request.getSession().getAttribute("glumac");
		Predstava p = Managers.findPredstava(predstava);
		
		Uloga u = Managers.saveUloga(uloga, g, p);
		
		if(u == null) {
			request.getSession().setAttribute("nijeSacuvano", true);
			request.getRequestDispatcher("/straniceGlumac/saveGlumcaNovo.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("uloga", u);
			request.getRequestDispatcher("/straniceGlumac/saveGlumcaNovo.jsp").forward(request, response);
		}
		
		
	}

}
