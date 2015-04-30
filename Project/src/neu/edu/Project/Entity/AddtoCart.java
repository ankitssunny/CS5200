package neu.edu.Project.Entity;

import javax.persistence.*;

@Entity
@Table(name="AddtoCart")
@IdClass(MyKey_AddtoCart.class)
public class AddtoCart {
	@Id
	public String username;
	@Id
	public String item;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public AddtoCart(String username, String item) {
		super();
		this.username = username;
		this.item = item;
	}
	public AddtoCart() {
		super();
	}
	
}	