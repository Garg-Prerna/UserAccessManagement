package com.user.service;

import java.util.List;

import javax.management.relation.InvalidRoleValueException;

import org.springframework.stereotype.Service;

import com.user.dto.User;

/**
 * @author Prerna Garg
 *
 */
@Service
public interface UserService {

	public User createUser(User user) throws InvalidRoleValueException;

	public List<User> findAllUsers();

	public void deleteById(int id);

	public boolean isAuthorized(int id);

}
