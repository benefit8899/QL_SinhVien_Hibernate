package com.vanloi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vanloi.dao.UserDao;
import com.vanloi.model.User;

@Service("userService")
public class UserService {

	@Autowired
	UserDao userDao;

	@Transactional
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Transactional
	public User getUser(int id) {
		return userDao.getUser(id);
	}

	@Transactional
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	@Transactional
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Transactional
	public void updateUser(User user) {
		userDao.updateUser(user);

	}

	@Transactional
	public void deleteUser(int id) {
		userDao.deleteUser(id);
	}
}