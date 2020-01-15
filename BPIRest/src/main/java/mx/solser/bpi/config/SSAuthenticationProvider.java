package mx.solser.bpi.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class SSAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			String username = authentication.getName();
			String password = authentication.getCredentials().toString();
			System.out.println("Name::::"+username);
			System.out.println("Pass:::::"+password);
//    		user=buss.login(user);
	        if(username.equals("test") && password.equals("prueba12"))
	        	return new UsernamePasswordAuthenticationToken(username, password, getAuthorities());
	        else
	        	throw new BadCredentialsException("External system authentication failed");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BadCredentialsException("External system authentication failed");
		}
	}


//	
//	
//	@Autowired
//	private LoginBuss buss;
//
//	
//    @Override
//    public Authentication authenticate(Authentication auth)   throws AuthenticationException {
//        String username = auth.getName();
//        String password = auth.getCredentials().toString();
// 
//        Usuario user=new Usuario();
//        user.setUsername(username);
//        user.setPassword(password);
//        
//    	try {
//    		user=buss.login(user);
//			 return new UsernamePasswordAuthenticationToken(username, password, getAuthorities(user.getPrivilegios()));
//		} catch (BusinessException e) {
//			e.printStackTrace();
//			throw new BadCredentialsException("External system authentication failed");
//		}
//    }
//    
    private final Collection<? extends GrantedAuthority> getAuthorities() {
    	List<GrantedAuthority> authorities = new ArrayList<>();
    	authorities.add(new SimpleGrantedAuthority("ROLE_"+"BPI"));
    	authorities.add(new SimpleGrantedAuthority("BPI"+"_"+"CONSULTA"));
//    	for (Privilegios role: roles) {
//    		authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getMenu().getFlujo().name()));
//    		for(PermisosEnum aux: role.getPermisos()) {
//    			System.out.println("Permisos usuario::"+role.getMenu().getFlujo().name()+"_"+aux.name());
//    			authorities.add(new SimpleGrantedAuthority(role.getMenu().getFlujo().name()+"_"+aux.name()));
//    		}
//    	}
    	
        return authorities;
    }
//    
//    
//
// 
    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
//
}
