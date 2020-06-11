package com.birlasoft.user;

import java.util.ArrayList;
import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


import com.birlasoft.user.model.Role;
import com.birlasoft.user.model.User;
import com.birlasoft.user.service.UserService;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	 @Autowired
	  UserService userService;

	 

	  @Bean
	  public ModelMapper modelMapper() {
	    return new ModelMapper();
	  }

	  @Override
	  public void run(String... params) throws Exception {
	    User admin = new User();
	    admin.setUsername("admin");
	    admin.setPassword("admin");
	    admin.setEmail("admin@email.com");
	    admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ADMIN)));

	    userService.signup(admin);

	    User client = new User();
	    client.setUsername("client");
	    client.setPassword("client");
	    client.setEmail("client@email.com");
	    client.setRoles(new ArrayList<Role>(Arrays.asList(Role.CLIENT)));

	    userService.signup(client);
	  }
}
