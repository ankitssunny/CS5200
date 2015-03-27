package edu.ccis.DAO.assignment3;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.ccis.Entity.assignment3.Actor;

public class ActorManager {

		DataSource ds;
		
		public ActorManager()
		{
			try
			{
				Context ctx = new InitialContext();
				ds = (DataSource)ctx.lookup("java:comp/env/jdbc/assignment3");
			} catch (NamingException e) {
				e.printStackTrace();
			}
			}
		
		//1. Creating New Actor
		public void createActor(Actor newActor)
		{
			
			String sql= "insert into Actor values(?,?,?,?)";
			try {
				Connection conn =ds.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, newActor.getId());
				statement.setString(2, newActor.getFirstname());
				statement.setString(3, newActor.getLastname());
				statement.setString(4, newActor.getDob());
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		
		//2. Read List of Actors
		public List<Actor> readallActors()
		{
			List<Actor> Actors =new ArrayList<Actor>();
			String sql = "select * from Actor";
			try {
				Connection conn = ds.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					Actor ac= new Actor();
					ac.setId(result.getInt("ID"));
					ac.setFirstname(result.getString("FIRSTNAME"));
					ac.setLastname(result.getString("LASTNAME"));
					ac.setDob(result.getString("DATE_OF_BIRTH"));
					Actors.add(ac);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Actors;		
		}
		
		//3. read a Actor having a particular ID
		public Actor readActor(int id)
		{
			String sql = "select * from Actor where Actor.id= ?";			
			Actor ac= new Actor();
			try {
				Connection conn = ds.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, id);
				ResultSet result = statement.executeQuery();
				if (result.next())
				{
					ac.setId(result.getInt("ID"));
					ac.setFirstname(result.getString("FIRSTNAME"));
					ac.setLastname(result.getString("LASTNAME"));
					ac.setDob(result.getString("DATE_OF_BIRTH"));
				}
				else
				{
					ac=null;
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return ac;
			}

		//4. Update a Actor based on ActorId
		public void updateActor(int id, Actor Actor)
		{
			
			String sql="update Actor set id=?, firstname=?, lastname=?, date_of_birth=? "
					+ " where id=? ";
			try {
				Connection conn = ds.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, Actor.getId());
				statement.setString(2, Actor.getFirstname());
				statement.setString(3, Actor.getLastname());
				statement.setString(4, Actor.getDob());
				statement.setInt(5, id);
				statement.executeUpdate();			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		//5. Delete a Actor based on ActorId
		
		public void deleteActor(int id)
		{
			
			String sql="delete from Actor where id=? ";
			try {
				Connection conn = ds.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, id);
				statement.executeUpdate();			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}

}
