package edu.ccis.Entity.assignment3;

public class Comments {

	int id;
	int userid;
	int movieid;
	String comment;
	String date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getMovieid() {
		return movieid;
	}
	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public Comments(int id, int userid, int movieid, String comment, String date) {
		super();
		this.id = id;
		this.userid = userid;
		this.movieid = movieid;
		this.comment = comment;
		this.date = date;
	}
	
	public Comments() {
		super();
	}
	
}
