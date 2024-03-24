package admin_user.controller;



import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import admin_user.dto.UserDto;
import admin_user.model.NoticeBoard;
import admin_user.model.Questions;
import admin_user.model.Result;
import admin_user.model.User;
import admin_user.repositories.Details;
import admin_user.repositories.NoticeBoardRepository;
import admin_user.repositories.QuestionDao;
import admin_user.repositories.ResultRepository;
import admin_user.service.QuestionService;
import admin_user.service.UserService;

@Controller
public class UserController {
	
	String category=null;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	ResultRepository resultRepository;
	
	@Autowired
	private UserService userService;
	
//	@Autowired
//	NoticeBoard noticeboard;
	
	@Autowired
	NoticeBoardRepository noticeboardrepository;
	
	@Autowired
	QuestionDao questionDao;
	
	@Autowired
	Details details;
	
	
	@GetMapping("/login")
	public String log() {
		return "login";
	}
	
	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}
	
	@GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto()); // Add an empty UserDto to the model
        return "signup";
    }
	
	@PostMapping("/registration")
	public String reg(@ModelAttribute("user") UserDto userDto, Model model) {
		userService.save(userDto);
		model.addAttribute("message", "Registered Successfuly!");
		return "signup";
	}
	
	@GetMapping("/category/{category}")
	public String getQuestionsByCategory(@PathVariable String category, Model model) {
		ResponseEntity<List<Questions>> response = questionService.getQuestionsByCategory(category);
		if (response.getStatusCode() == HttpStatus.OK) {
	        List<Questions> questions = response.getBody();
	        model.addAttribute("categoryName", category);
	        model.addAttribute("questions", questions);
	        return "questionByCategory"; // Return the name of the HTML template
	    } else {
	        // Handle the case when there is an error, for example, return an error template or a different HTTP status.
	        return "Error occurred";
	    }
	}
	
	
	@PostMapping("/log")
	public String log(Model model) {
		model.addAttribute("users",new User());
		return "log";
		
	}
	
	@GetMapping("/category/{section}/{cat}")
	public String loadquiz(@PathVariable String section, @PathVariable String cat, Model model, Principal principal){
		//String category = "nscq1";
		ResponseEntity<List<Questions>> response = questionService.getQuestionsByCategoryAndSection(cat,section);
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
			
		User detail = details.findByEmail(userDetails.getUsername());
		System.out.println(detail.getEmail());
		List<Questions> que = response.getBody();
		if(que.size() == 0) {
			return "quiznotready";
		}
		if (response.getStatusCode() == HttpStatus.OK) {
			if(checkscore(section, cat, detail.getEmail())) {
				List<Questions> questions = response.getBody();
		        model.addAttribute("categoryName", cat);
		        model.addAttribute("questions", questions);
		        return "questionByCategory";
			} else {
				return "quizattempted";
			}
	        
	    } else {
	        // Handle the case when there is an error, for example, return an error template or a different HTTP status.
	        return "Error occurred";
	    }
	}
	
	public boolean checkscore(String section, String category, String rollnumber) {
		List<Result> res = resultRepository.findBySection(section);
		Result stu = resultRepository.findByRollnumber(rollnumber);
		if(category.equals("nscq1")) {
			if(stu.getNscq1() != null) {
				return false;
			}
		} else if(category.equals("nscq2")) {
			if(stu.getNscq2() != null) {
				return false;
			}
		} else if(category.equals("nscq3")) {
			if(stu.getNscq3() != null) {
				return false;
			}
		} else if(category.equals("nscq4")) {
			if(stu.getNscq4() != null) {
				return false;
			}
		} else if(category.equals("nscq5")) {
			if(stu.getNscq5() != null) {
				return false;
			}
		} else if(category.equals("ccq1")) {
			if(stu.getCcq1() != null) {
				return false;
			}
		} else if(category.equals("ccq2")) {
			if(stu.getCcq2() != null) {
				return false;
			}
		} else if(category.equals("ccq3")) {
			if(stu.getCcq3() != null) {
				return false;
			}
		} else if(category.equals("ccq4")) {
			if(stu.getCcq4() != null) {
				return false;
			}
		} else if(category.equals("ccq5")) {
			if(stu.getCcq5() != null) {
				return false;
			}
		} else if(category.equals("mwaq1")) {
			if(stu.getMwaq1() != null) {
				return false;
			}
		} else if(category.equals("mwaq2")) {
			if(stu.getMwaq2() != null) {
				return false;
			}
		} else if(category.equals("mwaq3")) {
			if(stu.getMwaq3() != null) {
				return false;
			}
		} else if(category.equals("mwaq4")) {
			if(stu.getMwaq4() != null) {
				return false;
			}
		} else if(category.equals("mwaq5")) {
			if(stu.getMwaq5() != null) {
				return false;
			}
		} else if(category.equals("amq1")) {
			if(stu.getAmq1() != null) {
				return false;
			}
		} else if(category.equals("amq2")) {
			if(stu.getAmq2() != null) {
				return false;
			}
		} else if(category.equals("amq3")) {
			if(stu.getAmq3() != null) {
				return false;
			}
		} else if(category.equals("amq4")) {
			if(stu.getAmq4() != null) {
				return false;
			}
		} else if(category.equals("amq5")) {
			if(stu.getAmq5() != null) {
				return false;
			}
		} else if(category.equals("dlq1")) {
			if(stu.getDlq1() != null) {
				return false;
			}
		} else if(category.equals("dlq2")) {
			if(stu.getDlq2() != null) {
				return false;
			}
		} else if(category.equals("dlq3")) {
			if(stu.getDlq3() != null) {
				return false;
			}
		} else if(category.equals("dlq4")) {
			if(stu.getDlq4() != null) {
				return false;
			}
		} else if(category.equals("dlq5")) {
			if(stu.getDlq5() != null) {
				return false;
			}
		} else if(category.equals("uhvq1")) {
			if(stu.getUhvq1() != null) {
				return false;
			}
		} else if(category.equals("uhvq2")) {
			if(stu.getUhvq2() != null) {
				return false;
			}
		} else if(category.equals("uhvq3")) {
			if(stu.getUhvq3() != null) {
				return false;
			}
		} else if(category.equals("uhvq4")) {
			if(stu.getUhvq4() != null) {
				return false;
			}
		} else if(category.equals("uhvq5")) {
			if(stu.getUhvq5() != null) {
				return false;
			}
		} 
		return true;
	}
	
	@GetMapping("/user-page/dsa")
	public String dsa() {
		return "dsa";
	}
	
	// trails
	@GetMapping("/user-page/hello")
	public String hello(Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		//	System.out.println(userDetail.);
			
			User detail = details.findByEmail(userDetails.getUsername());
			List<NoticeBoard> updates = noticeboardrepository.findBySection(detail.getSection());
			System.out.println(detail.getSection());
			model.addAttribute("detail",detail);
			model.addAttribute("updates", updates);
			model.addAttribute("user", userDetails);
		return "User_Home";
	}
	
	@GetMapping("/user-page/user_quiz")
	public String quiz(Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		//	System.out.println(userDetail.);
			
			User detail = details.findByEmail(userDetails.getUsername());
			System.out.println(detail.getSection());
			model.addAttribute("detail",detail);
			model.addAttribute("user", userDetails);
		return "user_quiz";
	}
	
	
	@GetMapping("/user-page/category/{category}")
	public ResponseEntity<String> quizform(@PathVariable String category, Model model) {
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
	
	@GetMapping("user-page")
	public String userPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
	//	System.out.println(userDetail.);
		
		User detail = details.findByEmail(userDetails.getUsername());
		System.out.println(detail.getSection());
		model.addAttribute("detail",detail);
		model.addAttribute("user", userDetails);
		return "user";
	}
	
	@PostMapping("/category/submit_answers")
	 public String submitAnswers(@RequestParam Map<String, String> allRequestParams, Model model,  Principal principal) {
	        // Extract answers and question IDs from the request parameters		
		 //Questions question = questionservice.getById()
		 int score = 0;
		 String category = "";
		 
		 for(Map.Entry<String,String> e : allRequestParams.entrySet()) {
			 Questions question = questionService.getQuestionById(Integer.parseInt(e.getKey()));
			 System.out.println(e.getKey()+" "+e.getValue());
			 category = question.getCategory();
			 if(question != null) {
				 if(e.getValue().equals(question.getRightAnswer())) {
					 score++;
				 }
			 }
		 }
		 
		 UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		 System.out.println(userDetails.getUsername());
		 Result result = resultRepository.findByRollnumber(userDetails.getUsername());
		User detail = details.findByEmail(userDetails.getUsername());

		 if(result == null) {
			 System.out.println("Result is null");
		 }
		 else if (result != null) {
			    // Update the score based on the category
			    if ("nscq1".equals(category)) {
			        result.setNscq1(score);
			    } else if ("nscq2".equals(category)) {
			        result.setNscq2(score);
			    } else if ("nscq3".equals(category)) {
			        result.setNscq3(score);
			    } else if ("nscq4".equals(category)) {
			        result.setNscq4(score);
			    } else if ("nscq5".equals(category)) {
			        result.setNscq5(score);
			    } else if ("ccq1".equals(category)) {
			        result.setNscq1(score);
			    } else if ("ccq2".equals(category)) {
			        result.setNscq2(score);
			    } else if ("ccq3".equals(category)) {
			        result.setNscq3(score);
			    } else if ("ccq4".equals(category)) {
			        result.setNscq4(score);
			    } else if ("ccq5".equals(category)) {
			        result.setNscq5(score);
			    } else if ("dlq1".equals(category)) {
			        result.setNscq1(score);
			    } else if ("dlq2".equals(category)) {
			        result.setNscq2(score);
			    } else if ("dlq3".equals(category)) {
			        result.setNscq3(score);
			    } else if ("dlq4".equals(category)) {
			        result.setNscq4(score);
			    } else if ("dlq5".equals(category)) {
			        result.setNscq5(score);
			    } else if ("mwaq1".equals(category)) {
			        result.setNscq1(score);
			    } else if ("mwaq2".equals(category)) {
			        result.setNscq2(score);
			    } else if ("mwaq3".equals(category)) {
			        result.setNscq3(score);
			    } else if ("mwaq4".equals(category)) {
			        result.setNscq4(score);
			    } else if ("mwaq5".equals(category)) {
			        result.setNscq5(score);
			    } else if ("amq1".equals(category)) {
			        result.setNscq1(score);
			    } else if ("amq2".equals(category)) {
			        result.setNscq2(score);
			    } else if ("amq3".equals(category)) {
			        result.setNscq3(score);
			    } else if ("amq4".equals(category)) {
			        result.setNscq4(score);
			    } else if ("amq5".equals(category)) {
			        result.setNscq5(score);
			    } else if ("uhvq1".equals(category)) {
			        result.setNscq1(score);
			    } else if ("uhvq2".equals(category)) {
			        result.setNscq2(score);
			    } else if ("uhvq3".equals(category)) {
			        result.setNscq3(score);
			    } else if ("uhvq4".equals(category)) {
			        result.setNscq4(score);
			    } else if ("uhvq5".equals(category)) {
			        result.setNscq5(score);
			    } 
			    resultRepository.save(result);
		 } 
		
		 model.addAttribute("user", userDetails);
		 model.addAttribute("detail", detail);
		 System.out.println(score);
		 
	        return "submitpage"; // Redirect to a result page
	    }
	
}
