package uni.kassel.web17.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import uni.kassel.web17.spring.repo.UserRepo;
import uni.kassel.web17.spring.user.User;

//@EnableSwagger2 scans through all application classes and generates
//documentation based on request param/returns/annotations etc 

@EnableSwagger2
@SpringBootApplication
public class Main{
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);


	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		
	}
	
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();

	}

}
