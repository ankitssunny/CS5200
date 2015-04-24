package neu.edu.Project.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_communities")
@IdClass(MyKey.class)
public class User_Communities implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	public String name;
	@Id
	public String cname;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public User_Communities(String name, String cname) {
		super();
		this.name = name;
		this.cname = cname;
	}
	public User_Communities() {
		super();
	}
}

	
