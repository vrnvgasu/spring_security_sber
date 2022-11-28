package ru.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManager {

	private UserCache userCache;

	@Autowired
	public void setUserCache(UserCache userCache) {
		this.userCache = userCache;
	}

	public AuthResult authorize(String login, String password) {
		UserInfo userInfo = userCache.get(login);

		if (userInfo == null) {
			return new AuthResult("User login=" + login + " not found");
		}

		if (!userInfo.getPassword().equals(password)) {
			return new AuthResult("Incorrect credentials");
		}

		if (!"ADMIN".equals(userInfo.getRole())) {
			return new AuthResult("Access denied!");
		}

		return new AuthResult("OK");
	}

}
