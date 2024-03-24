package admin_user.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import admin_user.model.Questions;

@Repository
public interface QuestionDao extends JpaRepository<Questions, Integer> {
	List<Questions> findByCategory(String category);

	@Query(value="select * from questions q where q.category=:category", nativeQuery = true)
	List<Questions> findAllQuestionsByCategory(String category);
	
	@Query(value="select * from questions q where q.category=:category and q.section=:section", nativeQuery = true)
	List<Questions> findAllQuestionsByCategoryAndSection(String category,String section);
	
	
}
