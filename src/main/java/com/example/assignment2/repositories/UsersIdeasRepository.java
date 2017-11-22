package com.example.assignment2.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.assignment2.models.UsersIdeas;

@Repository
public interface UsersIdeasRepository extends CrudRepository<UsersIdeas, Long>{
	Long countByUser_id(Long id);

}
