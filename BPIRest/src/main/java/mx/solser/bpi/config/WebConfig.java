package mx.solser.bpi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("*")
		.allowedHeaders(Constantes.HEADER_CONTET,
				Constantes.HEADER_RESQUESTWITH_KEY,
				Constantes.HEADER_TOKEN_AUT , 
				Constantes.TOKEN_HEADER,
				Constantes.HEADER_STATUS)
		.exposedHeaders(Constantes.HEADER_TOKEN_AUT,
				Constantes.HEADER_STATUS)
		.allowCredentials(false).maxAge(3600);
	}
	
//	@Bean
//	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
//	public UserSession userSession() {
//		return new UserSession();
//	}

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
	    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	

//    @Bean
//    public LocaleResolver localeResolver() {
//        return new CookieLocaleResolver();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//        localeChangeInterceptor.setParamName("lang");
//        registry.addInterceptor(localeChangeInterceptor);
//    }
}
