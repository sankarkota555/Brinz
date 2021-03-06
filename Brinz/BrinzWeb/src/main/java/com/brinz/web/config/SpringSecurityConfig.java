package com.brinz.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/brz/admin").hasRole("ADMIN").antMatchers("/brz/**")
        .hasAnyRole("ADMIN", "USER")// configuring url for security
        .and().formLogin();// for login page

    // allow basic authentication
    http.httpBasic();

    // enable session management
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .enableSessionUrlRewriting(false).maximumSessions(1).maxSessionsPreventsLogin(false)
        .expiredUrl("/sessionExpired");

    // configure logout
    http.logout().clearAuthentication(true).deleteCookies("XSRF-TOKEN", "JSESSIONID")
        .invalidateHttpSession(true).logoutSuccessUrl("/login");

  }

  /*
   * @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception {
   * auth.inMemoryAuthentication().withUser("user").password("user").roles("USER"); }
   */

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    auth.userDetailsService(userDetailsService); // for authenticating using username and password
                                                 // from database.
  }

  /*
   * @Bean public HttpSessionEventPublisher httpSessionEventPublisher() { return new
   * HttpSessionEventPublisher(); }
   */
}
