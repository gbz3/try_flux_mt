package com.github.gbz3.try_flux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.github.gbz3.try_flux.route.HelloRouter;

@SpringBootApplication
public class TryFluxMtApplication {

	public static void main(String[] args) {
		SpringApplication.run(TryFluxMtApplication.class, args);
	}
	
	@Bean
	public RouterFunction<ServerResponse> routes( HelloRouter helloRouter ) {
		return helloRouter.route();
	}

}
