package com.movie;

import com.movie.common.servlet.ImgViewServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MoviemanageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviemanageApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean imgViewServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new ImgViewServlet());
		registration.addUrlMappings("/viewImage");
		return registration;
	}
}
