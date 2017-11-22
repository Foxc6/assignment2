package com.example.assignment2.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.assignment2.models.Idea;
import com.example.assignment2.models.User;

@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long>{
	List<Idea> findAll();
	Long countByUser_id(Long id);
}
