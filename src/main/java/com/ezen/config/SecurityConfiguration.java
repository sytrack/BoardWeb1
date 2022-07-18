package com.ezen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

	@Autowired
	private SecurityUserDetailsService securityUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.authorizeRequests().antMatchers("/").permitAll();
        security.authorizeRequests().antMatchers("/system/**").permitAll();
        security.authorizeRequests().antMatchers("/board/**").authenticated();
        security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
        
        security.csrf().disable(); 
        security.formLogin().loginPage("/system/login").defaultSuccessUrl("/board/getBoardList", true);
        security.exceptionHandling().accessDeniedPage("/system/accessDenied");
        security.logout().invalidateHttpSession(true).logoutSuccessUrl("/").logoutUrl("/system/logout");

        security.userDetailsService(securityUserDetailsService);

        return security.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        
    	return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
