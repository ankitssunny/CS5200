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

import edu.ccis.Entity.assignment3.User;
public class UserManager {

	DataSource ds;
	
	public UserManager()
	{
		try
		{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/assignment3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		}
	
	//1. Creating New User
	public void createUser(User newUser)
	{
		
		String sql= "insert into user values(?,?,?,?,?,?,?)";
		try {
			Connection conn =ds.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, newUser.getId());
			statement.setString(2, newUser.getUsername());
			statement.setString(3, newUser.getPassword());
			statement.setString(4, newUser.getFirstname());
			statement.setString(5, newUser.getLastname());
			statement.setString(6, newUser.getEmail());
			statement.setString(7, newUser.getDob());
			statement.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	//2. Read List of Users.
	public List<User> readallUsers()
	{
		List<User> users =new ArrayList<User>();
		String sql = "select * from user";
		try {
			Connection conn = ds.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				User usr= new User();
				usr.setId(result.getInt("USER_ID"));
				usr.setUsername(result.getString("USERNAME"));
				usr.setPassword(result.getString("PASSWORD"));
				usr.setFirstname(result.getString("FIRSTNAME"));
				usr.setLastname(result.getString("LASTNAME"));
				usr.setEmail(result.getString("EMAIL"));
				usr.setDob(result.getString("DATE_OF_BIRTH"));
				users.add(usr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;		
	}
	
	//3. read a user having a particular Username
	public User readUser(String username)
	{
		String sql = "select * from user where user.username= ?";			
		User usr= new User();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if (result.next())
			{
				usr.setId(result.getInt("USER_ID"));
				usr.setUsername(result.getString("USERNAME"));
				usr.setPassword(result.getString("PASSWORD"));
				usr.setFirstname(result.getString("FIRSTNAME"));
				usr.setLastname(result.getString("LASTNAME"));
				usr.setEmail(result.getString("EMAIL"));
				usr.setDob(result.getString("DATE_OF_BIRTH"));
			}
			else
			{
				usr=null;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usr;
		}

	//4. Update a User based on Username
	public void updateUser(String username, User user)
	{
		
		String sql="update user set user_id=?, username=?, password=?, firstname=?, "
				+ "lastname=?, email=?, Date_of_Birth=? where username=? ";
		try {
			Connection conn = ds.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, user.getId());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getFirstname());
			statement.setString(5, user.getLastname());
			statement.setString(6, user.getEmail());
			statement.setString(7, user.getDob());
			statement.setString(8, username);
			statement.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	//5. Delete a User based on Username
	
	public void deleteUser(String username)
	{
		
		String sql="delete from user where username=? ";
		try {
			Connection conn = ds.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}
