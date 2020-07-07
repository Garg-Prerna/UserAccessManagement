package com.user.controller;

import java.util.List;

import javax.management.relation.InvalidRoleValueException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.User;
import com.user.service.UserService;

/**
 * @author Prerna Garg
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/post/users")
	public ResponseEntity<String> createUser(@RequestBody User user, @RequestHeader int adminId)
			throws InvalidRoleValueException {
		if (userService.isAuthorized(adminId)) {
			userService.createUser(user);
			return new ResponseEntity<String>("User created successfully with id " + user.getId(), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("User does not have required role to perform this operation.",
					HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(value = "/delete/users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id, @RequestHeader int adminId) {
		if (userService.isAuthorized(adminId)) {
			userService.deleteById(id);
			return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("User does not have required role to perform this operation.",
					HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(value = "/get/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> findUsers() {
		List<User> users = userService.findAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
}
