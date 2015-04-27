package neu.edu.Project.Entity;

import javax.persistence.*;

// Mapping Table for Friends List

@Entity
@Table(name="friends")
@IdClass(MyKey_friends.class)
public class friends{
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