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


CommentManager mm = new CommentManager();


//1. To Create a new Comments

Comments mv = new Comments();
mv.setId(2);
mv.setComment("Awesome");
mv.setDate("2015-07-10");
mv.setDate("2015-07-10");
mv.setUserid(1);
mv.setMovieid(1);
mm.createComment(mv);

//2. TO read all the Comments from the Comments Table. 

List<Comments> ml=mm.readallComments();
for (Comments m: ml)
{
	out.println(m.getComment() + "\n" );
}


//3. TO read all the Comments from the Comments Table for a particular user.

String un="aas";
List<Comments> mlu=mm.readallCommentsForUsername(un);
for (Comments m: mlu)
{
	out.println(m.getComment() + "\n" );
}


//4. TO read all the Comments from the Comments Table. 

int mid =1;
List<Comments> mlm=mm.readallCommentsForMovie(mid);
for (Comments m: mlm)
{
	out.println(m.getComment() + "\n" );
}


//5. TO read a Comments by its ID

int id = 2;
Comments mvread=mm.readCommentForId(id);
if (mvread!=null)
{
	out.println(mvread.getId());
	out.println(mvread.getComment());
	out.println(mvread.getDate());
	out.println(mvread.getUserid());
	out.println(mvread.getMovieid());
	}
else
{
out.print("Comments does not exist");	
}


//4. Update a Comments based on id
mvread.setComment("Inception");
mm.updateComment(id, mvread);


//5. Delete a Comments based on CommentsId

mm.deleteComment(1);

%>

</body>
</html>