package mx.solser.bpi.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;


public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer { 

	public SecurityInitializer() {
		super(AppConfig.class,SecurityConfig.class,HttpSessionConfig.class); 
	}
}

