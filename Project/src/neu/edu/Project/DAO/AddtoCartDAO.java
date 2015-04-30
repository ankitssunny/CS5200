package neu.edu.Project.DAO;

import java.util.ArrayList;   
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import neu.edu.Project.Entity.AddtoCart;

public class AddtoCartDAO {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Project");
	EntityManager em =  factory.createEntityManager();

	//Creates a new product for a particular user in his cart 
	public void createcartproduct(AddtoCart c){
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
	}
	
	// Returns True if item already exist for a particular username in his cart
	public Boolean productxists(String username, String item){
		Query query = em.createQuery("Select f FROM AddtoCart f WHERE f.username=:username and f.item=:item");
		query.setParameter("username", username);
		query.setParameter("item", item);
		try {
				AddtoCart f = new AddtoCart();
				f = (AddtoCart)query.getSingleResult();
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
	
	//Remove an item 
	public void removeproduct(String user, String title) {
		em.getTransaction().begin();
		Query query = em.createQuery("Delete FROM AddtoCart uc WHERE uc.username=:user and uc.item=:title");
		query.setParameter("user", user);
		query.setParameter("title", title);
		query.executeUpdate();
		em.getTransaction().commit();
		
	}
	
	// Find all items selected by a particular user
	@SuppressWarnings("unchecked")
	public List<AddtoCart> findproductsForUser(String username){
		List<AddtoCart> likes = new ArrayList<AddtoCart>();
		Query query = em.createQuery("SELECT uc FROM AddtoCart uc WHERE uc.username=:username");
		query.setParameter("username", username);
		try {
				likes = (List<AddtoCart>)query.getResultList();
				if (likes!=null)
					return likes;
				else
					return null;
						
		}catch (NoResultException e){
			return null;
		}
	
}
}
