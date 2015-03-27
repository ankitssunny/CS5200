<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.ccis.DAO.assignment3.*" 
    import="edu.ccis.Entity.assignment3.*"
    import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Hey type this as well!!!!</h1>

<%

UserManager um = new UserManager();

//1. To Create a new User

User usr = new User();
usr.setId(6);
usr.setUsername("anks");
usr.setPassword("ankit");
usr.setFirstname("Sharma");
usr.setLastname("Ankit");
usr.setEmail("sharma.anki@gmail.com");
usr.setDob("1990-10-03");
um.createUser(usr);

//2. TO read all the Users from the User Table. 

List<User> us=um.readallUsers();
for (User u: us)
{
	out.println(u.getUsername() + "\n" );
}


//3. TO read a User from his username

String name = "anks";
User userread=um.readUser(name);
if (userread!=null)
{
	out.println(userread.getId());
	out.println(userread.getUsername());
	out.println(userread.getPassword());
	out.println(userread.getFirstname());
	out.println(userread.getLastname());
	out.println(userread.getEmail());
	out.println(userread.getDob());

}
else
{
out.print("Username does not exist");	
}


//4. Update a User based on Username
userread.setEmail("anksun@ccis.neu.edu");
um.updateUser(name, userread);


//5. Delete a User based on Username

um.deleteUser("anks");

%>



</body>
</html>