package com.user.repository;

import java.util.List;

import com.user.dto.User;

/**
 * @author Prerna Garg
 *
 */
public interface UserRepository {

	public User createUser(User user);

	public void deleteById(int id);

	public List<User> getAllUsers();

	public User getUserById(int id);
}
