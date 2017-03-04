
package com.brinz.web.security;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.brinz.domain.BrinzUser;
import com.brinz.repositories.UserRepository;


@Component
public class BrinzUserDetailsService implements UserDetailsService {

  private static final Logger log = LoggerFactory.getLogger(BrinzUserDetailsService.class);

  @Autowired
  private UserRepository userRepository; // this is spring data repository (see spring data
                                         // configuration)

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    
    BrinzUser user = userRepository.findByUserName(userName);

    final Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    log.info("user role from DB: {} for user with name {}", user.getRole(), user.getUserName());
    if (user.getRole().equalsIgnoreCase("admin")) {
      grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    } else {
      grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    return new User(user.getUserName(), user.getPassword(), true, true, true, true,
        grantedAuthorities);
  }

}
