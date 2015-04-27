package neu.edu.Project.DAO;

import java.util.ArrayList; 
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import neu.edu.Project.Entity.Likes;

public class LikeDAO {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Project");
	EntityManager em =  factory.createEntityManager();

	//Creates a new like for a particular user and a particular item 
	public void createLike(Likes li){
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
	}
	
	// Returns True if like already exist for a particular username
	public Boolean likeexists(String username, String item){
		Query query = em.createQuery("Select f FROM Likes f WHERE f.username=:username and f.itemname=:item");
		query.setParameter("username", username);
		query.setParameter("item", item);
		try {
				Likes f = new Likes();
				f = (Likes)query.getSingleResult();
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
	
	//Remove a like 
	public void removelike(String user, String title) {
		em.getTransaction().begin();
		Query query = em.createQuery("Delete FROM Likes uc WHERE uc.username=:user and uc.itemname=:title");
		query.setParameter("user", user);
		query.setParameter("title", title);
		query.executeUpdate();
		em.getTransaction().commit();
		
	}
	
	// Find all likes made by a particular user
	@SuppressWarnings("unchecked")
	public List<Likes> findLikesForUser(String username){
		List<Likes> likes = new ArrayList<Likes>();
		Query query = em.createQuery("SELECT uc FROM Likes uc WHERE uc.username=:username");
		query.setParameter("username", username);
		try {
				likes = (List<Likes>)query.getResultList();
				if (likes!=null)
					return likes;
				else
					return null;
						
		}catch (NoResultException e){
			return null;
		}
	
}
}
