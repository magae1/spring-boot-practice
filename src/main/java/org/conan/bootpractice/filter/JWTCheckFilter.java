package org.conan.bootpractice.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.conan.bootpractice.util.JWTUtil;
import org.springframework.web.filter.OncePerRequestFilter;


@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        String path = request.getRequestURI();
        log.info("check url..{}", path);

        if (path.startsWith("/api/subscriber")) {
            return true;
        }
        return false;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("---JWTCheckFilter---");
        String authorization = request.getHeader("Authorization");
        try {
            String accessToken = authorization.substring(7);
            Map<String, Object> claims = JWTUtil.validateToken(accessToken);

            log.info("JWT Claims..{}", claims);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            Gson gson = new Gson();
            String message = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));
            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                out.println(message);
            }
        }
    }
}
