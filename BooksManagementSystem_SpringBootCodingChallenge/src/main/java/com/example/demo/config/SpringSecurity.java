package com.example.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.filter.JwtFilter;
import com.example.demo.service_Impl.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	 http
         .cors() // Enable CORS
         .and()
         .csrf(AbstractHttpConfigurer::disable)
         .authorizeHttpRequests(request -> request
             .requestMatchers(
                 "/auth/login",
                 "/auth/register",
                 "/books/getAll",
                 "/books/BookByIsbn/**"
             ).permitAll()
             .requestMatchers(
                 "/books/update/**",
                 "/books/delete/**",
                 "/books/add"
             ).authenticated()
             .anyRequest().authenticated()
         );

     http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
     return http.build();
 }

 @Bean
 public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
     return authConfig.getAuthenticationManager();
 }

 @Bean
 public PasswordEncoder passwordEncoder() {
     return new BCryptPasswordEncoder();
 }

 // Configure authentication with custom UserDetailsService
 @Autowired
 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
     auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
 }

 // ✅ CORS Configuration
 @Bean
 public CorsConfigurationSource corsConfigurationSource() {
     CorsConfiguration config = new CorsConfiguration();
     config.setAllowedOrigins(List.of("http://localhost:3000")); // React app origin
     config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
     config.setAllowedHeaders(List.of("*"));
     config.setAllowCredentials(true); // Allow cookies/headers like JWT

     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
     source.registerCorsConfiguration("/**", config);
     return source;
 }
	
	
}
