package com.user.service;

import java.util.List;

import javax.management.relation.InvalidRoleValueException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.user.dto.User;
import com.user.repository.UserRepository;
import com.user.roles.Roles;

/**
 * @author Prerna Garg
 *
 */
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	/**
	 * @param userRepository the userRepository to set
	 */
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User createUser(User user) throws InvalidRoleValueException {
		if (Roles.ADMIN.equalsIgnoreCase(user.getRole())) {
			user.setRole(Roles.ADMIN);
		} else if (Roles.USER.equalsIgnoreCase(user.getRole())) {
			user.setRole(Roles.USER);
		} else {
			throw new InvalidRoleValueException();
		}
		return userRepository.createUser(user);
	}

	public List<User> findAllUsers() {
		return userRepository.getAllUsers();
	}

	public void deleteById(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public boolean isAuthorized(int id) {
		User user = userRepository.getUserById(id);
		if (null == user) {
			throw new UsernameNotFoundException(String.valueOf(id));
		} else if ("admin".equalsIgnoreCase(user.getRole())) {
			return true;
		}
		return false;
	}

}
