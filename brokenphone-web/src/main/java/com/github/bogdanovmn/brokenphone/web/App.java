package com.github.bogdanovmn.brokenphone.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.ComponentScan;
import com.github.bogdanovmn.common.spring.menu.MenuConfiguration;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.github.bogdanovmn.brokenphone.model")
@EntityScan(basePackages = "com.github.bogdanovmn.brokenphone.model")
@ComponentScan(basePackages = {
	"com.github.bogdanovmn.brokenphone.web",
	"com.github.bogdanovmn.brokenphone.model.common"
})
@EnableConfigurationProperties({
	MenuConfiguration.class
})
@EnableTransactionManagement
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

