package com.halfacode.CoreBankAuthentication.config;
import com.halfacode.CoreBankAuthentication.logger.ApiLoggerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebMvcConfig {// WebMvcConfigurer {
  /*  @Autowired
    private ApiLoggerInterceptor apiLoggerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiLoggerInterceptor).order(Ordered.HIGHEST_PRECEDENCE);
        // Add other interceptors if needed and specify their order as well.
    }*/
}