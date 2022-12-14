/* This class is going to listen to the login page
 * and display our login form.
 * 
 * It also is going to handle our signup form
 */
package com.tts.techtalenttwitter.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.techtalenttwitter.model.User;
import com.tts.techtalenttwitter.service.UserService;

@Controller
public class AuthorizationController {
	@Autowired
	UserService userService;
	
	@GetMapping(path="/login")
	public String login() {
		return "login";
	}
	
	@GetMapping(path="/signup")
	public String registration(User user) {		
		return "registration";
	}
	
	@PostMapping(path="/signup")
	public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
		User userExists = userService.findByUsername(user.getUsername());
		if (userExists != null) {
			bindingResult.rejectValue("username", "error.user", "Username is already taken");
		}
		
		if(!bindingResult.hasErrors()) {
			userService.saveNewUser(user);
			model.addAttribute("success", "Sign up successful!");
			model.addAttribute("user", new User());				
		}
		return "registration";		
	}		
}
