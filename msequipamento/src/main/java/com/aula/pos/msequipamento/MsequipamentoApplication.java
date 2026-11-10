package com.aula.pos.msequipamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsequipamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsequipamentoApplication.class, args);
	}

}
