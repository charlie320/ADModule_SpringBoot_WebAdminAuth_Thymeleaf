package com.perscholas.springboot_basic_web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

		@GetMapping("/thymeleaf")
		public String showThymeleafPage() {
			return "Thymeleaf";
		}
		
}
