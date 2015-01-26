package com.openjpa.test;

import com.openjpa.dao.AccountDao;

import com.openjpa.entities.Account;

public class AccountTest {
	
	public static void main(String[] args) {
		
		//-----------------
		// Please remove account before launching test
		//-----------------
		
		System.out.println("0001 accountTest INFO [main] AccountTest - test beginig ...");
		
		//-----------------
		// Creation of new account
		//-----------------
		
		System.out.println("0001 accountTest INFO [main] AccountDAO - creating a new Account");
		
		Account accountOne 		= new Account("Vincent");
		
		Account accountTwo 		= new Account("Olivier");
		
		Account accountThree 	= new Account("Philipe");
		
		//-----------------
		// Persisting of new account
		//-----------------
		
		System.out.println("0002 accountTest INFO [main] AccountTest - creating a new AccountDao");
		
		AccountDao accountDao = new AccountDao();
		
		System.out.println("0003 accountTest INFO [main] AccountTest - Persisting entity account");
		
		accountDao.createNewAccount(accountOne);
		
		accountDao.createNewAccount(accountTwo);
		
		accountDao.createNewAccount(accountThree);
		
		//-----------------
		// get a persisted account by id
		//-----------------
		
		System.out.println("0004 accountTest INFO [main] AccountTest - finding account created 2");
		
		Account accountfinded = accountDao.getAccountById(2);
		
		System.out.println("0007 accountTest INFO [main] AccountTest - account finded : "+accountfinded.toString());
		
		//-----------------
		// updating account
		//-----------------
		
		accountfinded.setName("Marc");
		
		accountfinded = accountDao.updateAccount(accountfinded);
		
		System.out.println("0007 accountTest INFO [main] AccountTest - account finded : "+accountfinded.toString());

		//-----------------
		// removing account
		//-----------------
		
		System.out.println("0005 accountTest INFO [main] AccountTest - finding removed");
		
		// Account accountRemoved = accountDao.getAccountById(3);
		
		// System.out.println("0006 accountTest INFO [main] AccountTest - account finded : "+accountRemoved.toString());
		
		// accountDao.deleteAccount(accountRemoved);
		
		System.out.println("0008 accountTest INFO [main] AccountTest - account removed !");
		
		System.out.println("0001 accountTest INFO [main] AccountTest - test ending !");
		
	}
		
}