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

import edu.ccis.Entity.assignment3.Movie;

public class MovieManager {

		DataSource ds;
		
		public MovieManager()
		{
			try
			{
				Context ctx = new InitialContext();
				ds = (DataSource)ctx.lookup("java:comp/env/jdbc/assignment3");
			} catch (NamingException e) {
				e.printStackTrace();
			}
			}
		
		//1. Creating New Movie
		public void createMovie(Movie newMovie)
		{
			
			String sql= "insert into movie values(?,?,?,?)";
			try {
				Connection conn =ds.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, newMovie.getId());
				statement.setString(2, newMovie.getTitle());
				statement.setString(3, newMovie.getPosterimage());
				statement.setString(4, newMovie.getRdate());
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		
		//2. Read List of Movies
		public List<Movie> readallMovies()
		{
			List<Movie> movies =new ArrayList<Movie>();
			String sql = "select * from movie";
			try {
				Connection conn = ds.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					Movie mv= new Movie();
					mv.setId(result.getInt("ID"));
					mv.setTitle(result.getString("TITLE"));
					mv.setPosterimage(result.getString("POSTERIMAGE"));
					mv.setRdate(result.getString("RELEASE_DATE"));
					movies.add(mv);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return movies;		
		}
		
		//3. read a movie having a particular ID
		public Movie readMovie(int id)
		{
			String sql = "select * from movie where movie.id= ?";			
			Movie mv= new Movie();
			try {
				Connection conn = ds.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, id);
				ResultSet result = statement.executeQuery();
				if (result.next())
				{
					mv.setId(result.getInt("ID"));
					mv.setTitle(result.getString("TITLE"));
					mv.setPosterimage(result.getString("POSTERIMAGE"));
					mv.setRdate(result.getString("RELEASE_DATE"));
				}
				else
				{
					mv=null;
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return mv;
			}

		//4. Update a Movie based on MovieId
		public void updateMovie(int id, Movie movie)
		{
			
			String sql="update movie set id=?, title=?, posterimage=?, release_date=? "
					+ " where id=? ";
			try {
				Connection conn = ds.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, movie.getId());
				statement.setString(2, movie.getTitle());
				statement.setString(3, movie.getPosterimage());
				statement.setString(4, movie.getRdate());
				statement.setInt(5, id);
				statement.executeUpdate();			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		//5. Delete a Movie based on MovieId
		
		public void deleteMovie(int id)
		{
			
			String sql="delete from movie where id=? ";
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
