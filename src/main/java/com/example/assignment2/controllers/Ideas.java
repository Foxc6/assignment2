package com.example.assignment2.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.assignment2.models.Idea;
import com.example.assignment2.models.User;
import com.example.assignment2.models.UsersIdeas;
import com.example.assignment2.services.IdeaService;
import com.example.assignment2.services.UserService;

@Controller
public class Ideas {
	private final IdeaService ideaService;
	private final UserService userService;
	
	public Ideas(IdeaService ideaService, UserService userService) {
		this.ideaService = ideaService;
		this.userService = userService;
	}
	
	@RequestMapping("/bright_ideas")
	public String getIdeas(HttpSession session, @ModelAttribute("idea") Idea idea, Model model, @ModelAttribute("userIdeas") UsersIdeas userLikes) {
		// System.out.println(session.getAttribute("user_id"));
		// First make sure that a user is logged in to view page
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		
		// Get currently logged-in User
		User user =  userService.findUserById((long) session.getAttribute("user_id"));
		model.addAttribute("userName", user.getName());
		model.addAttribute("user", user);
		
		// Get list of Ideas
		model.addAttribute("ideas", ideaService.getAll());
		return "bright_ideas.jsp";
	}
	
	@RequestMapping("/add_idea")
	public String addIdea(@Valid @ModelAttribute("idea") Idea idea, HttpSession session, BindingResult result, Model model) {
		ideaService.createIdea(idea, session);
		return "redirect:/bright_ideas";
	}
	
	@RequestMapping("/addLike")
	public String addLike(@Valid @ModelAttribute("userLike") UsersIdeas usersLikes, HttpSession session) {
		ideaService.addLike(usersLikes, session);
		return "redirect:/bright_ideas";
	}
	
	@RequestMapping("/ideas/{IdeaId}")
	public String showIdea(Model model, @PathVariable("IdeaId") Long IdeaId, HttpSession session) {
		// First make sure that a user is logged in to view page
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}		
		Idea idea = ideaService.getIdea(IdeaId);
		String ideaContent = idea.getContent();
		model.addAttribute("content", ideaContent);
		model.addAttribute("idea", idea);
		// Create Method to get list of all users that have liked this post
		// When user list is returned, render only the user alias and user name
		return "show_idea.jsp";
	}
	
	@RequestMapping(value="/ideas/delete/{id}")
    public String destroyIdea(@PathVariable("id") Long id) {
        ideaService.destroyIdea(id);
        return "redirect:/bright_ideas";
    }

}
