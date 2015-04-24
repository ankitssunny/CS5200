package neu.edu.Project.DAO;
import neu.edu.Project.Entity.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class CommunityDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Project");
	EntityManager em =  factory.createEntityManager();

	//Creates and inserts New Community into the Database
	public void createCommunity (Community comm) {
		em.getTransaction().begin();
		em.persist(comm);
		em.getTransaction().commit();
	}
	
	// Updates a Given Community
	public void UpdateCommunity (Community comm) { 
		em.getTransaction().begin();
		em.merge(comm);
		em.getTransaction().commit();
		}
	
	
	// Finds a Community by name
	public Community findCommunity(String cname) {
		try {
			Community comm =  em.find(Community.class, cname);
			return comm;
		}
		catch (NoResultException e ){
			return null;
		}catch (NullPointerException e){
			return null;
		}
	}
	
	
	// Returns True if Community exists in the database
	public Boolean communityexists(String cname) {
		try {
			Community comm =  em.find(Community.class, cname);
			if (comm != null)
				return true;
			else
				return false;
		}
		catch (NullPointerException e){
			return false;
		} 
	}

	
	// Returns True if CommunityUsername and password match
	public Boolean verifyCommunityOwner(String username, String cname) {
		try {
			Community comm =  em.find(Community.class, cname);
			if (comm.getCreator().equals(username)) {
				System.out.println("User is the creator of the community");
				return true;
			}
			System.out.print("Username cannot make changes.");
			return false; 
		}
		catch (Exception e) {
			System.out.println("Username/Community does not exist in the database.");
		}
		
		return false;
	}	
}
