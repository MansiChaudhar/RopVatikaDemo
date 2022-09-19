package com.Ropvatika.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.Ropvatika.common.entity", "com.Ropvatika.admin.user"})
public class RopvatikaBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(RopvatikaBackEndApplication.class, args);
	}

}
