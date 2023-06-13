package com.adel.ecommerce.config;

import com.adel.ecommerce.security.AlwaysSaveRequestCache;
import com.adel.ecommerce.security.service.CustomPersistentTokenRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomPersistentTokenRepositoryImpl customPersistentTokenRepositoryImpl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .requestCache().requestCache(new AlwaysSaveRequestCache()).and()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/products/**", "/css/**", "/images/**", "/restImages/**", "/registration"/*,"/cart/**"*/).permitAll()
                        .requestMatchers("/cart/**", "/account").hasAnyRole("ADMIN", "USER") //TODO anonymous user
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()

                )
                .formLogin((form) -> form
                                .loginPage("/login")
                                .permitAll()
                        //.failureHandler(authenticationFailureHandler())
                )
                .logout((logout) -> logout.permitAll().deleteCookies("JSESSIONID"))
                .rememberMe().tokenRepository(customPersistentTokenRepositoryImpl).key("uniqueAndSecret").tokenValiditySeconds(30 * 24 * 60 * 60);
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}