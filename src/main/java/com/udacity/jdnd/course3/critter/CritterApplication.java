package com.udacity.jdnd.course3.critter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Launches the Spring application. Unmodified from starter code.
 *
 * https://docs.spring.io/spring-data/jpa/docs/2.4.2/reference/html/#appendix.query.method.subject
 * https://docs.spring.io/spring-data/jpa/docs/2.4.2/reference/html/#repositories.core-concepts
 * https://www.baeldung.com/spring-data-derived-queries
 *
 */
@SpringBootApplication
public class CritterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CritterApplication.class, args);
	}

}
