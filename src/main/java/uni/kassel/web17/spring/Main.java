package uni.kassel.web17.spring;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;


import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableSwagger2 scans through all application classes and generates
//documentation based on request param/returns/annotations etc 

@EnableSwagger2
@SpringBootApplication
public class Main {
	
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		
	}
	
//	@Bean
//	public Docket api(){
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//			
//	}

}
