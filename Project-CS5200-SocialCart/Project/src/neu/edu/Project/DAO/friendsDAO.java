package neu.edu.Project.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import neu.edu.Project.Entity.friends;

public class friendsDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Project");
	EntityManager em = factory.createEntityManager();
	
	//Returns True if two particular users are already friends
	public Boolean friendshipexists(String user, String friend) {
		//javax.management.Query query = em.createQuery("SELECT f FROM friends f WHERE f.user=:user and f.friend=:friend");
		Query query = em.createQuery("SELECT f FROM friends f WHERE f.user=:user and f.friend=:friend");
		query.setParameter("user", user);
		query.setParameter("friend", friend);
		try {
			friends f = new friends();
			f = (friends)query.getSingleResult();
			if (f!= null)
				return true;
			else
				return false;					
		}catch (NoResultException e){
			return false;
		}catch (NullPointerException e){
			return false;
		}
	
	}
	
	//Creates new friendship
	public void addfriend (String user, String friend){
    	friends f = new friends(user,friend);
    	friends f1 = new friends(friend, user);
    	em.getTransaction().begin();
		em.persist(f);
		em.persist(f1);
		em.getTransaction().commit();		
	}
	
	//Remove a friendship
	public void remove (String user, String friend){
		em.getTransaction().begin();
		Query query = em.createQuery("DELETE FROM friends uc WHERE uc.user=:user and uc.friend=:friend");
		query.setParameter("user", user);
		query.setParameter("friend", friend);
		query.executeUpdate();
		Query q = em.createQuery("DELETE FROM friends uc WHERE uc.user=:user and uc.friend=:friend");
		q.setParameter("user", friend);
		q.setParameter("friend", user);
		q.executeUpdate();
		em.getTransaction().commit();				
	}
	
	//find all friends for particular user
	@SuppressWarnings("unchecked")
	public List<friends> findFriendsForUser(String username){
		List<friends> frnds = new ArrayList<friends>();
		Query query = em.createQuery("SELECT uc FROM friends uc WHERE uc.user=:username");
		query.setParameter("username", username);
		try {
			    frnds = (List<friends>)query.getResultList();
			    if (frnds!= null)
			    	     return frnds;
			    else
			    	return null;
		}catch (NoResultException e){
			return null;		 			    	
		}
			
	}
	
}
