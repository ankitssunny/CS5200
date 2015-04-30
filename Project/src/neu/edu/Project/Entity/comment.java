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
	public Integer getCommentid() {
		return commentid;
	}
	public void setCommentid(Integer commentid) {
		this.commentid = commentid;
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
	public comment(Integer commentid, String commenter, String community,
			String content) {
		super();
		this.commentid = commentid;
		this.commenter = commenter;
		this.community = community;
		this.content = content;
	}

	public comment() {
		super();
	}
}