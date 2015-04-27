package neu.edu.Project.Entity;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Community {
	@Id
	public String name;
	public String creator;
	public String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Community(String name, String creator, String description) {
		super();
		this.name = name;
		this.creator = creator;
		this.description = description;
	}
	public Community() {
		super();
	}

}