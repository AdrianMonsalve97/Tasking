package com.Amxx.Tasking.Security.Jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e)
            throws IOException, ServletException {

        try {
            logger.error("fail en el método commence " + e.getMessage());
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "no autorizado");
        } catch (Exception i) {
            logger.error("fail en el método doFilter  " +  i.getMessage());
        }
    }

}