package com.usernexusapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.usernexusapp.entity.User;

@Service
public interface UserService {
	
	//list of abstract methods used for CRUD operations
	public User createUser(User newUser);
	public List<User> readAllUsers();
	public Optional<User> getUserById(Integer id);
	public User updateUser(Integer id, User updateUser);
	public boolean deleteUser(Integer id);
}