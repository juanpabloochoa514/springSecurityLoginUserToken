package api_springsecurity.springsecurityjuanpabloochoa.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityFilterChainAplication {
	
	
	private static final String[] AUTH_WHITELIST = {
            "/sp/**",
            "/login",
            "/register"
            
    };

    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http) throws Exception {

              http.csrf(AbstractHttpConfigurer::disable).cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.disable())
              .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.xssProtection(xXssConfig -> xXssConfig.disable()))
//             .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/login"))
              .authorizeHttpRequests(
                      authorizationManagerRequestMatcherRegistry ->
                              authorizationManagerRequestMatcherRegistry
                                      .requestMatchers(AUTH_WHITELIST)
                                      .permitAll().anyRequest().authenticated());
              return http.build();

          }
}
