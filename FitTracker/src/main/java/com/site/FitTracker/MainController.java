package com.site.FitTracker;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	
	@GetMapping("/")
	public String index() {
		return "index" ;
	}
	
	@GetMapping("/writeFitRecord")
	public String writeFitRecord(Model model) {
		
		//Today
		LocalDate today = LocalDate.now();
		model.addAttribute("today", today);
		
		return "writeFitRecord";
	}

}
