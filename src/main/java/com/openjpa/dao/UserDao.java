package com.openjpa.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.openjpa.entities.Contact;
import com.openjpa.entities.User;
import com.openjpa.utils.OpenJPAUtils;

@Stateless
public class UserDao {

	public void create(User user) throws Exception {

		System.out.println("DEBUT UserDao create");

		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction userTransaction = em.getTransaction();

		userTransaction.begin();
		
		try{

			em.persist(user);
			
			userTransaction.commit();
			
		} catch(Exception e) {
			
			System.out.println("SQL[2000] constraint , user not created, see your SQL documentation for more details");
			
		}

		OpenJPAUtils.closeEntityManager(em);

		System.out.println("FIN UserDao create");

	}

	public void update(User user) throws Exception {

		System.out.println("DEBUT UserDao update");

		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction userTransaction = em.getTransaction();

		userTransaction.begin();

		em.merge(user);

		userTransaction.commit();

		OpenJPAUtils.closeEntityManager(em);

		System.out.println("FIN UserDao update");

	}
	
	public void updateScoreById(Integer Id, Integer Score) throws Exception {

		System.out.println("DEBUT UserDao updateScoreById");

		User user = findByUserID(Id);
		
		user.setScore(user.getScore() + Score);
		
		update(user);

		System.out.println("FIN UserDao update");

	}

	public User findByPseudo(String pseudo) throws Exception {

		System.out.println("DEBUT findByPseudo");
		
		User result = null;

		EntityManager em = OpenJPAUtils.openEntityManager();

		try{
			
			TypedQuery<User> query = em.createQuery(
					
					"SELECT u FROM User u WHERE u.pseudo = :pseudo", User.class);
			
			result = query.setParameter("pseudo", pseudo).getSingleResult();
			
			result.setContacts(getAllContactByUserID(result.getId()));
			
		} catch(Exception e) {
			
			System.out.println("Pseudo not found !");
		}


		OpenJPAUtils.closeEntityManager(em);

		System.out.println("FIN findByPseudo");

		return result;
		
	}

	public User findByUserID(Integer ID) throws Exception {

		EntityManager em = OpenJPAUtils.openEntityManager();

		User result = em.find(User.class, ID);
		
		HashSet<Contact> contacts = (HashSet<Contact>)getAllContactByUserID(result.getId());
		
		if (contacts != null) {
			
			result.setContacts(contacts);
			
		} else {
			
			result.setContacts(new HashSet<Contact>()); 
			
		}

		OpenJPAUtils.closeEntityManager(em);

		return result;	
		
	}
	
	public User findByEmail(String email) throws Exception {

		System.out.println("DEBUT findByEmail");
		
		User user;

		EntityManager em = OpenJPAUtils.openEntityManager();

		
		try{
			
			TypedQuery<User> query = em.createQuery(
					
					"SELECT u FROM User u WHERE u.email = :email", User.class);
			
			query.setParameter("email", email);
			
			user = query.getSingleResult();
		 
			user.setContacts(getAllContactByUserID(user.getId()));
			
		} catch( Exception e ) {
			
			user = null;
			
		}

		OpenJPAUtils.closeEntityManager(em);

		System.out.println("FIN findByEmail");

		return user;
	}
	
	public Boolean authenticateUser(String email, String password) throws Exception {

		System.out.println("DEBUT authenticateUser");
		
		User user;

		EntityManager em = OpenJPAUtils.openEntityManager();

		TypedQuery<User> query = em.createQuery(

		"SELECT u FROM User u WHERE u.email = :email AND u.password = :password ", User.class);
		
		query.setParameter("email", email);
		
		query.setParameter("password", password);
		
		try{
			
			user = query.getSingleResult();
		 
		} catch( Exception e ) {
			
			user = null;
		}

		Boolean result  = (user != null ) ? true  : false ; 

		OpenJPAUtils.closeEntityManager(em);

		System.out.println("FIN authenticateUser");

		return result;
		
	}
	
	public Boolean ifPseudoExist(String pseudo) throws Exception {

		System.out.println("DEBUT ifPseudoExist ");
		
		User user;
		
		EntityManager em = OpenJPAUtils.openEntityManager();

		TypedQuery<User> query = em.createQuery(

		"SELECT u FROM User u WHERE u.pseudo = :pseudo", User.class);
		
		try{
			
			user = query.setParameter("pseudo",pseudo).getSingleResult();
		 
		}catch(Exception e){
			
			user = null;
		}
		
		OpenJPAUtils.closeEntityManager(em);
		
		Boolean result  = (user != null ) ? true  : false ; 
		
		System.out.println("FIN ifPseudoExist " + result);
		
		return result;
		
	}
	
