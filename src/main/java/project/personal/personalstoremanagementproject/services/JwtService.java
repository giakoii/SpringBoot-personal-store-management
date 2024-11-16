package project.personal.personalstoremanagementproject.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import project.personal.personalstoremanagementproject.entities.User;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    private SecretKey secretKey;
    private static final long EXPIRATION_TIME = 86400000;
    /**
     * Constructor
     */
    public JwtService(@Value("${jwt.secret}") String secretKeyString) {
        byte[] keyBytes = Base64.getDecoder().decode(secretKeyString.getBytes(StandardCharsets.UTF_8));
        this.secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    /**
     * Generate token
     * @param user
     * @return
     */
    public String generateToken(User user) {
        return Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("role", "ROLE_" + user.getRole().name())
                .signWith(secretKey)
                .compact();
    }

    /**
     * Generate refresh token
     * @param user
     * @return
     */
    public String generateRefreshToken(User user) {
        return Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 2))
                .signWith(secretKey)
                .compact();
    }

    /**
     * Validate token
     * @param token
     * @return
     */
    public  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Validate token
     * @param token
     * @param userDetails
     * @return
     */
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Validate token
     * @param token
     * @param user
     * @return
     */
    public boolean isTokenValid(String token, UserDetails user){
        final String username = extractUserName(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Check if token is expiredisTokenValid
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token) {
        return  extractClaim(token, Claims::getExpiration).before(new Date());
    }

    /**
     * Extract role
     * @param token
     * @return
     */
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

}
