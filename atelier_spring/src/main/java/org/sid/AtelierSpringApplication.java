package org.sid;

import java.util.Date;

import org.sid.Dao.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AtelierSpringApplication implements  CommandLineRunner {

	@Autowired
	PatientRepository crud ;
	public static void main(String[] args) {
		SpringApplication.run(AtelierSpringApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
	
		
		crud.findAll().forEach(p->{
			System.out.println(p.getName());
		});
	}

}
