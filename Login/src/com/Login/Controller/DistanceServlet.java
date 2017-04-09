package com.Login.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.Clustering.Location;

/**
 * Servlet implementation class DistanceServlet
 */
@WebServlet("/DistanceServlet")
public class DistanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DistanceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Location l1=new Location("1",Double.parseDouble(request.getParameter("lat1")),Double.parseDouble(request.getParameter("lng1")));
		Location l2=new Location("2",Double.parseDouble(request.getParameter("lat2")),Double.parseDouble(request.getParameter("lng2")));
		Double distance=l1.distanceTo(l2);
		//HttpSession session=request.getSession();
		request.setAttribute("distance", String.format("%.2f", distance));
		//response.sendRedirect("distanceCalculator.jsp");
		RequestDispatcher dispatcher=request.getRequestDispatcher("distanceCalculator.jsp");
		dispatcher.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
