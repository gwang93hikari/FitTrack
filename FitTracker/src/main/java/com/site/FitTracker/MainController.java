package com.site.FitTracker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String index() {
		return "index" ;
	}
	
	@GetMapping("/writeFitRecord")
	public String writeFitRecord() {
		return "writeFitRecord";
	}

}
