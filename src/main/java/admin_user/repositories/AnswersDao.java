package admin_user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import admin_user.model.Response;

@Repository
public interface AnswersDao extends JpaRepository<Response,Integer> {

}
