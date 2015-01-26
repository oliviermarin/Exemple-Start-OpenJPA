package com.openjpa.servlet;

import java.io.IOException;

// import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openjpa.dao.AccountDao;
import com.openjpa.entities.Account;

public class AccountServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AccountDao accountDao;
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) 
			
			throws ServletException, IOException {
		
		Account account = new Account("François");
		
		// AccountDao accountDao = new AccountDao();
		
		accountDao.createNewAccount(account);	
		
	}
	
}
