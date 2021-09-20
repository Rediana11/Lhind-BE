package com.lhind.project.annualleave.security;

import com.lhind.project.annualleave.security.model.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
        String username = userDetails.getUsername();
        response.sendRedirect(request.getContextPath());
        super.onLogoutSuccess(request, response, authentication);
    }
}
