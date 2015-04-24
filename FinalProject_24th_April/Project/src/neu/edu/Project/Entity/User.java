package neu.edu.Project.Entity;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String username;
	private String password;
	private String credit_card;
	private String address;
	private String email;
	private String firstname;
	private String lastname;
	private String contact;
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String fname) {
		this.firstname = fname;
	}
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lname) {
		this.lastname = lname;
	}
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	
	public String getCredit_card() {
		return this.credit_card;
	}

	public void setCredit_card(String credit_card) {
		this.credit_card = credit_card;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public User(String username, String password,String address, String fname, String lname, String contact, String email, 
			String credit) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = fname;
		this.lastname = lname;
		this.contact = contact;
		this.credit_card = credit;
		this.address = address;
		this.email = email;
		}	

	public User() {
		super();
	}

}