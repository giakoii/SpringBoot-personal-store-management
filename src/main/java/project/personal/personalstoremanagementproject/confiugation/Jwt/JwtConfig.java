package project.personal.personalstoremanagementproject.confiugation.Jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import project.personal.personalstoremanagementproject.services.UserDetailService;
import project.personal.personalstoremanagementproject.utils.constants.ConstantEnum;

/**
 * JwtConfig
 */
@Configuration
@EnableWebSecurity
public class JwtConfig {

    @Autowired
    private  UserDetailService userDetailService;

    @Autowired
    private  JwtAuthFilter jwtFilter;

    private final String[] PUBLIC_ENDPOINTS = {
            "/api/v1/login",
            "/api/v1/register",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**"
    };

    /**
     * Configures the security filter chain for handling HTTP security in the application.
     *
     * @param httpSecurity an instance of {@link HttpSecurity} for configuring HTTP security policies.
     * @return a configured {@link SecurityFilterChain} object.
     * @throws Exception if there is an error during the configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeRequests(request -> request
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                        .requestMatchers("/api/v1/admin/**").hasRole(ConstantEnum.Role.ADMIN.name())
                        .requestMatchers("/api/v1/staff/**").hasRole(ConstantEnum.Role.STAFF.name())
                        .requestMatchers("/api/v1/user/**").hasAnyRole(ConstantEnum.Role.CUSTOMER.name(), ConstantEnum.Role.ADMIN.name())
                        .anyRequest().authenticated())
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        var daoAuthenticationProvider = new DaoAuthenticationProvider();
        // Set the {@link UserDetailService} to use for authentication.
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        // Set the {@link BCryptPasswordEncoder} to use for password encoding.
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(10));
        return daoAuthenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}