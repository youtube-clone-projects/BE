//package com.sparta.akijaki.config;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final CustomOAuth2UserSerivce customOAuth2UserSerivce;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .headers().frameOptions().disable()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/","/css/**","/images/**",
//        "/js/**","/h2-console/**").permitAll()
//                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
//                .anyRequest().authenticated()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .userService(customOAuth2UserSerivce);
//    }
//}
