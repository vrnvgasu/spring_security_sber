package ru.edu.service;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
//UserDetailsService - интерфейс для Spring Security
// позволяет Spring Security найти пользователя по идентификатору
public class CustomDetailedService implements UserDetailsService {

	private UserCache userCache;

	@Autowired
	public void setUserCache(UserCache userCache) {
		this.userCache = userCache;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = userCache.get(username);

		if (userInfo == null) {
			throw new UsernameNotFoundException("User=" + username + " not found");
		}

		// authorities - роли и привилегии
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + userInfo.getRole()));
		return new User(userInfo.getLogin(), userInfo.getPassword(), authorities);
	}

}
