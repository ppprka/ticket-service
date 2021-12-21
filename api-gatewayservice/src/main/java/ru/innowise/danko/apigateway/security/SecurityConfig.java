package ru.innowise.danko.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.innowise.danko.apigateway.service.UserService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder encoder;
    private final UserService userService;
    private final ApplicationSettings applicationSettings;

    @Autowired
    public SecurityConfig(BCryptPasswordEncoder encoder,
                          UserService userService,
                          ApplicationSettings applicationSettings) {
        this.encoder = encoder;
        this.userService = userService;
        this.applicationSettings = applicationSettings;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and().httpBasic()
                .and().sessionManagement().disable();}

//    .authorizeRequests()
//                .antMatchers(applicationSettings.getWhiteList())
//                .permitAll()
//                .anyRequest().authenticated()
}
