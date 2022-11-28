package ru.edu.service;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

// репозиторий
@Component
public class UserCache {

	private Map<String, UserInfo> cache = new HashMap<>();

	public UserInfo get(String login) {
		return cache.get(login);
	}

	private void create(UserInfo userInfo) {
		cache.put(userInfo.getLogin(), userInfo);
	}

	@PostConstruct
	public void init() {
		create(new UserInfo("admin",  "qwerty", "Admin user", "ADMIN"));
		create(new UserInfo("prog",  "qwerty", "Programmer", "ADMIN"));
		create(new UserInfo("manager",  "qwerty", "Project manager", "MANAGER"));
	}

}
