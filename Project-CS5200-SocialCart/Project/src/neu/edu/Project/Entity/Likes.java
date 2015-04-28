package neu.edu.Project.Entity;

import javax.persistence.*;

@Entity
@Table(name="Likes")
@IdClass(MyKey_Likes.class)
public class Likes {
	@Id
	public String username;
	@Id
	public String itemname;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public Likes(String username, String itemname) {
		super();
		this.username = username;
		this.itemname = itemname;
	}
	public Likes() {
		super();
	}

}