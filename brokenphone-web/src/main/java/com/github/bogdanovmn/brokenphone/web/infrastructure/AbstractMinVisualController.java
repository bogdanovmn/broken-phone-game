package com.github.bogdanovmn.brokenphone.web.infrastructure;

import com.github.bogdanovmn.brokenphone.web.infrastructure.config.mustache.Layout;
import com.samskivert.mustache.Mustache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Value;


import java.util.Map;

public abstract class AbstractMinVisualController extends AbstractController {
	@Autowired
	private Mustache.Compiler compiler;
	@Value("${server.servlet.context-path:}")
	private String contextPath;

	@ModelAttribute("layout")
	public Mustache.Lambda layout(Map<String, Object> model) {
		return new Layout(compiler, "min", contextPath);
	}
}
