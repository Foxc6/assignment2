package com.example.assignment2.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.assignment2.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findByEmail(String email);
	User findUserById(Long id);
	List<User> findAll();
}
