package az.project.usermanagementsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/api/auth").setViewName("auth");
        registry.addViewController("/signIn").setViewName("signIn");
        registry.addViewController("/signUp").setViewName("signUp");
    }
}