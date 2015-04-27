package neu.edu.Project.DAO;

import java.util.ArrayList; 
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import neu.edu.Project.Entity.User_Communities;

public class UserCommunityDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Project");
	EntityManager em =  factory.createEntityManager();

	// Returns True if a particular Username and Community exists in the database
	public Boolean usercommunityexists(String name, String cname){
		Query query = em.createQuery("SELECT uc FROM User_Communities uc WHERE uc.name=:name and uc.cname=:cname");
		query.setParameter("name", name);
		query.setParameter("cname", cname);
		try {
				User_Communities u = new User_Communities();
				u = (User_Communities)query.getSingleResult();
				if (u!= null)
					return true;
				else 
					return false;
		}catch (NoResultException e){
			return false;
			
		}
	}
	
	//Creates and insert a new User into the community
	public void Join (User_Communities comm){
		em.getTransaction().begin();
		em.persist(comm);
		em.getTransaction().commit();
	}
	
	// Leave a particular Community for a Particular User
	public void unJoin(String name, String cname ){
		em.getTransaction().begin();
		Query query = em.createQuery("Delete FROM User_Communities uc WHERE uc.name=:name and uc.cname=:cname");
		query.setParameter("name", name);
		query.setParameter("cname", cname);
		query.executeUpdate();
		em.getTransaction().commit();
	}
	
	// Find All the Communities for a particular User
	@SuppressWarnings("unchecked")
	public List<User_Communities> findCommunitiesForUser(String username){
		List<User_Communities> u = new ArrayList<User_Communities>();
		Query query = em.createQuery("SELECT uc FROM User_Communities uc WHERE uc.name=:username");
		query.setParameter("username", username);
		try {
				u = (List<User_Communities>)query.getResultList();
				if (u!=null)
					return u;
				else
					return null;
						
		}catch (NoResultException e){
			return null;
		}
}
	
	// Find all the members of a given Community
	@SuppressWarnings("unchecked")
	public List<User_Communities> findMembersInCommunity(String cname){
		List<User_Communities> u = new ArrayList<User_Communities>();
		Query query = em.createQuery("SELECT uc FROM User_Communities uc WHERE uc.cname=:cname");
		query.setParameter("cname", cname);
		try {
				u = (List<User_Communities>)query.getResultList();
				if (u!=null)
					return u;
				else
					return null;
						
		}catch (NoResultException e){
			return null;
		}
}
}
