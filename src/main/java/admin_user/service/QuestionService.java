package admin_user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import admin_user.model.Questions;
import admin_user.model.QuestionsForm;
import admin_user.repositories.QuestionDao;

@Service
public class QuestionService {
	
	  @Autowired
	  QuestionDao questionDao;
	  
	  public ResponseEntity<List<Questions>> getAllQuestions() {
	        try {
	            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	    }
	
	public ResponseEntity<String> addQuestion(Questions question) {
        questionDao.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }
	
	public Questions getQuestionById(int id) {
        Optional<Questions> question = questionDao.findById(id);
        return question.orElse(null);
    }
	
	public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }
	public ResponseEntity<List<Questions>> getQuestionsByCategoryAndSection(String category, String section) {
        try {
            return new ResponseEntity<>(questionDao.findAllQuestionsByCategoryAndSection(category,section),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }
	/*
	public void save(QuestionsForm questions) {
		questionDao.saveAll(questions);
	}*/
}
