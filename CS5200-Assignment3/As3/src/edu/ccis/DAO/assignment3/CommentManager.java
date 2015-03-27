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

import edu.ccis.Entity.assignment3.Comments;

public class CommentManager {

		DataSource ds;
		
		public CommentManager()
		{
			try
			{
				Context ctx = new InitialContext();
				ds = (DataSource)ctx.lookup("java:comp/env/jdbc/assignment3");
			} catch (NamingException e) {
				e.printStackTrace();
			}
			}
		
		//1. Creating New Comment
		public void createComment(Comments newComment)
		{
			
			String sql= "insert into comments values(?,?,?,?,?)";
			try {
				Connection conn =ds.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, newComment.getId());
				statement.setString(2, newComment.getComment());
				statement.setString(3, newComment.getDate());
				statement.setInt(4, newComment.getMovieid());
				statement.setInt(5, newComment.getUserid());
				
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		
		//2. Read List of Comments
		public List<Comments> readallComments()
		{
			List<Comments> Cm =new ArrayList<Comments>();
			String sql = "select * from comments";
			try {
				Connection conn = ds.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet result = statement.executeQuery();
				while(result.next())
				{
					Comments ac= new Comments();
					ac.setId(result.getInt("ID"));
					ac.setComment(result.getString("COMMENT"));
					ac.setDate(result.getString("DATE"));
					ac.setUserid(result.getInt("USERID"));
					ac.setMovieid(result.getInt("MOVIEID"));
					
					Cm.add(ac);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Cm;		
		}
		
		
		//3. Read List of Comments for a particular Username
				public List<Comments> readallCommentsForUsername(String username)
				{
					List<Comments> Cm =new ArrayList<Comments>();
					String sql = "select comments.* from Comments c,"
							+ " User u where u.username= ? and c.userid= u.user_id";
					try {
						Connection conn = ds.getConnection();
						PreparedStatement statement = conn.prepareStatement(sql);
						statement.setString(1, username);
						ResultSet result = statement.executeQuery();
						while(result.next())
						{
							Comments ac= new Comments();
							ac.setId(result.getInt("ID"));
							ac.setComment(result.getString("COMMENT"));
							ac.setDate(result.getString("DATE"));
							ac.setUserid(result.getInt("USERID"));
							ac.setMovieid(result.getInt("MOVIEID"));
							
							Cm.add(ac);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return Cm;		
				}
				
		
	//4. Read List of Comments for a particular Movie
			public List<Comments> readallCommentsForMovie(int id)
			{
					List<Comments> Cm =new ArrayList<Comments>();
					String sql = "select * from comments where movieid=?";
					try {
						Connection conn = ds.getConnection();
						PreparedStatement statement = conn.prepareStatement(sql);
						statement.setInt(1, id);
						ResultSet result = statement.executeQuery();
						while(result.next())
						{
							Comments ac= new Comments();
							ac.setId(result.getInt("ID"));
							ac.setComment(result.getString("COMMENT"));
							ac.setDate(result.getString("DATE"));
							ac.setUserid(result.getInt("USERID"));
							ac.setMovieid(result.getInt("MOVIEID"));
							
							Cm.add(ac);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return Cm;		
				}
				
		
				
		//5. read a Comment having a particular ID
		public Comments readCommentForId(int id)
		{
			String sql = "select * from comments where Comments.id= ?";			
			Comments ac= new Comments();
			try {
				Connection conn = ds.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, id);
				ResultSet result = statement.executeQuery();
				if (result.next())
				{
					ac.setId(result.getInt("ID"));
					ac.setComment(result.getString("COMMENT"));
					ac.setDate(result.getString("DATE"));
					ac.setUserid(result.getInt("USERID"));
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
		public void updateComment(int id, Comments Comment)
		{
			
			String sql="update comments set id=?, comment=?, date=?, userid=?"
					+ "movieid=? where id=? ";
			try {
				Connection conn = ds.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, Comment.getId());
				statement.setString(2, Comment.getComment());
				statement.setString(3, Comment.getDate());
				statement.setInt(4, Comment.getUserid());
				statement.setInt(5, Comment.getMovieid());
				statement.setInt(6, id);
				statement.executeUpdate();			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		
		
		//7. Delete a Comment based on CommentId
		
		public void deleteComment(int id)
		{
			
			String sql="delete from comments where id=? ";
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
