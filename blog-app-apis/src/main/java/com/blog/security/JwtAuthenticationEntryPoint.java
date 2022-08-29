package com.blog.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//to send an HTTP response that requests credentials from a client.
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * request - that resulted in an AuthenticationException
     * response - so that the user agent can begin authentication
     * authException - that caused the invocation
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied !!");
    }
}
