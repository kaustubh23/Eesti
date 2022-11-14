package eesti.assignment;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories("eesti.assignment.repository")
@AutoConfiguration
public class AssignmentApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class);
		
		 SpringApplication app = new SpringApplication(AssignmentApplication.class);
			
			app.setDefaultProperties(Collections
			          .singletonMap("server.port", "9090"));
			        app.run(args);

	}


}
