package com.firsttry.firsttryout.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

public class OwnerTokenFilter extends OncePerRequestFilter {

    public static final String OWNER_ATTR = "OWNER_ID";
    private static final String COOKIE_NAME = "owner_token";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        UUID ownerId = null;
        String token = null;

        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if (COOKIE_NAME.equals(c.getName())) {
                    token = c.getValue();
                    break;
                }
            }
        }

        try {
            if (token != null) {
                ownerId = OwnerTokenUtil.verifyAndExtract(token);
            } else {
                ownerId = UUID.randomUUID();
                token = OwnerTokenUtil.generateToken(ownerId);

                Cookie cookie = new Cookie(COOKIE_NAME, token);
                cookie.setHttpOnly(true);
                cookie.setSecure(true); // HTTPS only
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * 24 * 365); // 1 year
                response.addCookie(cookie);
            }

            request.setAttribute(OWNER_ATTR, ownerId);
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid owner token");
        }
    }
}