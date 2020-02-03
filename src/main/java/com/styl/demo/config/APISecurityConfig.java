package com.styl.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.styl.demo.util.ApiUtil;

@Configuration
@EnableWebSecurity
@Order(1)
public class APISecurityConfig extends WebSecurityConfigurerAdapter {
	

	@Autowired
	private ApiUtil apiUtil;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
	 	.csrf().disable().authorizeRequests()
	 	.antMatchers("/**").permitAll()
        .anyRequest().authenticated();
        
    }

    
}
