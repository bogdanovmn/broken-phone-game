package com.github.bogdanovmn.brokenphone.web.infrastructure;

import com.github.bogdanovmn.brokenphone.web.infrastructure.config.security.ProjectSecurityService;
import com.github.bogdanovmn.brokenphone.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class AbstractController {
	@Autowired
	private ProjectSecurityService securityService;

	public User getUser() {
		return securityService.getLoggedInUser();
	}

	@ModelAttribute("isAdmin")
	public boolean isAdmin() {
		User user = getUser();
		return user != null && user.getRoles().stream().anyMatch(x -> x.getName().equals("Admin"));
	}
}
