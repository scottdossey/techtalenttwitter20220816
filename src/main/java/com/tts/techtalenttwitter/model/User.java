package com.tts.techtalenttwitter.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates Getters and Setters
      // Generates a default toString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_profile")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;
	
	private String email;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int active;
	
	@CreationTimestamp
	private Date createdAt;
	
	//This will associate roles with a user.
	//This is a collection.....
	//We generally do not store multiple elements
	//in a single field in a database. So how is this
	//going to be represented with the JPA?
	

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
}
