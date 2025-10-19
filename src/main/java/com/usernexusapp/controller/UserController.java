package com.usernexusapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usernexusapp.entity.User;
import com.usernexusapp.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;// Dependency Injection

	// REST End-points

	// ✅ CREATE
	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User newUser = userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}

	// ✅ READ ALL
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.readAllUsers();
		if (users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users);
	}

	// ✅ READ BY ID
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id) {
		Optional<User> user = userService.getUserById(id);
		return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// ✅ UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User userDetails) {
		User updatedUser = userService.updateUser(id, userDetails);
		if (updatedUser == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedUser);
	}

	// ✅ DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
		boolean isDeleted = userService.deleteUser(id);
		if (isDeleted) {
			return ResponseEntity.ok("User deleted successfully.");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
	}
}