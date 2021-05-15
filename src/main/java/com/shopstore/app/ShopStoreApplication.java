package com.shopstore.app;

import com.shopstore.app.security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Profile("!dev")
@SpringBootApplication
@PropertySources({@PropertySource("classpath:application.properties"),
		@PropertySource("classpath:application-${spring.profiles.active}.properties")})
public class ShopStoreApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(ShopStoreApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

	@Bean(name = "AppProperties")
	public AppProperties getAppProperties() {
		return new AppProperties();
	}
}
