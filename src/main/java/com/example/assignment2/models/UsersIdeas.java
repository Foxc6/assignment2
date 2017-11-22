package com.example.assignment2.models;

import java.util.Date;

import javax.persistence.Column;

// Extra model. This model is not needed in many to many relationships, but we are adding so that you can see
// how you can have more column in the middle joining table and not just the foreign keys.
// Make sure that you have the table name correctly and set correct relationships

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="users_ideas")
public class UsersIdeas {
    @Id
    @GeneratedValue
    private Long id;
    
	@Column(updatable=false)
	private Date createdAt;
 	private Date updatedAt;
    
    // Many to one relationship for the foreign keys. The middle joining table has many-to-one relationships with
 	// the products table and categories table.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idea_id")
	private Idea idea;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	public UsersIdeas() {
	}
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Idea getIdea() {
		return idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
