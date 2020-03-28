package ai.bitflow.ecm.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.AllArgsConstructor;

/**
 * @author mgmt_
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Override
    public void configure(WebSecurity web) throws Exception {
    	web.ignoring().antMatchers("/webjars/**", "/js/**", "/css/**");
    }

    /**
     * 
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.headers().frameOptions().disable()
        	.and()
		        .csrf().disable()
		        .httpBasic();
    }

}
