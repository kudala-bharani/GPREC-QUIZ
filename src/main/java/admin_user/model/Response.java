package admin_user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "answers")
public class Response {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "quest_id")
	private Integer questId;
    private String response;
	
	public String getResponse() {
		return response;
	}
	
	public void setResponse(String response) {
		this.response = response;
	}
	
	public Response() {
		super();
	}
	
	public Integer getQuestId() {
		return questId;
	}
	
	public void setQuestId(Integer questId) {
		this.questId = questId;
	}
	
	public Response(Long id, Integer questId, String response) {
		super();
		this.id = id;
		this.questId = questId;
		this.response = response;
	}
    
}
