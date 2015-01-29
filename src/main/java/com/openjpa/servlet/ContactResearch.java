package com.openjpa.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.openjpa.dao.UserDao;
import com.openjpa.entities.User;

public class ContactResearch extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) 
	
			throws ServletException, IOException {
		
		UserDao userDao = new UserDao();
		
		List<User> users = null;
		
		try {
			
			users = userDao.getAll();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		ArrayList<String> nameTable = new ArrayList<String>();
		
		for (User user : users) {
			
			nameTable.add(user.getPseudo()); 
			
		}
		
		Gson gson = new Gson();
		 
		String result = gson.toJson(nameTable);;
		
		 response.setContentType("text/plain");
		    
		 response.setCharacterEncoding("UTF-8");
		    
		 response.getWriter().write(result); 
		 
	}

}
