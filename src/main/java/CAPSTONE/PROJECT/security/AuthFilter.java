package CAPSTONE.PROJECT.security;


import CAPSTONE.PROJECT.entities.User;
import CAPSTONE.PROJECT.exceptions.UnauthorizedException;
import CAPSTONE.PROJECT.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Verifico se c'è l'header Authorization
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Inserire token in Authorization Header");
        } else {

            String token = authHeader.substring(7);

            jwtTools.verifyToken(token);


            String id = jwtTools.idFromToken(token);


            User foundUser = userService.findUserById(Integer.parseInt(id));


            Authentication authentication = new UsernamePasswordAuthenticationToken(foundUser, null, foundUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);


            filterChain.doFilter(request, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
