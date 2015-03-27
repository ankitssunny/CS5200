package edu.ccis.Entity.assignment3;

public class Movie {
	
	int id;
	String title;
	String posterimage;
	String rdate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPosterimage() {
		return posterimage;
	}
	public void setPosterimage(String posterimage) {
		this.posterimage = posterimage;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	
	public Movie(int id, String title, String posterimage, String rdate) {
		super();
		this.id = id;
		this.title = title;
		this.posterimage = posterimage;
		this.rdate = rdate;
	}
	
	public Movie() {
		super();
	}
	
}
