package com.usernexusapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.usernexusapp.entity.User;
import com.usernexusapp.repository.UserRepository;

@Component
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	// Create User
	@Override
	public User createUser(User newUser) {
		return userRepository.save(newUser);//saving a new user data
	}

	
	// Get All Users
	@Override
	public List<User> readAllUsers() {
		return userRepository.findAll();//fetching all users from database
	}

	
	// Update User
	@Override
	public User updateUser(Integer id, User updateUser) {
		return userRepository.findById(id)
				.map(user ->{
					user.setName(updateUser.getName());
					user.setAddress(updateUser.getAddress());
					user.setMobNo(updateUser.getMobNo());
					return userRepository.save(user);
				})
				.orElseThrow(() -> new RuntimeException("User not found..!"));
	}

	
	// Get User By Id
	@Override
	public Optional<User> getUserById(Integer id) {
		return userRepository.findById(id);
	}

	
	// Delete a User by id
	@Override
	public boolean deleteUser(Integer id) {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}

}