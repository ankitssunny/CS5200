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


MovieManager mm = new MovieManager();


//1. To Create a new Movie

Movie mv = new Movie();
mv.setId(2);
mv.setPosterimage("Dreams");
mv.setTitle("Awakenings");
mv.setRdate("2015-07-10");
mm.createMovie(mv);

//2. TO read all the Movies from the Movie Table. 

List<Movie> ml=mm.readallMovies();
for (Movie m: ml)
{
	out.println(m.getTitle() + "\n" );
}


//3. TO read a Movie by its ID

int id = 4;
Movie mvread=mm.readMovie(id);
if (mvread!=null)
{
	out.println(mvread.getId());
	out.println(mvread.getTitle());
	out.println(mvread.getPosterimage());
	out.println(mvread.getRdate());
}
else
{
out.print("Movie does not exist");	
}


//4. Update a Movie based on id
mvread.setTitle("Inception");
mm.updateMovie(id, mvread);


//5. Delete a Movie based on MovieId

mm.deleteMovie(1);

%>

</body>
</html>