package mx.solser.bpi.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import mx.solser.bpi.util.UrlPermisos;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 

	
	@Autowired
    private SSAuthenticationProvider authProvider;
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Bean
    public PasswordEncoder delegatingPasswordEncoder() {
        
        String encodingId = "bcrypt";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put(encodingId, new BCryptPasswordEncoder());
		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
		encoders.put("scrypt", new SCryptPasswordEncoder());
		encoders.put("argon2", new Argon2PasswordEncoder());

		return new DelegatingPasswordEncoder(encodingId, encoders);
        
    }


	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf().disable()
//		.sessionManagement()
//      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//      .and()
        .authorizeRequests()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		.antMatchers("/index.html").permitAll()
		.antMatchers("/swagger-ui.html").permitAll()
		.antMatchers("/*").permitAll()
		.antMatchers("/assets/**").permitAll()
		.antMatchers("/v2/**").permitAll()
		.antMatchers("/security/login").permitAll()
        .antMatchers("/controller/**").authenticated()
        .and()
        .httpBasic()
		;
//		
		
		for(UrlPermisos permisos:UrlPermisos.values()) {
			System.out.println("Permisos:::::"+permisos.getAutorizacion()+"::"+permisos.getUrl());
			httpSecurity.authorizeRequests().antMatchers(permisos.getUrl()).hasAuthority(permisos.getAutorizacion())    ;
		}
		
        
	}


	
}