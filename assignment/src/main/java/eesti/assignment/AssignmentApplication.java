package eesti.assignment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import eesti.assignment.model.Itemdetails;
import eesti.assignment.repository.ItemsRepository;
import io.r2dbc.spi.ConnectionFactory;

@SpringBootApplication(scanBasePackages = { "eesti.*" })
@EnableR2dbcRepositories("eesti.assignment.repository")
@EnableAutoConfiguration
public class AssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	 @Bean
	    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

	        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
	        initializer.setConnectionFactory(connectionFactory);
	        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

	        return initializer;
	    }
	
	@Bean
	@Profile("!test")
	public CommandLineRunner demo(ItemsRepository repository) {
		return args -> {
			for (Itemdetails item : getItems()) {
				if (repository.findById(item.getId()).block() == null) {
					repository.save(item.setAsNew());
				}

			}
		};
	}

	public List<Itemdetails> getItems() {
		List<Itemdetails> list = new ArrayList<Itemdetails>();

		list.add(new Itemdetails(1, "Brownie", 0.65, "1", 45, false));
		list.add(new Itemdetails(2, "Muffin", 1.00, "1", 36, false));
		list.add(new Itemdetails(3, "Cake Pop", 1.35, "1", 24, false));
		list.add(new Itemdetails(4, "Apple tart", 1.50, "1", 60, false));
		list.add(new Itemdetails(5, "Water", 1.50, "1", 30, false));
		list.add(new Itemdetails(6, "Shirt", 2.00, "2", 6, false));
		list.add(new Itemdetails(7, "Pants", 3.00, "2", 4, false));
		list.add(new Itemdetails(8, "Jacket", 4.00, "2", 6, false));

		list.add(new Itemdetails(9, "Toy", 1.00, "2", 3, false));
		return list;
	}

}
