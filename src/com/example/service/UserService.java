package com.example.service;

import com.example.dao.UserDao;
import com.example.entity.User;

public class UserService {

	private final UserDao dao;

	public UserService(UserDao dao) {
		this.dao = dao;
	}

	public boolean canLogin(String userId, String pass) {
		throw new RuntimeException("LoginService.canLogin() No implementado");
	}

	public boolean addUser(String userId, String name, String email, String pass) {
		User user = new User();
		user.setUserId(userId);
		user.setName(name);
		user.setEmail(email);
		dao.insert(user);
		dao.setPassword(user, pass);
		return true;
	}
}
