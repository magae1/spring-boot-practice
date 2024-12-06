package org.conan.bootpractice.util;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;

import org.conan.bootpractice.exception.CustomJWTException;


public class JWTUtil {
    private static final String SECRET = "GQKoddWPxzluf4jQIAsdaklkjvdvdsdasdqwc1324SWRpfbB693CtjmrWInk0NgFP4FGKgjFcpXal7eane9jW7YQmQtxmskPs13IRMA";

    public static String generateToken(Map<String, Object> valueMap, int min) {
        SecretKey key;
        try {
            key = Keys.hmacShaKeyFor(JWTUtil.SECRET.getBytes(StandardCharsets.UTF_8));
        } catch (WeakKeyException e) {
            throw new RuntimeException(e.getMessage());
        }

        return Jwts.builder()
                .setHeader(Map.of("typ", "JWT"))
                .setClaims(valueMap)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                .signWith(key)
                .compact();
    }

    public static Map<String, Object> validateToken(String token) {
        Map<String, Object> claim = null;
        SecretKey key;
        try {
            key = Keys.hmacShaKeyFor(JWTUtil.SECRET.getBytes(StandardCharsets.UTF_8));
            claim = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (WeakKeyException e) {
            throw new CustomJWTException("WeakKeyException");
        }
        return claim;
    }
}
