package admin_user.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import admin_user.model.Questions;
import admin_user.model.QuestionsForm;
import admin_user.model.User;
import admin_user.repositories.Details;
import admin_user.service.QuestionService;
import admin_user.service.ResponseService;

@RestController
@RequestMapping("admin-page")
public class AdminController {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	ResponseService responseService;
	
	@Autowired
	Details details;
	
	@GetMapping("allQuestions")
    public ResponseEntity<List<Questions>> getAllQuestions(){
        return questionService.getAllQuestions();
    }
	
	/*@GetMapping("category/{category}")
	public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category){
		
	    return questionService.getQuestionsByCategory(category);
	    
	}*/
	
	@GetMapping("category/{category}")
	public ResponseEntity<String> displayQuestionsByCategory(@PathVariable String category, Model model) {
	    ResponseEntity<List<Questions>> response = questionService.getQuestionsByCategory(category);
	    
	    if (response.getStatusCode() == HttpStatus.OK) {
	        List<Questions> questions = response.getBody();
	        model.addAttribute("categoryName", category);
	        model.addAttribute("questions", questions);
	        return ResponseEntity.ok("questionByCategory"); // Return the name of the HTML template
	    } else {
	        // Handle the case when there is an error, for example, return an error template or a different HTTP status.
	        return ResponseEntity.status(response.getStatusCode()).body("Error occurred");
	    }
	}
	
	@GetMapping("category/{section}/{category}")
	public ResponseEntity<String> displayQuestionsByCategor(@PathVariable String section, @PathVariable String category, Model model) {
	    ResponseEntity<List<Questions>> response = questionService.getQuestionsByCategory(category);
	    
	    if (response.getStatusCode() == HttpStatus.OK) {
	        List<Questions> questions = response.getBody();
	        model.addAttribute("categoryName", category);
	        model.addAttribute("questions", questions);
	        return ResponseEntity.ok("questionByCategory"); // Return the name of the HTML template
	    } else {
	        // Handle the case when there is an error, for example, return an error template or a different HTTP status.
	        return ResponseEntity.status(response.getStatusCode()).body("Error occurred");
	    }
	}
	
	@PutMapping("/update")
	public void updateStudentRecords(@RequestParam String email, @RequestBody Map<String, String> body) {
		
	}
	
	/*
	@RequestMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestParam String category,@RequestBody Questions question,@ModelAttribute QuestionsForm questionsForm, Model model, Principal principal){
		//QuestionsForm questionsForm = new QuestionsForm();
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
			System.out.println(userDetails.getUsername());
		User detail = details.findByEmail(userDetails.getUsername());
		question.setCategory(category);
		question.setSection(detail.getSection());
		questionsForm.setCategory(category);
		questionsForm.setSection(detail.getSection());
        model.addAttribute("questionsForm", questionsForm);
        return questionService.addQuestion(question);
        
    }
    */
	
}
