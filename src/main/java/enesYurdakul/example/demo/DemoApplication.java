package enesYurdakul.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class DemoApplication {

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//		// Register resource handler for images
//		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/")
//				.setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
//	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


}
