package project.personal.personalstoremanagementproject.confiugation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * SecurityConfig - Configuration for Security
 */
@Configuration
public class SecurityConfig {

    /**
     * Configures the CorsConfigurationSource object for CORS.
     *
     * @return an instance of {@link CorsConfigurationSource} object.
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        // Create a CorsConfiguration object
        CorsConfiguration configuration = new CorsConfiguration();

        // Set the allowed origins, methods, credentials, and headers
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

        // Create a UrlBasedCorsConfigurationSource object
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
