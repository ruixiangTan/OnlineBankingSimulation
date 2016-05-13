package concordia.inse6260.bankingsimulation.config;

/**
 * Created by ruixiangtan on 11/05/16.
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/public").setViewName("Home");
        registry.addViewController("/login").setViewName("Login");
        //registry.addViewController("/user").setViewName("Account");
        //registry.addViewController("/admin").setViewName("Admin");
    }

    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UserHandlerMethodArgumentResolver());
    }

}