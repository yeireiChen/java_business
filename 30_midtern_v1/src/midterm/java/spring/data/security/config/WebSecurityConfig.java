package midterm.java.spring.data.security.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{	
	@Autowired
	DataSource dataSource;
	
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
    	.usersByUsernameQuery(
			"select USERNAME, PASSWORD, 'true' as enabled "
			+ " from [USER]"
			+ " where USERNAME=?")
		.authoritiesByUsernameQuery(
			"select USERNAME, ROLE"
			+ " from [USER] "
			+ "where USERNAME=?")
		.rolePrefix("ROLE_");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
	    	.authorizeRequests()
	    		.antMatchers("/").permitAll()
	    		.antMatchers("/register").anonymous()
	    		.antMatchers("/login").permitAll()
	    		.antMatchers("/operation").hasAnyRole("customer", "manager", "director")
	    	    .antMatchers("/deposit").hasAnyRole("customer")
	        	.antMatchers("/withdraw").hasAnyRole("customer")
	        	.antMatchers("/statement").hasAnyRole("customer")
		        .antMatchers("/close").hasAnyRole("customer")
		        .antMatchers("/account").hasAnyRole("manager", "director")
		        .antMatchers("/transaction").hasAnyRole("manager", "director")
		        .antMatchers("/security/**").hasAnyRole("director")
		        .anyRequest().permitAll()
		        .and()
		        .formLogin()
		        .loginPage("/login").permitAll()
		        .defaultSuccessUrl("/operation")
		        .failureUrl("/loginError")
		        .and()
		   .exceptionHandling().accessDeniedPage("/accessError");
		        
		http.logout()
			.logoutUrl("/logout")
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/");
	}
}
