package grit.skane;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getLatAndLongServlet
 */
@WebServlet("/getLatAndLongServlet")
public class getLatAndLongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getLatAndLongServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//Code here 
		
		// Check if the right info got sent
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String address = request.getParameter("address");
		
		
		if(address.contains(",")) {	
				
			String[] parts = address.split(",");
			String longitude = parts[0];
			String latitude = parts[1];
			out.print("<br>");
			out.print("<br>");			
			out.print("<p>" + longitude+ "</p>");	
			out.print("<p>" + latitude+ "</p>");
			
		}else {
			out.println("Request not found!!! ");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
	}

}
