package com.openjpa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import com.openjpa.constant.Constants;
import com.openjpa.dao.ChallengeDao;
import com.openjpa.dao.UserDao;
import com.openjpa.entities.Challenge;
import com.openjpa.entities.User;

public class TreatChallengeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet( HttpServletRequest request, HttpServletResponse response ) 
	
			throws ServletException, IOException {
		
		// PrintWriter out = response.getWriter();
		
		// out.println("Hello world !");
		
		String function = request.getParameter("function");
		
		switch (function) {

		case "choiceChallenge": {

			System.out.println("choiceChallenge ...");

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

				if(choice) {

					challengeDao.updateStatut(challenge, Constants.CHALLENGE_ACCEPTED);
				}
				else {

					challengeDao.updateStatut(challenge, Constants.CHALLENGE_REFUSED);	
				}

			} catch (Exception e) {

				e.printStackTrace();

			}

			break;
		}

		case "resultatChallenge": {

			System.out.println("ResultatChallenge ...");

			String choiceString = request.getParameter("choice");
			String idUserString = request.getParameter("idUser");

			Integer idUser = Integer.parseInt(idUserString);

			String idChallengeString = request.getParameter("idChallenge");

			Integer idChallenge = Integer.parseInt(idChallengeString);

			// out.println("choice : "+choice+" | idUser : "+idUser+" | idChallenge : "+idChallenge);

			ChallengeDao challengeDao = new ChallengeDao();

			Challenge challenge = null;

			try {

				challenge = challengeDao.findByID(idChallenge);

				if(choiceString.equals("gagne")) {

					challengeDao.updateWinner(challenge, challenge.getId_user());
				}
				else if(choiceString.equals("nul")){

					challengeDao.updateWinner(challenge, -1);
				}
				else if(choiceString.equals("perdu")){

					challengeDao.updateWinner(challenge, challenge.getId_contact());	
				}
				else {

					System.out.println("challenge.getId_user() : " + challenge.getId_user() + " challenge.getId_contact() : " + challenge.getId_contact());
				}

				challengeDao.updateStatut(challenge, Constants.CHALLENGE_DONE);

			} catch (Exception e) {

				e.printStackTrace();

			}

			break;
		}

		case "validationChallenge": {

			System.out.println("validationChallenge ...");

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

				if(choice) {
					
					challengeDao.updateStatut(challenge, Constants.CHALLENGE_CHECKED);
					
					challengeDao.setScore(challenge);
				}
				else {

					challengeDao.deleteChallenge(challenge);
				}

			} catch (Exception e) {

				e.printStackTrace();

			}

			break;
		}
		default:

			System.out.println("Default ...");

			break;

		}

		
		// ServletContext contextPath = this.getServletContext();
		
		// String reportPath = contextPath.getRealPath("/test.jsp");
		
		// this.getServletContext().getRequestDispatcher( "/WEB-INF/test.jsp" ).forward( request, response );
		
		// response.sendRedirect("${pageContext.request.contextPath}/InitChallenge");
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/InitChallenge");
		
		dispatch.forward(request, response);
		
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) 
	
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String contactFinal = request.getParameter("contactFinal");
		
		String challengeName = request.getParameter("challenge");
		
		String pseudoUser = request.getParameter("pseudoUser");
		
		UserDao userDao = new UserDao();
		
		User user = new User();
		
		Challenge challenge = new Challenge();
		
		User contact = new User();
		
		try {
			
			user = userDao.findByPseudo(pseudoUser);
			
			contact = userDao.findByPseudo(contactFinal);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		challenge.setDescription(challengeName);
		
		challenge.setId_user(user.getId());
		
		challenge.setId_contact(contact.getId());
		
		challenge.setId_winner(null);
		
		challenge.setStatut(Constants.CHALLENGE_ON_HOLD);
		
		ChallengeDao challengeDao = new ChallengeDao(); 
		
		try {
			
			challengeDao.create(challenge);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		// PrintWriter out = response.getWriter();
		
		// out.println(challengeName);
		
		// out.println(challengeNameGood);
		
		// out.println(user.toString());
		
		// out.println(contact.toString());
		
		// out.println(challenge.toString());
		
		// ServletContext contextPath = this.getServletContext();
		
		response.sendRedirect("InitChallenge");
						
	}

}
