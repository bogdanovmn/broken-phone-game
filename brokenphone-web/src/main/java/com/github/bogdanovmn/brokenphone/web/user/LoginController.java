package com.github.bogdanovmn.brokenphone.web.user;

import com.github.bogdanovmn.brokenphone.web.infrastructure.AbstractMinVisualController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
class LoginController extends AbstractMinVisualController {
	@GetMapping("/login")
	ModelAndView form(Model model, String error) {
		if (getUser() != null) {
			return new ModelAndView("redirect:/index");
		}

		if (error != null) {
			model.addAttribute("customError", "Попробуйте еще разок");
		}

		return new ModelAndView("login", model.asMap());
	}
}
