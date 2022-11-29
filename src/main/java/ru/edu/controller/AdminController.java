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
import ru.edu.service.Product;
import ru.edu.service.ProductCache;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	private ProductCache productCache;

	@Autowired
	public void setProductCache(ProductCache productCache) {
		this.productCache = productCache;
	}

	// можем сделать DI Request и Response в контроллере
	@GetMapping("/create")
	public ModelAndView createProductView(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return new ModelAndView("/create_product.jsp");
	}

	@PostMapping("/update")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("price") String price)
			throws IOException {
		Product info = new Product(id, name, Double.parseDouble(price));
		productCache.update(info);

		ModelAndView modelAndView = new ModelAndView("/success_create_product.jsp");
		return modelAndView;
	}

}
