package admin_user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import admin_user.model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer>{

}
