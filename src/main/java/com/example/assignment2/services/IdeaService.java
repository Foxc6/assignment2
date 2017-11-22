package com.example.assignment2.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.assignment2.models.Idea;
import com.example.assignment2.models.UsersIdeas;
import com.example.assignment2.repositories.IdeaRepository;
import com.example.assignment2.repositories.UserRepository;
import com.example.assignment2.repositories.UsersIdeasRepository;

@Service
public class IdeaService {
	private final IdeaRepository ideaRepository;
	private final UserRepository userRepository;
	private final UsersIdeasRepository usersIdeasRepository;
	
	public IdeaService(IdeaRepository ideaRepository, UserRepository userRepository, UsersIdeasRepository usersIdeasRepository) {
		this.ideaRepository = ideaRepository;
		this.userRepository = userRepository;
		this.usersIdeasRepository = usersIdeasRepository;
		
	}
	
	public Idea createIdea(Idea idea, HttpSession session) {
		idea.setUser(userRepository.findUserById((long) session.getAttribute("user_id")));
		return ideaRepository.save(idea);
	}
	
	public List<Idea> getAll() {
		return ideaRepository.findAll();
	}
	
	public Idea getIdea(Long id) {
		return ideaRepository.findOne(id);
	}
	
	public void addLike(UsersIdeas usersLikes, HttpSession session) {
		usersLikes.setUser(userRepository.findUserById((long) session.getAttribute("user_id")));

		usersIdeasRepository.save(usersLikes);
	}
	
	public void destroyIdea(Long id) {
        ideaRepository.delete(id);
    }

}
