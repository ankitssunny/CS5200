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


CastManager mm = new CastManager();


//1. To Create a new Cast

Casts mv = new Casts();
mv.setId(7);
mv.setCharname("Villain");
mv.setActorid(1);
mv.setMovieid(1);
mm.createCast(mv);

//2. TO read all the Casts from the Casts Table. 

List<Casts> ml=mm.readAllCast();
for (Casts m: ml)
{
	out.println(m.getCharname() + "\n" );
}


//3. TO read all the Casts from the Casts Table for a particular actor.

int ida=2;
List<Casts> mlu=mm.readAllCastForActor(ida);
for (Casts m: mlu)
{
	out.println(m.getCharname() + "\n" );
}


//4. TO read all the Casts from the Casts Table for a particular movie.

int mid =1;
List<Casts> mlm=mm.readAllCastForMovie(mid);
for (Casts m: mlm)
{
	out.println(m.getCharname() + "\n" );
}


//5. TO read a Cast by its ID

int id = 2;
Casts mvread=mm.readCastForId(id);
if (mvread!=null)
{
	out.println(mvread.getId());
	out.println(mvread.getCharname());
	out.println(mvread.getActorid());
	out.println(mvread.getMovieid());
	}
else
{
out.print("Cast does not exist");	
}


//4. Update a Cast based on id
mvread.setCharname("Inception");
mm.updateCast(id, mvread);


//5. Delete a Cast based on Cast Id

mm.deleteCast(3);

%>

</body>
</html>