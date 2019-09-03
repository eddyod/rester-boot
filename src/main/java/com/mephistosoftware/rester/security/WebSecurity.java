package com.mephistosoftware.rester.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.mephistosoftware.rester.security.SecurityConstants.SIGN_UP_URL;
import static com.mephistosoftware.rester.security.SecurityConstants.SOCIAL_REGISTER;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private PersonDetailsServiceImpl userDetailsService;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public WebSecurity(PersonDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors()
		.and()
		.formLogin().loginPage("/login").usernameParameter("email")
        .successHandler(successHandler())
        .failureHandler(failureHandler())
		.and().csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
		.antMatchers(HttpMethod.POST, SOCIAL_REGISTER).permitAll()
		.antMatchers(HttpMethod.GET, "/health").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(new JWTAuthenticationFilter(authenticationManager()))
		.addFilter(new JWTAuthorizationFilter(authenticationManager()));		
	}
	/*
	CustomCorsFilter customCorsFilter() {
        CustomCorsFilter filter = new CustomCorsFilter();
        return filter;
    }
    */
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	  @Bean 
	  CorsConfigurationSource corsConfigurationSource() { 
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); 
		source.registerCorsConfiguration("/**", configuration); 
		return source; 
	}
	
	 private AuthenticationFailureHandler failureHandler() {
         return new SimpleUrlAuthenticationFailureHandler() {
             public void onAuthenticationFailure(HttpServletRequest request,
                                                 HttpServletResponse response, AuthenticationException exception)
                     throws IOException, ServletException {
                 response.setContentType("text/html;charset=UTF-8");
                 response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed. Wrong email or password or both");
             }
         };
     }


     private AuthenticationSuccessHandler successHandler() {
         return new SimpleUrlAuthenticationSuccessHandler() {
             public void onAuthenticationSuccess(HttpServletRequest request,
                     HttpServletResponse response, Authentication authentication)
                     throws IOException, ServletException {
             }
         };
     }

}