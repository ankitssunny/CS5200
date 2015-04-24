package neu.edu.Project.Entity;

import java.io.Serializable;

import javax.persistence.*;

//Mapping Table for Friends List

@Entity
@Table(name="friends")
public class friends implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	String user;
	@Id
	String friend;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getFriend() {
		return friend;
	}
	public void setFriend(String friend) {
		this.friend = friend;
	}
	public friends(String user, String friend) {
		super();
		this.user = user;
		this.friend = friend;
	}
	public friends() {
		super();
	}	
}
