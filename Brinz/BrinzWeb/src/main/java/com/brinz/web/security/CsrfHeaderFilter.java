package com.brinz.web.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

@Component
public class CsrfHeaderFilter extends OncePerRequestFilter {

  private static final Logger log = LoggerFactory.getLogger(CsrfHeaderFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    log.debug("In filter for csrf  ");
    log.debug("ser context path: " + request.getContextPath());
    HttpSession session = request.getSession(false);
    if (session != null) {
      log.debug("session id in filter: " + session.getId());
      log.debug("sessiion is new: " + session.isNew());
      log.debug("session inactive interval time: " + session.getMaxInactiveInterval());
    } else {
      log.debug("session is : " + session);
    }

    CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    if (csrf != null) {
      Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
      String token = csrf.getToken();
      log.debug("csrf token: {}", token);
      if (cookie == null || token != null && !token.equals(cookie.getValue())) {
        cookie = new Cookie("XSRF-TOKEN", token);
        log.debug("cookie created");
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
        log.debug("cookie added to response");
      }
    }

    filterChain.doFilter(request, response);
  }

}
