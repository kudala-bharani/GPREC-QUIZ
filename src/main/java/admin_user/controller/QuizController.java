package admin_user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import admin_user.model.QuestionWrapper;
import admin_user.model.Questions;
import admin_user.model.Response;
import admin_user.repositories.QuestionDao;
import admin_user.service.QuestionService;
import admin_user.service.QuizService;


@RestController
@RequestMapping("admin-page")
public class QuizController {
	
	@Autowired
	QuizService quizservice;
	
	@Autowired
	QuestionService questionservice;
	
	@Autowired
	QuestionDao questionDao;
	
	@RequestMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam String title) {
		return quizservice.createQuiz(category, title);
	}
	
	@GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizservice.getQuizQuestions(id);
    }
	
	 @PostMapping("submit/{id}")
	    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
	        return quizservice.calculateResult(id, responses);
	 }

}
