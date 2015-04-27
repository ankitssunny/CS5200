package neu.edu.Project.DAO;


import java.util.ArrayList; 
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import neu.edu.Project.Entity.comment;

public class CommentDAO {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Project");
	EntityManager em =  factory.createEntityManager();
	
	//Creates and enter new comment into the database
	public void createComment (comment cmnt) {
		em.getTransaction().begin();
		em.persist(cmnt);
		em.getTransaction().commit();
	}
	
	// Updates a given Comment
		public void UpdateComment (comment comm) {
			em.getTransaction().begin();
			em.merge(comm);
			em.getTransaction().commit();
		}
		
	// Finds a Comments for a particular Community
	@SuppressWarnings("unchecked")
	public List<comment> findCommentsForCommunity(String cname) {
	List<comment> cmnts = new ArrayList<comment>();
	Query query = em.createQuery("SELECT uc FROM comment uc WHERE uc.community=:cname");
	query.setParameter("cname", cname);
	try {
		cmnts = (List<comment>)query.getResultList();
		    if (cmnts!= null)
		    	     return cmnts;
		    else
		    	return null;
	}catch (NoResultException e){
		return null;		 			    	
	}
		
}
	
	// Finds a Comments made by a user
		@SuppressWarnings("unchecked")
		public List<comment> findCommentsByCommenter(String cname) {
			List<comment> cmnts = new ArrayList<comment>();
			Query query = em.createQuery("SELECT uc FROM comment uc WHERE uc.commenter=:cname");
			query.setParameter("cname", cname);
			try {
					cmnts = (List<comment>)query.getResultList();
					if (cmnts!= null)
			    	     return cmnts;
					else
						 return null;
		}catch (NoResultException e){
			return null;		 			    	
		}
			
	}


		// Finds a Comments made in a Particular Community
		@SuppressWarnings("unchecked")
		public List<comment> findCommentsByCommunity(String cname) {
			List<comment> cmnts = new ArrayList<comment>();
			Query query = em.createQuery("SELECT uc FROM comment uc WHERE uc.community=:cname");
			query.setParameter("cname", cname);
			try {
					cmnts = (List<comment>)query.getResultList();
					if (cmnts!= null)
			    	     return cmnts;
					else
						 return null;
		}catch (NoResultException e){
			return null;		 			    	
		}
			
	}



}
