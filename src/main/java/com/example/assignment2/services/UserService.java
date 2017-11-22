package com.example.assignment2.services;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.assignment2.models.User;
import com.example.assignment2.repositories.IdeaRepository;
import com.example.assignment2.repositories.UserRepository;
import com.example.assignment2.repositories.UsersIdeasRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final IdeaRepository ideaRepository;
	private final UsersIdeasRepository usersIdeasRepository;
	
	public UserService(UserRepository userRepository, IdeaRepository ideaRepository, UsersIdeasRepository usersIdeasRepository) {
		super();
		this.userRepository = userRepository;
		this.ideaRepository = ideaRepository;
		this.usersIdeasRepository = usersIdeasRepository;
	}
	
	public Boolean login(String email, String password, HttpSession session) {
		User user = userRepository.findByEmail(email);
		if(user != null) {
			System.out.println("Good email");
			if(BCrypt.checkpw(password, user.getPassword())) {
				session.setAttribute("user_id", user.getId());
				return true;
			} else {
			System.out.println("Bad email");
			return false;
			}
		}
		System.out.println("Bad email");
		return false;
	}
	
	public String saveUser(User user, String c_password, HttpSession session) {
		if(!user.getPassword().equals(c_password)) {
			System.out.println("Password don't match!");
			return "Password don't match!";
		} 
		if(userRepository.findByEmail(user.getEmail()) == null) {
			user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
			userRepository.save(user);
			session.setAttribute("user_id", user.getId());
			System.out.println("Success!");
			return "Success!";
		} else {
			System.out.println("An account with that email already exists!");
			return "An account with that email already exists!";
		}
	}
	
	public User findUserById(Long id) {
        return userRepository.findOne(id);
    }
	
	public Long countByUserId(Long id) {
		return ideaRepository.countByUser_id(id);
	}
	
	public Long _countByUserId(Long id) {
		return usersIdeasRepository.countByUser_id(id);
	}

}
