package edu.ccis.Entity.assignment3;

public class Casts {

	String charname;
	int id;
	int actorid;
	int movieid;
	public String getCharname() {
		return charname;
	}
	public void setCharname(String charname) {
		this.charname = charname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getActorid() {
		return actorid;
	}
	public void setActorid(int actorid) {
		this.actorid = actorid;
	}
	public int getMovieid() {
		return movieid;
	}
	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}
	
	public Casts(String charname, int id, int actorid, int movieid) {
		super();
		this.charname = charname;
		this.id = id;
		this.actorid = actorid;
		this.movieid = movieid;
	}
	
	public Casts() {
		super();
	}
	
}

