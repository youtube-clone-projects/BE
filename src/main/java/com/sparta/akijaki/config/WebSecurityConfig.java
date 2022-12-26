package com.sparta.akijaki.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.akijaki.jwt.JwtAuthFilter;
import com.sparta.akijaki.jwt.JwtUtil;
import com.sparta.akijaki.security.CustomAccessDeniedHandler;
import com.sparta.akijaki.security.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig{

    private final JwtUtil jwtUtil;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final ObjectMapper om;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()

                .antMatchers("/", "/api/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/post/**").permitAll()
                .antMatchers(HttpMethod.POST,"/s3/file").permitAll()
                .antMatchers(HttpMethod.POST,"/api/login").permitAll()

                .antMatchers(HttpMethod.GET,"/swagger-resources/**").permitAll()
                .antMatchers(HttpMethod.GET,"/swagger-ui/**").permitAll()
                .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                .anyRequest().authenticated()
                .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        // 401 Error 처리, 인증과정에서 실패할 시 처리
        http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);

        // 403 Error 처리, 인증과는 별개로 추가적인 권한이 충족되지 않는 경우
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);

        //Cors 테스트를 위한 코드
//        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
//
//        http
//                .authorizeRequests()
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll(); // 추가
//
//        http
//                .exceptionHandling()
//                .authenticationEntryPoint(customAuthenticationEntryPoint())
//                .accessDeniedHandler(customAccessDeniedHandler())
//                .and()
//                .headers()
//                .frameOptions()
//                .disable()
//
//                // 이 설정을 해주지 않으면 밑의 corsConfigurationSource가 적용되지 않습니다!
//                .and()
//                .cors()
//
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//                .and();
//                .apply(new JwtConfig(jwtUtil, om));

        return http.build();
    }





    /**
     * 이 설정을 해주면, 우리가 설정한대로 CorsFilter가 Security의 filter에 추가되어
     * 예비 요청에 대한 처리를 해주게 됩니다.
     * cors 개념 참고 - https://inpa.tistory.com/entry/WEB-%F0%9F%93%9A-CORS-%F0%9F%92%AF-%EC%A0%95%EB%A6%AC-%ED%95%B4%EA%B2%B0-%EB%B0%A9%EB%B2%95-%F0%9F%91%8F
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){

        CorsConfiguration config = new CorsConfiguration();

        // 서버에서 응답하는 리소스에 접근 가능한 출처를 명시
        // Access-Control-Allow-Origin
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedOrigin("http://charleybucket.s3-website.ap-northeast-2.amazonaws.com"); //요거 변경하시면 됩니다.

        // 특정 헤더를 클라이언트 측에서 꺼내어 사용할 수 있게 지정
        // 만약 지정하지 않는다면, Authorization 헤더 내의 토큰 값을 사용할 수 없음
        // Access-Control-Expose-Headers
        config.addExposedHeader(JwtUtil.AUTHORIZATION_HEADER);

        // 본 요청에 허용할 HTTP method(예비 요청에 대한 응답 헤더에 추가됨)
        // Access-Control-Allow-Methods
        config.addAllowedMethod("*");

        // 본 요청에 허용할 HTTP header(예비 요청에 대한 응답 헤더에 추가됨)
        // Access-Control-Allow-Headers
        config.addAllowedHeader("*");

        // 기본적으로 브라우저에서 인증 관련 정보들을 요청 헤더에 담지 않음
        // 이 설정을 통해서 브라우저에서 인증 관련 정보들을 요청에 담을 수 있도록 해줍니다.
        // Access-Control-Allow-Credentials
        config.setAllowCredentials(true);

        // allowCredentials 를 true로 하였을 때,
        // allowedOrigin의 값이 * (즉, 모두 허용)이 설정될 수 없도록 검증합니다.
        config.validateAllowCredentials();

        config.setMaxAge(600L);

        // 어떤 경로에 이 설정을 적용할 지 명시합니다. (여기서는 전체 경로)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

//    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler(){
        return new CustomAccessDeniedHandler(om);
    }

//    @Bean
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint(){
        return new CustomAuthenticationEntryPoint(om);
    }
}