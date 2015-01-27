package com.openjpa.constant;

import java.util.HashMap;

public class Constants {
	
	//--------------------
	// constants for User entity
	//--------------------
	
	public static final 	Integer		NON_VUE 	= 10;
	
	public static final 	Integer		VUE 		= 10;
	
	public static final 	Integer		ACCEPTE 	= 10;
	
	public static final 	Integer		REFUSE 		= 10;
	
	//--------------------
	// constants for Challenge entity
	//--------------------
	
	public static final 	Integer		CHALLENGE_ON_HOLD 		= 10;
	
	public static final 	Integer		CHALLENGE_ACCEPTED 		= 20;
	
	public static final 	Integer		CHALLENGE_REFUSED 		= 30;
	
	public static final 	Integer		CHALLENGE_DONE 			= 40;
	
	public static final 	Integer		CHALLENGE_CHECKED		= 50;
	
	public static final 	HashMap<Integer, String> hashMapChallenge = new HashMap<Integer, String>();
	
	static{
		
		hashMapChallenge.put(10, "en attente");
		
		hashMapChallenge.put(20, "accepté");
		
		hashMapChallenge.put(30, "refusé");
		
		hashMapChallenge.put(40, "terminé");
		
		hashMapChallenge.put(50, "validé");
		
	}
	 
}
