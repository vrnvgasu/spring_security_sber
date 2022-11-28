package ru.edu.service;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationManager {

	public boolean authorize(String login, String password) {
		if ("admin".equals(login) && "qwerty".equals(password)) {
			return true;
		}

		return false;
	}

}
