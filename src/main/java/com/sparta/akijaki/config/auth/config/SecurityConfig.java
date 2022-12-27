//package com.sparta.akijaki.config.auth.config;
//
//
//import com.sparta.akijaki.config.auth.oauth2.handler.OAuth2AuthenticationFailureHandler;
//import com.sparta.akijaki.config.auth.oauth2.handler.OAuth2AuthenticationSuccessHandler;
//import com.sparta.akijaki.config.auth.oauth2.service.CustomOAuth2AuthService;
//import com.sparta.akijaki.config.auth.oauth2.service.CustomOidcUserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@RequiredArgsConstructor
//@Configuration
//public class SecurityConfig {
//
//    private final CustomOAuth2AuthService customOAuth2AuthService;
//
//    private final CustomOidcUserService customOidcUserService;
//
//    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
//
//    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
//
////    @Bean
////    PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .httpBasic().disable()
//            .formLogin().disable()
//            .csrf().disable()
//            .cors()
//            .and()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .oauth2Login()
//            .userInfoEndpoint()
//            .oidcUserService(customOidcUserService)
//            .userService(customOAuth2AuthService)
//            .and()
//            .successHandler(oAuth2AuthenticationSuccessHandler)
//            .failureHandler(oAuth2AuthenticationFailureHandler);
//        return http.build();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**"));
//    }
//
//}
