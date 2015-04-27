package neu.edu.Project.DAO;

import neu.edu.Project.Entity.*; 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class UserDAO {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Project");
	EntityManager em = factory.createEntityManager();
	
	//Creates and enters new user into the database
	public void createuser (User user){
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
	
	//Updates a given user
	public void UpdateUser(User user){
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
	}

	//Finds a user by username
	public User findUser(String username){
		try{
			User user = em.find(User.class, username);
			return user;
	}
	catch (NoResultException e){
		return null;
	}catch (NullPointerException e){
		return null;			
	}
}
	
	// Returns True if Username exist in database
	public Boolean userexists(String username){
		try{
				User user = em.find(User.class, username);
				if (user != null)
					return true;
				else 
					return false;
		}
		catch (NullPointerException e){
			return false;
		}
	}
	
	
	// Returns True if User name and password match
	public Boolean verifyuser(String username, String password){
		try {
				User user = em.find(User.class, username);
				if (user.getPassword().equals(password)){
					System.out.println("Username matches");
					return true;
			}
			System.out.println("Username/password do not match");
			return false;
		}
		catch(Exception e){
			System.out.println("Username does not exist in the databse.");	
		}
		return false;
		
	}
	
	// Deletes a User based on User name
	public void deleteUser(String username){
		try {	
				User user = em.find(User.class, username);
				em.getTransaction().begin();
				em.remove(user);
				em.getTransaction().commit();			
		}
		catch (IllegalArgumentException e) {
			System.out.println("Username does not exist in the database.");
			
		}
	}	

	public static void main(String args[]){
		
		System.out.print("Je");
	}


}