package neu.edu.Project.Entity;
import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
	@Id
	public String username;
	public String password;
	public String credit_card;
	public String address;
	public String email;
	public String firstname;
	public String lastname;
	public String contact;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCredit_card() {
		return credit_card;
	}
	public void setCredit_card(String credit_card) {
		this.credit_card = credit_card;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public User(String username, String password, String credit_card,
			String address, String email, String firstname, String lastname,
			String contact) {
		super();
		this.username = username;
		this.password = password;
		this.credit_card = credit_card;
		this.address = address;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.contact = contact;
	}
	public User() {
		super();
	}

}