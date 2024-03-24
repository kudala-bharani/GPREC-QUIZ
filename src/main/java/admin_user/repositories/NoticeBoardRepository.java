package admin_user.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import admin_user.model.NoticeBoard;

public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Integer>{

	List<NoticeBoard> findBySection(String section);

}
