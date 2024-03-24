package admin_user.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.Param;

import admin_user.model.Result;

public interface ResultRepository extends JpaRepository<Result, String> {

	Result findByRollnumber(String username);
	
	//Result save(ResultDto resultdto);
	
	@Query(value="select r from Result r where r.section=:section")
	List<Result> findBySection(@Param("section")String section);

}
