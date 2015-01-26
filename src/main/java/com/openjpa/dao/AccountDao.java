package com.openjpa.dao;

import javax.ejb.Stateless;

import javax.persistence.*;

import com.openjpa.entities.Account;

@Stateless
public class AccountDao {
	
	public void createNewAccount(Account currentAccount) {
		
		EntityManagerFactory accountManagerFactory = Persistence.createEntityManagerFactory("account");
		
		EntityManager accountManager = accountManagerFactory.createEntityManager();
		
		EntityTransaction accountTransaction = accountManager.getTransaction();
		
		accountTransaction.begin();
		
		accountManager.persist(currentAccount);
		
		accountTransaction.commit();
		
		accountManager.close();
		
		accountManagerFactory.close();
		
	}
	
	public Account getAccountById(Integer accountId) {
		
		Account accountFinded = null;
		
		EntityManagerFactory accountManagerFactory = Persistence.createEntityManagerFactory("account");
		
		EntityManager accountManager = accountManagerFactory.createEntityManager();
		
		EntityTransaction accountTransaction = accountManager.getTransaction();
		
		accountTransaction.begin();
		
		accountFinded = accountManager.find(Account.class, accountId);
		
		accountTransaction.commit();
		
		accountManager.close();
		
		accountManagerFactory.close();
		
		return accountFinded;
		
	}
	
	public Account updateAccount(Account currentAccount) {
		
		Account accountUpdated = null;
		
		EntityManagerFactory accountManagerFactory = Persistence.createEntityManagerFactory("account");
		
		EntityManager accountManager = accountManagerFactory.createEntityManager();
		
		accountManager.getTransaction().begin();
		
		accountUpdated = accountManager.find(Account.class, currentAccount.getId());
		
		accountUpdated.setName(currentAccount.getName());
				
		accountManager.getTransaction().commit();
		
		accountManagerFactory.close();
		
		return accountUpdated;
		
	}
	
	public void deleteAccount(Account currentAccount) {
		
		EntityManagerFactory accountManagerFactory = Persistence.createEntityManagerFactory("account");
		
		EntityManager accountManager = accountManagerFactory.createEntityManager();
		
		accountManager.getTransaction().begin();
		
		currentAccount = accountManager.merge(currentAccount);
		
		accountManager.remove(currentAccount);
				
		accountManager.getTransaction().commit();
		
		accountManagerFactory.close();
		
	}
	
}
