package com.openjpa.test;

import java.util.List;

import com.openjpa.constant.Constants;

import com.openjpa.dao.ChallengeDao;

import com.openjpa.dao.UserDao;

import com.openjpa.entities.Challenge;

import com.openjpa.entities.User;

import com.openjpa.utils.OpenJPAUtils;

public class ChallengeTest {
	
	public static void main(String[] args) throws Exception {
		
		UserDao userDao = new UserDao();
		
		ChallengeDao challengeDao = new ChallengeDao();
		
		OpenJPAUtils.setUp();
		
		User user1 = new User("John", "John@ouioui.fr", "pass", "0608080465");
		
		User user2 = new User("Arthur", "Arthur@ouioui.fr", "pass", "0788888888");
		
		User user3 = new User("Louis", "Louis@ouioui.fr", "ssap", "0788888888");
		
		userDao.create(user1);
		
		userDao.create(user2);
		
		userDao.create(user3);
			
		Challenge challenge1 = new Challenge(user1.getId(), user2.getId(), Constants.CHALLENGE_ON_HOLD, -1);
		
		Challenge challenge2 = new Challenge(user2.getId(), user3.getId(), Constants.CHALLENGE_ON_HOLD, -1);
		
		challengeDao.create(challenge1);
		
		challengeDao.create(challenge2);
		
		System.out.println("IfChallengeExist ? : " + challengeDao.ifChallengeExist(challenge1));
		
		System.out.println("findByID : challenge1 : " + challengeDao.findByID(challenge1.getId()));
		
		List<Challenge> challengesByUserId = challengeDao.findByUserID(user2.getId());
		
		for(Challenge c : challengesByUserId) {
			
			System.out.println("FindByUserId Found : " + c);
			
		}
		
		System.out.println("Avant modif : " + challenge1);
		
		challenge1.setId_user(user3.getId());
		
		challengeDao.update(challenge1);
		
		challengeDao.updateStatut(challenge1, Constants.CHALLENGE_ACCEPTED);
		
		Challenge challengesById = challengeDao.findByID(challenge1.getId());
		
		System.out.println("Apres modif : " + challengesById);
		
		System.out.println("Actualisation Score ...");
		
		challenge2.setId_winner(user2.getId());
		
		challengeDao.update(challenge2);
		
		challengeDao.updateStatut(challenge2, Constants.CHALLENGE_CHECKED);
		
		challengeDao.setScore(challenge2);
		
		System.out.println("... Done");
		
		System.out.println("Suppression challenge ...");
		
		// challengeDao.deleteChallenge(challenge2);
		
		// userDao.delete(user3);
		
		// boolean isSuppr = challengeDao.ifChallengeExist(challenge2);
		
		// System.out.println("... " + isSuppr);
		
	}
	
}
