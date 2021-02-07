package com.psingh69.database.databasedemo;

import com.psingh69.database.databasedemo.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class DatabaseDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersonJdbcDao dao;

	public static void main(String[] args) {
		SpringApplication.run(DatabaseDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 logger.info("All users --> {} ", dao.findAll());
		 logger.info("Find by Id --> {}", dao.findbyId(10001));
		 logger.info("Delete by Id : 10000--> {}", dao.deletebyId(10000));
		 logger.info("insert : 10004 --> {}", dao.addPerson(new Person(10004, "MARK", "ENGLAND",  new Date())));
		 logger.info("update : 10003 --> {}", dao.updatePerson(new Person(10003, "STOKES", "ENGLAND",  new Date())));


	}
}
