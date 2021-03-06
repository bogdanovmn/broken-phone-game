package com.github.bogdanovmn.brokenphone.web.user;

import com.github.bogdanovmn.brokenphone.web.infrastructure.AbstractVisualController;
import com.github.bogdanovmn.brokenphone.web.infrastructure.FormErrors;
import com.github.bogdanovmn.brokenphone.web.infrastructure.config.security.Md5PasswordEncoder;
import com.github.bogdanovmn.brokenphone.model.entity.User;
import com.github.bogdanovmn.brokenphone.model.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.github.bogdanovmn.common.spring.menu.MenuItem;
import com.github.bogdanovmn.brokenphone.web.infrastructure.MainMenuItem;



import javax.validation.Valid;

@Controller
@RequestMapping("/user/settings")
class UserSettingsController extends AbstractVisualController {
	private final UserRepository userRepository;

	@Autowired
	UserSettingsController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	protected MenuItem currentMenuItem() {
		return MainMenuItem.SETTINGS;
	}

	@InitBinder
	void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@GetMapping
	ModelAndView form(
		Model model,
		@RequestHeader(name = "referer", required = false) String referer
	) {
		User user = getUser();

		model.addAttribute("referer", referer);
		model.addAttribute("userEmail", user.getEmail());
		model.addAttribute("userRegistrationDate", user.getRegisterDate());
		model.addAttribute(
			"userSettingsForm",
			new UserSettingsForm()
		);
		return new ModelAndView("user_settings");
	}

	@PostMapping
	ModelAndView update(
		@Valid UserSettingsForm form,
		BindingResult bindingResult,
		Model model,
		@RequestHeader(name = "referer", required = false) String referer
	) {
		FormErrors formErrors = new FormErrors(bindingResult);

		User user = getUser();

		if (form.getNewPassword() != null) {
			String currentPassword = form.getCurrentPassword();
			if (currentPassword == null) {
				formErrors.add("currentPassword", "???????????????????? ??????????????");
			}
			else if (form.getNewPassword() == null) {
				formErrors.add("newPassword", "???????????????????? ??????????????");
			}
			else if (!form.getNewPassword().equals(form.getNewPasswordConfirm())) {
				formErrors.add("newPasswordConfirm", "???????????? ???????????? ???????????? ???? ??????????????????");
			}
			else {
				Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
				if (!passwordEncoder.encode(currentPassword).equals(user.getPasswordHash())) {
					formErrors.add("currentPassword", "???????????? ???????????? ??????????????????????");
				}
				else {
					user.setPasswordHash(
						passwordEncoder.encode(
							form.getNewPassword()
						)
					);
				}
			}
		}

		if (formErrors.isNotEmpty()) {
			model.addAllAttributes(formErrors.getModel());
			model.addAttribute("referer", referer);
			return new ModelAndView("user_settings");
		}

		userRepository.save(user);

		return new ModelAndView("redirect:/");
	}
}
