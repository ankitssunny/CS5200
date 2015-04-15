package com.neu.edu.cs5200.assignment4.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.neu.edu.cs5200.assignment4.Entity.Site;
@Path("/site")
public class SiteDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Assignment4-Web Services");
	EntityManager em= factory.createEntityManager();
	
	@GET
	@Path("/{ID}")
	@Produces(MediaType.APPLICATION_JSON)
	//a.  public Site findSite(int siteId)
	public Site findSite(@PathParam("ID") int siteId){
		 
		 return em.find(Site.class, siteId);
	 }
	 
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	 //b. public List<Site> findAllSites()
	public List<Site> findAllSites(){
		 
		 Query query=em.createQuery("select site from Site site");
		 return (List<Site>)query.getResultList();
		 }
	 
	
	@PUT
	@Path("/{id}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 //c. public List<Site> updateSite(Site site)
	 public List<Site> updateSite(@PathParam("id")int s, Site site){

		 em.getTransaction().begin();
		 em.merge(site);
		 em.getTransaction().commit();
		 Query query=em.createQuery("select site from Site site");
		 return (List<Site>)query.getResultList();
		 }
	 
	 
	 
	 @DELETE
	 @Path("/{id}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 //d. public List<Site> removeSite(int siteId) 
	 public List<Site> removeSite(@PathParam("id") int siteId){
		 Site site=findSite(siteId);
		 em.getTransaction().begin();
		 em.remove(site);
		 em.getTransaction().commit();
		 Query query=em.createQuery("select site from Site site");
		 return (List<Site>)query.getResultList();		 
	 }
	 
	 
	 
	 @POST
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)

	 //e. public List<Site> createSite(Site site)
	 public List<Site> createSite(Site site) {
		em.getTransaction().begin();
		em.persist(site);
		em.getTransaction().commit();
		Query query=em.createQuery("select site from Site site");
		return (List<Site>)query.getResultList();		 
	 }
		 
	 
	 
	public static void main(String[] args) {
		SiteDAO dao = new SiteDAO();
		//a.
//		Site s=dao.findSite(4);
//		System.out.print("The Site Details found are: ");
//		System.out.print(site.getName());
	
		//b.	
//		List<Site> sites= dao.findAllSites();
//		for (Site site : sites)
//		{
//			
//			System.out.println(site.getName());
//		}
//	

		//c.
//		s.setName("Ankit");
//		List<Site> sites= dao.updateSite(s.getId(), s);
//		for (Site site : sites)
//		{
//			
//			System.out.println(site.getName());
//		}
//	
		
		//d.
		
//		List<Site> sites= dao.removeSite(3);
//		for (Site site : sites)
//		{
//			
//			System.out.println(site.getName());
//		}

		//e.
//		Site s= new Site(null,10.0,10.0,"Boston");
//		List<Site> sites= dao.createSite(s);
//		for (Site site : sites)
//		{
//			System.out.println(site.getName());
//		}
		
	}	
}


