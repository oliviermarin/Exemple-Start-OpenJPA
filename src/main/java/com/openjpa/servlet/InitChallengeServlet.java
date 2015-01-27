package com.openjpa.servlet;

import java.io.IOException;

import java.util.ArrayList;

import javax.inject.Inject;

import javax.servlet.*;

import javax.servlet.http.*;

import com.openjpa.dao.*;

import com.openjpa.entities.*;

import com.openjpa.utils.OpenJPAUtils;

public class InitChallengeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserDao userDao;
	
	@Inject
	private ChallengeDao challengeDao;
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) 
			
			throws ServletException, IOException {
		
		User userFinded = null;
		
		ArrayList<Challenge> challenges = null;
		
		try {
			
			OpenJPAUtils.setUp();
				
			userFinded = userDao.findByUserID(2);
			
			challenges = (ArrayList<Challenge>)challengeDao.findByUserID(2);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} 
			
		request.setAttribute("userFinded", userFinded);
		
		request.setAttribute("challenges", challenges);
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/test.jsp" ).forward( request, response );
				
	}

}
