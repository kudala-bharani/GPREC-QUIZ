package admin_user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "questions")
public class Questions {
	
	    @Id
	    @Column(name = "id")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	    private String questionTitle;
	    private String option1;
	    private String option2;
	    private String option3;
	    private String option4;
	    private String rightAnswer;
	    private String category;
	    private String section;
	    
		public Questions() {
			super();
		}
		
		public Questions(String questionTitle, String option1, String option2, String option3, String option4,
				String rightAnswer, String category, String section) {
			super();
			this.questionTitle = questionTitle;
			this.option1 = option1;
			this.option2 = option2;
			this.option3 = option3;
			this.option4 = option4;
			this.rightAnswer = rightAnswer;
			this.category = category;
			this.section = section;
		}
		
		public Questions(Integer id, String questionTitle, String option1, String option2, String option3,
				String option4, String rightAnswer, String category, String section) {
			super();
			this.id = id;
			this.questionTitle = questionTitle;
			this.option1 = option1;
			this.option2 = option2;
			this.option3 = option3;
			this.option4 = option4;
			this.rightAnswer = rightAnswer;
			this.category = category;
			this.section = section;
		}

		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getQuestionTitle() {
			return questionTitle;
		}
		public void setQuestionTitle(String questionTitle) {
			this.questionTitle = questionTitle;
		}
		public String getOption1() {
			return option1;
		}
		public void setOption1(String option1) {
			this.option1 = option1;
		}
		public String getOption2() {
			return option2;
		}
		public void setOption2(String option2) {
			this.option2 = option2;
		}
		public String getOption3() {
			return option3;
		}
		public void setOption3(String option3) {
			this.option3 = option3;
		}
		public String getOption4() {
			return option4;
		}
		public void setOption4(String option4) {
			this.option4 = option4;
		}
		public String getRightAnswer() {
			return rightAnswer;
		}
		public void setRightAnswer(String rightAnswer) {
			this.rightAnswer = rightAnswer;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}

		public String getSection() {
			return section;
		}

		public void setSection(String section) {
			this.section = section;
		}
	    
	    
	
}
