package com.openjpa.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OpenJPAUtils {

	private static EntityManagerFactory entityManagerFactory;

	public static void setUp() throws Exception {

		OpenJPAUtils.entityManagerFactory = Persistence.createEntityManagerFactory("account");
		
	}
	
	
	public static EntityManager openEntityManager() throws Exception {
		
		EntityManager entityManager = OpenJPAUtils.entityManagerFactory.createEntityManager();
		
		return entityManager;

	}
	
	public static void closeEntityManager(EntityManager entityManager) throws Exception {

		entityManager.close();

	}
	
	public static void tearDown() throws Exception {

		OpenJPAUtils.entityManagerFactory.close();

	}

}
