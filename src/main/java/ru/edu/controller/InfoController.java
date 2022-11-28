package ru.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/info")
public class InfoController {

	@GetMapping
	public ModelAndView info() {
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("/info.jsp");
		 modelAndView.addObject("author", "Dmitrii");
		 modelAndView.addObject("title", "Custom secu red app");
		 return modelAndView;
	}

}
