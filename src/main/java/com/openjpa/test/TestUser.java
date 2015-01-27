package com.openjpa.test;

import com.openjpa.dao.UserDao;

import com.openjpa.entities.*;

import com.openjpa.utils.OpenJPAUtils;

public class TestUser {
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("user initialisation");
		
		System.out.println("userDao creation");
		
		UserDao userDao = new UserDao();
		
		System.out.println("new user persistence");
		
		OpenJPAUtils.setUp();
		
		// User user1 = new User("thomas", "thomas@ouioui.fr", "pass", "0608080465");
		
		// userDao.create(user1);
		
		System.out.println("findind user ...");
		
		User userFinded = userDao.findByUserID(7);
		
		System.out.println("user finded !");
		
		System.out.println(userFinded.toString());
		
	}
	
}
