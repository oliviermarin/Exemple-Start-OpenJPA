package com.openjpa.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;

import com.openjpa.constant.Constants;
import com.openjpa.dao.ChallengeDao;
import com.openjpa.entities.Challenge;

public class TreatChallengeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet( HttpServletRequest request, HttpServletResponse response ) 
	
			throws ServletException, IOException {
		
		// PrintWriter out = response.getWriter();
		
		// out.println("Hello world !");
		
		String function = request.getParameter("function");
		
		switch (function) {
		
		case "choiceChallenge":
			
			String choiceString = request.getParameter("choice");
			
			Boolean choice = Boolean.valueOf(choiceString);
			
			String idUserString = request.getParameter("idUser");
			
			Integer idUser = Integer.parseInt(idUserString);
			
			String idChallengeString = request.getParameter("idChallenge");
			
			Integer idChallenge = Integer.parseInt(idChallengeString);
			
			// out.println("choice : "+choice+" | idUser : "+idUser+" | idChallenge : "+idChallenge);
			
			ChallengeDao challengeDao = new ChallengeDao();
			
			Challenge challenge = null;
			
			try {
				
				challenge = challengeDao.findByID(idChallenge);
				
				challengeDao.updateStatut(challenge, Constants.CHALLENGE_ACCEPTED);
				
			} catch (Exception e) {

				e.printStackTrace();
				
			}
			
			break;

		default:
			
			break;
			
		}
		
		ServletContext contextPath = this.getServletContext();
		
		// String reportPath = contextPath.getRealPath("/test.jsp");
		
		// this.getServletContext().getRequestDispatcher( "/WEB-INF/test.jsp" ).forward( request, response );
		
		// response.sendRedirect("${pageContext.request.contextPath}/InitChallenge");
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/InitChallenge");
		
		dispatch.forward(request, response);
		
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) 
	
			throws ServletException, IOException {
		
		String contact = request.getParameter("contact");

		String challenge = request.getParameter("challenge");
		
		PrintWriter out = response.getWriter();
		
		out.println(contact);
		
		out.println(challenge);		
						
	}

}
