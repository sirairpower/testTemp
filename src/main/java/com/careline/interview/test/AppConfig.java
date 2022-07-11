package com.careline.interview.test;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


/**
 * @author jack.wang
 */
@SpringBootApplication(scanBasePackages = "com.careline.interview.test")
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  protected UserDetailsService userDetailsService() {
    log.info("call userDetailsService ...");
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withUsername("howard").password("1234").authorities("USER").build());
    return manager;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    log.info("run configure .. ..");
    http.csrf().disable()
        .authorizeRequests()
        .mvcMatchers("/missions/mission5.html")
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/missions/mission5.html")
        .loginProcessingUrl("/mission5/login")
        .usernameParameter("email")
        .successHandler(new SimpleUrlAuthenticationSuccessHandler() {
          @Override
          public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
              Authentication authentication) throws IOException, ServletException {
            // run custom logics upon successful login
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            log.info("The user {} has logged in.", username);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print("{\"success\": true}");
            response.getWriter().flush();
          }
        })
        .permitAll();

  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("howard")
        .password("{noop}1234")
        .roles("USER");
  }
}
