package com.project.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//marks a class as a source of bean definitions
//When the Spring container bootstrap process runs, it scans 
//these annotated classes and processes methods marked with the @Bean annotation
//to instantiate, configure, and manage objects in the Application Context
@Configuration
public class MapperConfig {
	// its third party used to convert from one object to another
	// here dto <--> entity
	// Register model mapper bean in container(spring handles it)
	// now we can use it in application anywhere
	// using any injection
	@Bean
	public ModelMapper modelMapper() {
		// we can write here explicit codes before the container gets this bean
		// so its a benenfit of @config then @compo
		return new ModelMapper();
	}

}