	public Boolean ifMailExist(String email) throws Exception {

		System.out.println("DEBUT ifMailExist ");
		
		User user = null;
		
		EntityManager em = OpenJPAUtils.openEntityManager();

		TypedQuery<User> query = em.createQuery(

		"SELECT u FROM User u WHERE u.email = :email",
		User.class);
		
		try{
			
			user = query.setParameter("email",email).getSingleResult();
		 
		}catch(Exception e){
			
			user = null;
		}
		
		OpenJPAUtils.closeEntityManager(em);
		
		Boolean result  = (user != null ) ? true  : false ; 
		
		System.out.println("FIN ifMailExist " + result);
		
		return result;
		
	}
	
	public Set<Contact> getAllContactByUserID(Integer ID) throws Exception {

		System.out.println("DEBUT UserDao getAllContactByID");

		EntityManager em = OpenJPAUtils.openEntityManager();

		TypedQuery<Contact> queryContact = em.createQuery(

			"SELECT c FROM Contact c WHERE c.id_user = :id OR c.id_contact = :id", Contact.class);
		
		List<Contact> contactslist = queryContact.setParameter("id",ID).getResultList();
		
		OpenJPAUtils.closeEntityManager(em);

		return new HashSet<Contact>(contactslist);
	}
	
	public Boolean ifContactExist(Contact c) throws Exception {

		System.out.println("DEBUT UserDao ifContactExist");
		
		Contact contact;

		EntityManager em = OpenJPAUtils.openEntityManager();

		TypedQuery<Contact> queryContact = em.createQuery(

		"SELECT c FROM Contact c WHERE c.id = :id",
		Contact.class);
		
		try{
			
			contact = queryContact.setParameter("id",c.getId()).getSingleResult();
		 
		}catch(Exception e){
			
			contact = null;
		}
		
		OpenJPAUtils.closeEntityManager(em);
		
		Boolean result  = (contact != null ) ? true  : false ; 

		return result;
	}

	public void deleteContact(Contact contact) throws Exception {

		System.out.println("DEBUT UserDao deleteContact");

		if(this.ifContactExist(contact)){

			EntityManager em = OpenJPAUtils.openEntityManager();
			
			User user = findByUserID(contact.getId_user());
			
			user.removeContact(contact);
			
			System.out.println("after removeContact  : " + user);
			
			update(user);			
			
			OpenJPAUtils.closeEntityManager(em);
			
		} else {
		
			System.out.println("delete failure ! inexistant contact en DB");
		}

	}
	
	public Contact findContactById(Integer id) throws Exception {

		System.out.println("DEBUT findContactById");
		
		Contact c;

		EntityManager em = OpenJPAUtils.openEntityManager();

		c = em.find(Contact.class, id);

		OpenJPAUtils.closeEntityManager(em);

		System.out.println("FIN findContactById");

		return c;
	}
	
	
	public void changeContactStatut(Integer contactID, Integer statut) throws Exception {

		System.out.println("DEBUT changeContactStatut");

		EntityManager em = OpenJPAUtils.openEntityManager();
		
		Contact c = this.findContactById(contactID);
		
		if(c != null){
			
			User user = this.findByUserID(c.getId_user());
			
			HashSet<Contact> contacts = (HashSet<Contact>) user.getContacts();
			
			for (Contact currentContact : contacts) {
				
				if(currentContact.equals(c)){
					
					currentContact.setStatut(statut);
					
					System.out.println("changeContactStatut contact after modif : " + currentContact);
					
				}
				
			}
			
			this.update(user);

		}
		
		OpenJPAUtils.closeEntityManager(em);
			
		System.out.println("END changeContactStatut");

	}
	
	public List<User> getAll() throws Exception {
		
		System.out.println("DEBUT UserDao getAll");

		EntityManager em = OpenJPAUtils.openEntityManager();

		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
		
		List<User> result = query.getResultList();
		
		OpenJPAUtils.closeEntityManager(em);

		System.out.println("FIN UserDao getAll");

		return result;
		
	}

	public void delete(User user) throws Exception {

		System.out.println("DEBUT UserDao delete");

		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction userTransaction = em.getTransaction();

		userTransaction.begin();
		
		User u = em.find(User.class, user.getId());

		em.remove(u);

		userTransaction.commit();

		OpenJPAUtils.closeEntityManager(em);

		System.out.println("FIN UserDao delete");

	}

}
