package neu.edu.Project.Entity;

import javax.persistence.*;

@Entity
@Table(name="comments")
public class comment {
	@Id
	@SequenceGenerator(name = "mySeq", sequenceName = "MY_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "mySeq")
	public Integer commentid;
	public String commenter;
	public String community;
	public String content;
	
	public Integer getId() {
		return commentid;
	}
	public void setId(Integer id) {
		this.commentid = id;
	}
	public String getCommenter() {
		return commenter;
	}
	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public comment(Integer id, String commenter, String community,
			String content) {
		super();
		this.commentid = id;
		this.commenter = commenter;
		this.community = community;
		this.content = content;
	}
	
	public comment() {
		super();
	}
}
