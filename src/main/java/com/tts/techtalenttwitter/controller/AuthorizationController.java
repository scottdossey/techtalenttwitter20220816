/* This class is going to listen to the login page
 * and display our login form.
 * 
 * It also is going to handle our signup form
 */
package com.tts.techtalenttwitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tts.techtalenttwitter.model.User;

@Controller
public class AuthorizationController {
	@GetMapping(path="/login")
	public String login() {
		return "login";
	}
	
	@GetMapping(path="/signup")
	public String registration(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}
	
}
