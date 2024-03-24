package admin_user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "noticeboard")
public class NoticeBoard {
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private String updates;
	private String section;
	
	public NoticeBoard() {
		super();
	}
	
	public NoticeBoard(String updates, String section) {
		super();
		this.updates = updates;
		this.section = section;
	}
	
	public NoticeBoard(Integer id, String updates,String section) {
		super();
		this.id = id;
		this.updates = updates;
		this.section = section;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUpdates() {
		return updates;
	}

	public void setUpdates(String updates) {
		this.updates = updates;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
	
	
	
}
