package io.pivotal.demo;

import io.pivotal.demo.domain.Customer;
import io.pivotal.demo.service.CustomerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class PcfEnvApplication {
	
	Logger logger = LoggerFactory
			.getLogger(PcfEnvApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PcfEnvApplication.class, args);
	}
	
	@Bean
	CommandLineRunner loadDatabase(CustomerRepository custRepo) {
		return args -> {
			logger.debug("Loading database...");
			custRepo.save(new Customer(1, "John", "Smith"));
			custRepo.save(new Customer(2, "Sam", "Snow"));
			custRepo.save(new Customer(3, "Bob", "Johnson"));
			logger.debug("record count: {}", custRepo.count());
			custRepo.findAll().forEach(x -> logger.debug(x.toString()));
			
		};
	}
}
