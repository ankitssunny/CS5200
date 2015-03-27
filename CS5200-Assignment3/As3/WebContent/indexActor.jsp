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


ActorManager mm = new ActorManager();


//1. To Create a new Actor

Actor mv = new Actor();
mv.setId(2);
mv.setFirstname("Kunal");
mv.setLastname("Sharma");
mv.setDob("1980-12-25");
mm.createActor(mv);

//2. TO read all the Actors from the Actor Table. 

List<Actor> ml=mm.readallActors();
for (Actor m: ml)
{
	out.println(m.getFirstname() + "\n" );
}


//3. TO read a Actor by its ID

int id = 1;
Actor mvread=mm.readActor(id);
if (mvread!=null)
{
	out.println(mvread.getId());
	out.println(mvread.getFirstname());
	out.println(mvread.getLastname());
	out.println(mvread.getDob());
}
else
{
out.print("Actor does not exist");	
}


//4. Update a Actor based on id
mvread.setFirstname("Inception");
mm.updateActor(id, mvread);


//5. Delete a Actor based on ActorId

mm.deleteActor(1);

%>

</body>
</html>