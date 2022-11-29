package ru.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity // конфиг для Spring Security
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	@Override // настраивает авторизацию
	protected void configure(HttpSecurity http) throws Exception {
		http

				.csrf().ignoringAntMatchers("/admin/update")

				.and()

				.authorizeRequests()
				.antMatchers("/info").permitAll()	// сюда всех пускам
				.antMatchers("/market").permitAll()
				// пускаем только с ролью ADMIN, которую добавляем в CustomDetailedService.loadUserByUsername
				.antMatchers("/admin/*").hasRole("ADMIN")
				.anyRequest().authenticated()	// остальное по аутентификации

				.and()

				.formLogin()	// форма логина по умолчанию

				.and()

				.exceptionHandling().accessDeniedPage("/access_denied.jsp");
	}

	@Bean // каким образом шифруем пароль
	public PasswordEncoder passwordEncoder() {
		// без шифрования
		return NoOpPasswordEncoder.getInstance();
	}

}
