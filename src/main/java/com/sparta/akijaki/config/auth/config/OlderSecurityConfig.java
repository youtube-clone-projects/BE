//package com.boki.socialauth.config;
//
//import com.boki.socialauth.oauth2.handler.OAuth2AuthenticationFailureHandler;
//import com.boki.socialauth.oauth2.handler.OAuth2AuthenticationSuccessHandler;
//import com.boki.socialauth.oauth2.service.CustomOAuth2AuthService;
//import com.boki.socialauth.oauth2.service.CustomOidcUserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@RequiredArgsConstructor
//@EnableWebSecurity
//public class OlderSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final CustomOAuth2AuthService customOAuth2AuthService;
//
//    private final CustomOidcUserService customOidcUserService;
//
//    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
//
//    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
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
//    }
//}
