package my.com.tm.portal.leasing.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter  {
	
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/mock/","classpath:/data/"};
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {  
		
		registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
	}

	@Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }
	
}