/**
 * 
 */
package com.simiyu.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author enock
 *
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	private String name;
	private String username;
	private String password;
	

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
		name="user_role",
		joinColumns = {
                @JoinColumn(name = "user_id", referencedColumnName = "id",
                        nullable = false, updatable = false)},
        inverseJoinColumns = {
                @JoinColumn(name = "role_id", referencedColumnName = "id",
                        nullable = false, updatable = false)})
	private List<Role> roles;
	public User(){
	}

	
	public User(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
	
	
}
