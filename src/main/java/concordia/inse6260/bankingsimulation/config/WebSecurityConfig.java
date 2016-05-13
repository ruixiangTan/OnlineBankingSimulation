package concordia.inse6260.bankingsimulation.config;

/**
 * Created by ruixiangtan on 11/05/16.
 */

import concordia.inse6260.bankingsimulation.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/public", "/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
                .antMatchers("/account").access("hasRole('USER')")
                .antMatchers("/user").access("hasRole('USER')")
                .antMatchers("/profile").access("hasRole('USER')")
                .antMatchers("/admin").access("hasRole('ADMIN')")
                .antMatchers("/create").access("hasRole('ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.csrf().disable();
    }

    @Override
    protected void configure(
            AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(new UserDetailsService() {
                    @Override
                    public UserDetails loadUserByUsername(String username)
                            throws UsernameNotFoundException {
                        UserDetails user = userRepository.findByUsername(username);
                        if (user != null) {
                            return user;
                        }
                        throw new UsernameNotFoundException("User '" + username + "' not found.");
                    }
                });
    }
}