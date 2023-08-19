package com.halfacode.CoreBankAuthentication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EntityScan(basePackages = "com.halfacode.CoreBankAuthentication.entity")
public class CoreBankAuthenticationApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CoreBankAuthenticationApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CoreBankAuthenticationApplication.class);
	}
}
