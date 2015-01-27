package com.openjpa.servlet;

import java.io.IOException;

import javax.inject.Inject;

import javax.servlet.*;

import javax.servlet.http.*;

import com.openjpa.dao.AccountDao;

import com.openjpa.entities.Account;

public class AccountServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AccountDao accountDao;
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) 
			
			throws ServletException, IOException {
		
		//--------------------
		// three accounts creation
		//--------------------
		
		Account accountOne 		= new Account("Olivier");
		
		Account accountTwo 		= new Account("Vincent");
		
		Account accountThree 	= new Account("Claude");
		
		//--------------------
		// Persisting new account
		//--------------------
		
		accountDao.createNewAccount(accountOne);
		
		accountDao.createNewAccount(accountTwo);
		
		accountDao.createNewAccount(accountThree);
		
		//--------------------
		// getting the first account
		//--------------------		
		
		Account accountFinded = accountDao.getAccountById(1);
		
		System.out.println("0001 accountTest INFO [main] AccountTest - account finded : "+accountFinded.toString());
		
		accountFinded.setName("Jean");
		
		accountDao.updateAccount(accountFinded);
		
		//--------------------
		// remove the third account
		//--------------------
		
		accountFinded = accountDao.getAccountById(2);
		
		request.setAttribute("accountFinded", accountFinded);
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/challenge-user.jsp" ).forward( request, response );
		
		// accountDao.deleteAccount(accountFinded);
		
	}
	
}
