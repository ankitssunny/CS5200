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

import edu.ccis.Entity.assignment3.Casts;

public class CastManager {

		DataSource ds;
		
		public CastManager()
		{
			try
			{
				Context ctx = new InitialContext();
				ds = (DataSource)ctx.lookup("java:comp/env/jdbc/assignment3");
			} catch (NamingException e) {
				e.printStackTrace();
			}
			}
		
		//1. Creating New Cast
		public void createCast(Casts newCast)
		{
			
			String sql= "insert into casts values(?,?,?,?)";
			try {
				Connection conn =ds.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, newCast.getId());
				statement.setString(2, newCast.getCharname());
				statement.setInt(3, newCast.getActorid());
				statement.setInt(4, newCast.getMovieid());
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		
		//2. Read List of casts
		public List<Casts> readAllCast()
		{
			List<Casts> Cm =new ArrayList<Casts>();
			String sql = "select * from casts";
			try {
				Connection conn = ds.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					Casts ac= new Casts();
					ac.setId(result.getInt("ID"));
					ac.setCharname(result.getString("CHAR_NAME"));
					ac.setActorid(result.getInt("ACTORID"));
					ac.setMovieid(result.getInt("MOVIEID"));
					Cm.add(ac);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Cm;		
		}
		
		
		//3. Read List of casts for a particular Actor
				public List<Casts> readAllCastForActor(int id)
				{
					List<Casts> Cm =new ArrayList<Casts>();
					String sql = "select * from casts where actorid=?";
					try {
						Connection conn = ds.getConnection();
						PreparedStatement statement = conn.prepareStatement(sql);
						statement.setInt(1, id);
						ResultSet result = statement.executeQuery();
						while(result.next())
						{
							Casts ac= new Casts();
							ac.setId(result.getInt("ID"));
							ac.setCharname(result.getString("CHAR_NAME"));
							ac.setActorid(result.getInt("ACTORID"));
							ac.setMovieid(result.getInt("MOVIEID"));
							Cm.add(ac);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return Cm;		
				}
				
		
	//4. Read List of casts for a particular Movie
			public List<Casts> readAllCastForMovie(int id)
			{
					List<Casts> Cm =new ArrayList<Casts>();
					String sql = "select * from casts where movieid=?";
					try {
						Connection conn = ds.getConnection();
						PreparedStatement statement = conn.prepareStatement(sql);
						statement.setInt(1, id);
						ResultSet result = statement.executeQuery();
						while(result.next())
						{
							Casts ac= new Casts();
							ac.setId(result.getInt("ID"));
							ac.setCharname(result.getString("CHAR_NAME"));
							ac.setActorid(result.getInt("ACTORID"));
							ac.setMovieid(result.getInt("MOVIEID"));
							Cm.add(ac);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return Cm;		
				}
				
		
				
		//5. read a Cast having a particular ID
		public Casts readCastForId(int id)
		{
			String sql = "select * from casts where casts.id= ?";			
			Casts ac= new Casts();
			try {
				Connection conn = ds.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, id);
				ResultSet result = statement.executeQuery();
				if (result.next())
				{
					ac.setId(result.getInt("ID"));
					ac.setCharname(result.getString("CHAR_NAME"));
					ac.setActorid(result.getInt("ACTORID"));
					ac.setMovieid(result.getInt("MOVIEID"));
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

		
		//6. Update a Comment based on CommentId
		public void updateCast(int id, Casts newcast)
		{
			
			String sql="update casts set id=?, char_name=?, actorid=?"
					+ "movieid=? where id=? ";
			try {
				Connection conn = ds.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, newcast.getId());
				statement.setString(2, newcast.getCharname());
				statement.setInt(3, newcast.getActorid());
				statement.setInt(4, newcast.getMovieid());
				statement.setInt(5,id);
				statement.executeUpdate();			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		
		
		//7. Delete a Cast based on CastId
		
		public void deleteCast(int id)
		{
			
			String sql="delete from casts where id=? ";
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
