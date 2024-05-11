package az.edu.ada.wm2.Assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for Spring MVC.
 */
@Configuration
@EnableWebMvc
public class WebMVCConfig implements WebMvcConfigurer {

    /**
     *  It allows us to register view controllers to handle requests for different URLs without the need for a controller class.
     *  In this example, the root URL ("/") is mapped to the "index" view.
     *
     * @param registry ViewControllerRegistry instance to register view controllers
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    /**
     * Creates a validator bean for validating model attributes in the application.
     *
     * @return LocalValidatorFactoryBean instance
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
