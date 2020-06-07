package com.example.pre_3_1_2.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        for (GrantedAuthority authority: authentication.getAuthorities()) {
            if (authority.getAuthority().contains("ADMIN")) {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/admin");
                return;
            }
        }

        for (GrantedAuthority authority: authentication.getAuthorities()) {
            if (authority.getAuthority().contains("USER")) {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/user");
                return;
            }
        }

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/admin");

    }
}
