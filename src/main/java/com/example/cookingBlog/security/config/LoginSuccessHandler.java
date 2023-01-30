package com.example.cookingBlog.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.cookingBlog.security.details.AccountDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();
        String redirectURL;
        if (accountDetails.getRole().equals("USER")) {
            redirectURL = accountDetails.id().toString();
        } else redirectURL = "/accounts";
        response.sendRedirect(redirectURL);
    }
}