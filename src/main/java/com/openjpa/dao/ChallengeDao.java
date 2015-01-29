package com.openjpa.dao;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.EntityTransaction;

import javax.persistence.TypedQuery;

import org.apache.commons.collections.ListUtils;

import com.openjpa.constant.Constants;

import com.openjpa.entities.Challenge;

import com.openjpa.utils.OpenJPAUtils;

@Stateless
public class ChallengeDao {

	public void create(Challenge challenge) throws Exception {

		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction challengeTransaction = em.getTransaction();

		challengeTransaction.begin();

		try{

			em.persist(challenge);

			challengeTransaction.commit();

		} catch(Exception e) {

			System.out.println("SQL[2000] constraint , challenge not created, see your SQL documentation for more details");

		}

		OpenJPAUtils.closeEntityManager(em);

	}

	public void update(Challenge challenge) throws Exception {

		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction challengeTransaction = em.getTransaction();

		challengeTransaction.begin();

		em.merge(challenge);

		challengeTransaction.commit();

		OpenJPAUtils.closeEntityManager(em);

	}
	
	public void updateWinner(Challenge challenge, Integer idWinner) throws Exception {

		challenge.setId_winner(idWinner);;

		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction challengeTransaction = em.getTransaction();

		challengeTransaction.begin();

		em.merge(challenge);

		challengeTransaction.commit();

		OpenJPAUtils.closeEntityManager(em);

	}

	public void updateStatut(Challenge challenge, Integer statut) throws Exception {

		challenge.setStatut(statut);

		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction challengeTransaction = em.getTransaction();

		challengeTransaction.begin();

		em.merge(challenge);

		challengeTransaction.commit();

		OpenJPAUtils.closeEntityManager(em);

	}

	public void setScore(Challenge challenge) throws Exception {

		UserDao userDao = new UserDao();

		if(challenge.getStatut() == Constants.CHALLENGE_CHECKED) {

			if(challenge.getId_winner() == -1) {

				userDao.updateScoreById(challenge.getId_contact(), 1);

				userDao.updateScoreById(challenge.getId_user(), 1);
			}
			else {

				userDao.updateScoreById(challenge.getId_winner(), 3);
			}
		}
		else {

			System.out.println("Challenge pas encore terminé");
		}

	}

	public Challenge findByID(Integer ID) throws Exception {

		EntityManager em = OpenJPAUtils.openEntityManager();

		Challenge result = em.find(Challenge.class, ID);

		OpenJPAUtils.closeEntityManager(em);

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Challenge> findByUserID(Integer ID) throws Exception {

		List<Challenge> result = null;

		EntityManager em = OpenJPAUtils.openEntityManager();

		try{

			TypedQuery<Challenge> query = em.createQuery(

					"SELECT c FROM Challenge c WHERE c.id_user = :id_user", Challenge.class);

			result = query.setParameter("id_user", ID).getResultList();

			TypedQuery<Challenge> query2 = em.createQuery(

					"SELECT c FROM Challenge c WHERE c.id_contact = :id_contact", Challenge.class);

			List<Challenge> result2 = query2.setParameter("id_contact", ID).getResultList();

			result = ListUtils.union(result, result2);

		}catch(Exception e){

			e.printStackTrace();
		}


		OpenJPAUtils.closeEntityManager(em);

		return result;

	}

	public Boolean ifChallengeExist(Challenge c) throws Exception {

		Challenge challenge;

		EntityManager em = OpenJPAUtils.openEntityManager();

		TypedQuery<Challenge> queryChallenge = em.createQuery(

				"SELECT c FROM Challenge c WHERE c.id = :id",Challenge.class);

		try{

			challenge = queryChallenge.setParameter("id",c.getId()).getSingleResult();

		}catch(Exception e){

			challenge = null;
		}

		OpenJPAUtils.closeEntityManager(em);

		Boolean result  = (challenge != null ) ? true  : false ; 

		return result;
	}

	public void deleteChallenge(Challenge challenge) throws Exception {
		
		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction challengeTransaction = em.getTransaction();

		challengeTransaction.begin();
		
		Challenge c = em.find(Challenge.class, challenge.getId());

		em.remove(c);

		challengeTransaction.commit();

		OpenJPAUtils.closeEntityManager(em);

	}

}
