package admin_user.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import admin_user.model.NoticeBoard;
import admin_user.model.Questions;
import admin_user.model.QuestionsForm;
import admin_user.model.Result;
import admin_user.model.User;
import admin_user.repositories.Details;
import admin_user.repositories.NoticeBoardRepository;
import admin_user.repositories.QuestionDao;
import admin_user.repositories.ResultRepository;
import admin_user.service.QuestionService;
import admin_user.service.UserService;

@Controller
public class AdmController {
	
	String category=null;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	ResultRepository resultRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	QuestionDao questionDao;
	
	@Autowired
	Details details;
	
	@Autowired
	NoticeBoardRepository noticeboard;
	
	String section = null;
	
	@GetMapping("adm")
	public String adm() {
		return "adminpage";
	}
	
	// After successful login (Admin/Faculty)
	@GetMapping("admin-page")
	public String adminPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		User detail = details.findByEmail(userDetails.getUsername());
		this.section = detail.getSection();
		this.category = null;
		return "admin";
	}
	
	@PostMapping("/admin-page/noticeboard/addnew")
	public String addnewupdate(Model model, Principal principal, @ModelAttribute("notice") NoticeBoard notice) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		User detail = details.findByEmail(userDetails.getUsername());
		this.section = detail.getSection();
		NoticeBoard newupdate = new NoticeBoard(notice.getUpdates(), detail.getSection());
		model.addAttribute("detail",detail);
		noticeboard.save(newupdate);
		model.addAttribute("notice",new NoticeBoard());
		return "noticeboard";
	}
	
	@GetMapping("/admin-page/noticeboard")
	public String noticeboard(Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		model.addAttribute("notice", new NoticeBoard());
		return "noticeboard";
	}
	
	// To create quiz with category
	@GetMapping("/admin-page/add")
	public String addQest(Model model,@RequestParam String category, Principal principal) {
		this.category = category;
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		User detail = details.findByEmail(userDetails.getUsername());
		model.addAttribute("detail", detail);
		model.addAttribute("category", category);
		model.addAttribute("questionsForm", new QuestionsForm());
		List<Questions> allquestions = questionDao.findAllQuestionsByCategoryAndSection(category, section);
		model.addAttribute("allquestions", allquestions);
		System.out.println(section);
		System.out.println(category);
		
		model.addAttribute("questionsForm",new QuestionsForm());
		return "add";
	} 
	
	@PostMapping("/admin-page/delete-question")
	public String deleteQuestion(@RequestParam Integer questionId) {
	  
	        // Call your service or repository method to delete the question by ID
	        questionDao.deleteById(questionId);
	        return "redirect:/admin-page/add?category=" + category;
	   
	}


	
	// Adding a question to database
	@PostMapping("/admin-page/addd")
	public String addQuest(Model model, Principal principal, @ModelAttribute("questionsForm") Questions questions) {
	//	System.out.println(questions.getQuestionTitle());
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		User detail = details.findByEmail(userDetails.getUsername());
		Questions question = new Questions(questions.getQuestionTitle(),questions.getOption1(),questions.getOption2(),questions.getOption3(),questions.getOption4(),questions.getRightAnswer(),category,detail.getSection());
		model.addAttribute("detail",detail);
		model.addAttribute("category", category);
		questionDao.save(question);
		List<Questions> allquestions = questionDao.findAllQuestionsByCategoryAndSection(category, section);
		model.addAttribute("allquestions",allquestions);
		model.addAttribute("questionsForm",new QuestionsForm());
		model.addAttribute("detail",detail);
		return "add";
	}
	
	// Final Report
	@GetMapping("/admin-page/finalreport")
	public String generateFinalReport(Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		User detail = details.findByEmail(userDetails.getUsername());
		category = detail.getSubject();
		List<Result> res = resultRepository.findBySection(section);
		for(int i=0;i<res.size();i++) {
			Integer a=0,b=0,c=0,d=0,e=0;
			Result r = res.get(i);
			ArrayList<Integer> sco = new ArrayList<Integer>();
			if(category.equals("nsc")) {
				a = r.getNscq1();
				b = r.getNscq2();
				c = r.getNscq3();
				d = r.getNscq4();
				e = r.getNscq5();
				sco.add(a);sco.add(b);sco.add(c);sco.add(d);sco.add(e);
				sco.removeIf(Objects::isNull);
				if(sco.size() == 0) {
					continue;
				}
				if(sco.size() == 1) {
					r.setNSC_Average(sco.get(0));
				} else if(sco.size() == 2) {
					r.setNSC_Average((int) Math.ceil((sco.get(0)+sco.get(1))/2.0));
				} else {
					Collections.sort(sco);
					r.setNSC_Average( (int) Math.ceil((sco.get(sco.size() - 1) + sco.get(sco.size() - 2) + sco.get(sco.size() - 3)) / 3.0));
				}
			} else if(category.equals("cc")) {
				a = r.getCcq1();
				b = r.getCcq2();
				c = r.getCcq3();
				d = r.getCcq4();
				e = r.getCcq5();
				sco.add(a);sco.add(b);sco.add(c);sco.add(d);sco.add(e);
				sco.removeIf(Objects::isNull);
				if(sco.size() == 0) {
					continue;
				}
				if(sco.size() == 1) {
					r.setCC_Average(sco.get(0));
				} else if(sco.size() == 2) {
					r.setCC_Average((int) Math.ceil((sco.get(0)+sco.get(1))/2.0));
				} else {
					Collections.sort(sco);
					r.setCC_Average( (int) Math.ceil((sco.get(sco.size() - 1) + sco.get(sco.size() - 2) + sco.get(sco.size() - 3)) / 3.0));
				}
			} else if(category.equals("mwa")) {
				a = r.getMwaq1();
				b = r.getMwaq2();
				c = r.getMwaq3();
				d = r.getMwaq4();
				e = r.getMwaq5();
				sco.add(a);sco.add(b);sco.add(c);sco.add(d);sco.add(e);
				sco.removeIf(Objects::isNull);
				if(sco.size() == 0) {
					continue;
				}
				if(sco.size() == 1) {
					r.setMWA_Average(sco.get(0));
				} else if(sco.size() == 2) {
					r.setMWA_Average((int) Math.ceil((sco.get(0)+sco.get(1))/2.0));
				} else {
					Collections.sort(sco);
					r.setMWA_Average( (int) Math.ceil((sco.get(sco.size() - 1) + sco.get(sco.size() - 2) + sco.get(sco.size() - 3)) / 3.0));
				}
			} else if(category.equals("am")) {
				a = r.getAmq1();
				b = r.getAmq2();
				c = r.getAmq3();
				d = r.getAmq4();
				e = r.getAmq5();
				sco.add(a);sco.add(b);sco.add(c);sco.add(d);sco.add(e);
				sco.removeIf(Objects::isNull);
				if(sco.size() == 0) {
					continue;
				}
				if(sco.size() == 1) {
					r.setAM_Average(sco.get(0));
				} else if(sco.size() == 2) {
					r.setAM_Average((int) Math.ceil((sco.get(0)+sco.get(1))/2.0));
				} else {
					Collections.sort(sco);
					r.setAM_Average( (int) Math.ceil((sco.get(sco.size() - 1) + sco.get(sco.size() - 2) + sco.get(sco.size() - 3)) / 3.0));
				}
			} else if(category.equals("uhv")) {
				a = r.getUhvq1();
				b = r.getUhvq2();
				c = r.getUhvq3();
				d = r.getUhvq4();
				e = r.getUhvq5();
				sco.add(a);sco.add(b);sco.add(c);sco.add(d);sco.add(e);
				sco.removeIf(Objects::isNull);
				if(sco.size() == 0) {
					continue;
				}
				if(sco.size() == 1) {
					r.setUHV_Average(sco.get(0));
				} else if(sco.size() == 2) {
					r.setUHV_Average((int) Math.ceil((sco.get(0)+sco.get(1))/2.0));
				} else {
					Collections.sort(sco);
					r.setUHV_Average((int) Math.ceil((sco.get(sco.size() - 1) + sco.get(sco.size() - 2) + sco.get(sco.size() - 3)) / 3.0));
				}
			} else if(category.equals("dl")) {
				a = r.getDlq1();
				b = r.getDlq2();
				c = r.getDlq3();
				d = r.getDlq4();
				e = r.getDlq5();
				sco.add(a);sco.add(b);sco.add(c);sco.add(d);sco.add(e);
				sco.removeIf(Objects::isNull);
				if(sco.size() == 0) {
					continue;
				}
				if(sco.size() == 1) {
					r.setDL_Average(sco.get(0));
				} else if(sco.size() == 2) {
					r.setDL_Average((int) Math.ceil((sco.get(0)+sco.get(1))/2.0) );
				} else {
					Collections.sort(sco);
					r.setDL_Average((int) Math.ceil((sco.get(sco.size() - 1) + sco.get(sco.size() - 2) + sco.get(sco.size() - 3)) / 3.0));
				}
				
			} 
			resultRepository.save(r);
			
		}
		System.out.println(category);
		
		Integer maxScore = 0;
		
		List<String> fields = new ArrayList<String>();
		if(category.equals("nsc")) {
			 fields = Arrays.asList("nscq1", "nscq2", "nscq3", "nscq4", "nscq5", "NSC_Average");
		} else if(category.equals("cc")) {
			fields = Arrays.asList("ccq1", "ccq2", "ccq3","ccq4","ccq5", "CC_Average");
		} else if(category.equals("mwa")) {
			fields = Arrays.asList("mwaq1", "mwaq2", "mwaq3","mwaq4","mwaq5", "MWA_Average");
		} else if(category.equals("am")) {
			fields = Arrays.asList("amq1", "amq2", "amq3","amq4","amq5", "AM_Average");
		} else if(category.equals("uhv")) {
			fields = Arrays.asList("uhvq1", "uhvq2", "uhvq3","uhvq4","uhvq5","UHV_Average");
		} else if(category.equals("dl")) {
			fields = Arrays.asList("dlq1", "dlq2", "dlq3","dlq4","dlq5","DL_Average");
		}
		
		model.addAttribute("fields",fields);
		model.addAttribute("res", res); 
		return "finalreport";
	}
	
	// Generate Final Reports
	@GetMapping("/sheet/section/category")
	public String getFinalReport(@RequestParam String section,@RequestParam String category, Model model) {
		System.out.println(section+" "+category);
		
		List<Result> res = resultRepository.findBySection(section);
		for(int i=0;i<res.size();i++) {
			Integer a=0,b=0,c=0,d=0,e=0;
			Result r = res.get(i);
			ArrayList<Integer> sco = new ArrayList<Integer>();
			if(category.equals("nsc")) {
				a = r.getNscq1();
				b = r.getNscq2();
				c = r.getNscq3();
				d = r.getNscq4();
				e = r.getNscq5();
				sco.add(a);sco.add(b);sco.add(c);sco.add(d);sco.add(e);
				sco.removeIf(Objects::isNull);
				if(sco.size() == 0) {
					continue;
				}
				if(sco.size() == 1) {
					r.setNSC_Average(sco.get(0));
				} else if(sco.size() == 2) {
					r.setNSC_Average((int) Math.ceil((sco.get(0)+sco.get(1))/2.0));
				} else {
					Collections.sort(sco);
					r.setNSC_Average( (int) Math.ceil((sco.get(sco.size() - 1) + sco.get(sco.size() - 2) + sco.get(sco.size() - 3)) / 3.0));
				}
			} else if(category.equals("cc")) {
				a = r.getCcq1();
				b = r.getCcq2();
				c = r.getCcq3();
				d = r.getCcq4();
				e = r.getCcq5();
				sco.add(a);sco.add(b);sco.add(c);sco.add(d);sco.add(e);
				sco.removeIf(Objects::isNull);
				if(sco.size() == 0) {
					continue;
				}
				if(sco.size() == 1) {
					r.setCC_Average(sco.get(0));
				} else if(sco.size() == 2) {
					r.setCC_Average((int) Math.ceil((sco.get(0)+sco.get(1))/2.0));
				} else {
					Collections.sort(sco);
					r.setCC_Average( (int) Math.ceil((sco.get(sco.size() - 1) + sco.get(sco.size() - 2) + sco.get(sco.size() - 3)) / 3.0));
				}
			} else if(category.equals("mwa")) {
				a = r.getMwaq1();
				b = r.getMwaq2();
				c = r.getMwaq3();
				d = r.getMwaq4();
				e = r.getMwaq5();
				sco.add(a);sco.add(b);sco.add(c);sco.add(d);sco.add(e);
				sco.removeIf(Objects::isNull);
				if(sco.size() == 0) {
					continue;
				}
				if(sco.size() == 1) {
					r.setMWA_Average(sco.get(0));
				} else if(sco.size() == 2) {
					r.setMWA_Average((int) Math.ceil((sco.get(0)+sco.get(1))/2.0));
				} else {
					Collections.sort(sco);
					r.setMWA_Average( (int) Math.ceil((sco.get(sco.size() - 1) + sco.get(sco.size() - 2) + sco.get(sco.size() - 3)) / 3.0));
				}
			} else if(category.equals("am")) {
				a = r.getAmq1();
				b = r.getAmq2();
				c = r.getAmq3();
				d = r.getAmq4();
				e = r.getAmq5();
				sco.add(a);sco.add(b);sco.add(c);sco.add(d);sco.add(e);
				sco.removeIf(Objects::isNull);
				if(sco.size() == 0) {
					continue;
				}
				if(sco.size() == 1) {
					r.setAM_Average(sco.get(0));
				} else if(sco.size() == 2) {
					r.setAM_Average((int) Math.ceil((sco.get(0)+sco.get(1))/2.0));
				} else {
					Collections.sort(sco);
					r.setAM_Average( (int) Math.ceil((sco.get(sco.size() - 1) + sco.get(sco.size() - 2) + sco.get(sco.size() - 3)) / 3.0));
				}
			} else if(category.equals("uhv")) {
				a = r.getUhvq1();
				b = r.getUhvq2();
				c = r.getUhvq3();
				d = r.getUhvq4();
				e = r.getUhvq5();
				sco.add(a);sco.add(b);sco.add(c);sco.add(d);sco.add(e);
				sco.removeIf(Objects::isNull);
				if(sco.size() == 0) {
					continue;
				}
				if(sco.size() == 1) {
					r.setUHV_Average(sco.get(0));
				} else if(sco.size() == 2) {
					r.setUHV_Average((int) Math.ceil((sco.get(0)+sco.get(1))/2.0));
				} else {
					Collections.sort(sco);
					r.setUHV_Average((int) Math.ceil((sco.get(sco.size() - 1) + sco.get(sco.size() - 2) + sco.get(sco.size() - 3)) / 3.0));
				}
			} else if(category.equals("dl")) {
				a = r.getDlq1();
				b = r.getDlq2();
				c = r.getDlq3();
				d = r.getDlq4();
				e = r.getDlq5();
				sco.add(a);sco.add(b);sco.add(c);sco.add(d);sco.add(e);
				sco.removeIf(Objects::isNull);
				if(sco.size() == 0) {
					continue;
				}
				if(sco.size() == 1) {
					r.setDL_Average(sco.get(0));
				} else if(sco.size() == 2) {
					r.setDL_Average((int) Math.ceil((sco.get(0)+sco.get(1))/2.0) );
				} else {
					Collections.sort(sco);
					r.setDL_Average((int) Math.ceil((sco.get(sco.size() - 1) + sco.get(sco.size() - 2) + sco.get(sco.size() - 3)) / 3.0));
				}
				
			} 
			resultRepository.save(r);
			
		}
		System.out.println(category);
		
		Integer maxScore = 0;
		
		List<String> fields = new ArrayList<String>();
		if(category.equals("nsc")) {
			 fields = Arrays.asList("nscq1", "nscq2", "nscq3", "nscq4", "nscq5", "NSC_Average");
		} else if(category.equals("cc")) {
			fields = Arrays.asList("ccq1", "ccq2", "ccq3","ccq4","ccq5", "CC_Average");
		} else if(category.equals("mwa")) {
			fields = Arrays.asList("mwaq1", "mwaq2", "mwaq3","mwaq4","mwaq5", "MWA_Average");
		} else if(category.equals("am")) {
			fields = Arrays.asList("amq1", "amq2", "amq3","amq4","amq5", "AM_Average");
		} else if(category.equals("uhv")) {
			fields = Arrays.asList("uhvq1", "uhvq2", "uhvq3","uhvq4","uhvq5","UHV_Average");
		} else if(category.equals("dl")) {
			fields = Arrays.asList("dlq1", "dlq2", "dlq3","dlq4","dlq5","DL_Average");
		}
		
		model.addAttribute("fields",fields);
		model.addAttribute("res", res); 
		//return "home";
		return "finalreport";
	}
	
}
