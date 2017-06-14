package com.zbwfisher.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {
	@GetMapping("/html")
	public String html(@RequestParam("resource") String resource) {
		return resource + ".html";
	}

	@RequestMapping("/")
	public String homepage() {
		return ("redirect:/index.html");

	}
}
