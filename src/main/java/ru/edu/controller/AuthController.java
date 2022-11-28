package ru.edu.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.service.AuthResult;
import ru.edu.service.AuthenticationManager;

@Controller
@RequestMapping("/auth")
public class AuthController {

	private AuthenticationManager authenticationManager;

	private final String SUCCESS_LOGIN = "/admin/create";

	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@GetMapping("/login")
	public ModelAndView getLoginForm() {
		return new ModelAndView("/login_form.jsp");
	}

	@PostMapping("/authorize")
	public ModelAndView authorize(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("login") String login, @RequestParam("password") String password) throws IOException {
		HttpSession session = request.getSession();

		AuthResult authResult = authenticationManager.authorize(login, password);

		if ("OK".equals(authResult.getStatus())) {
			session.setAttribute("authObject", "AUTHENTICATED");
			response.sendRedirect(SUCCESS_LOGIN);
			return null;
		} else {
			session.removeAttribute("authObject");

			ModelAndView modelAndView = new ModelAndView("/login_form.jsp");
			modelAndView.addObject("message", "Access denied!");
			return modelAndView;
		}
	}

	@PostMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("authObject");
		response.sendRedirect(SUCCESS_LOGIN);
	}

}
