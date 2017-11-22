package com.example.assignment2.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.assignment2.models.User;
import com.example.assignment2.services.UserService;

@Controller
public class Users {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	private final UserService userService;
	
	public Users(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/")
	public String home(@ModelAttribute("user") User user) {
		return "index.jsp";
	}
	
	@PostMapping("/register/user")
	public String userHandler(@RequestParam("c_password") String c_password, @Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		if(result.hasErrors() || session.getAttribute("user_id") != null) {
			return "redirect:/";
		} else {
			// System.out.println(user);
			userService.saveUser(user, c_password, session);
			if(session.getAttribute("user_id") != null) {
				return "redirect:/bright_ideas";
			} else {
				return "index.jsp";
			}
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/login/user")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
		if(userService.login(email, password, session)) {
			return "redirect:/bright_ideas";
		}
		return "redirect:/";
	}
	
	@RequestMapping("/users/{UserId}")
	public String showUser(Model model, @PathVariable("UserId") Long UserId, HttpSession session) {
		// First make sure that a user is logged in to view page
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
				
		User user = userService.findUserById(UserId);
		model.addAttribute("user", user);
		
		// likeCount (_countByUserId) provides number of Likes for the User
		System.out.println(UserId);
		Long likeCount = userService._countByUserId(UserId);
		model.addAttribute("likeCount", likeCount);
		
		// likeCount (countByUserId) provides number of Ideas for the User
		Long count = userService.countByUserId(UserId);
		model.addAttribute("count", count);
		
		return "show_user.jsp";
	}

}
