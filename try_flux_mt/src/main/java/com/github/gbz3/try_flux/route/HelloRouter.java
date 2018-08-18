package com.github.gbz3.try_flux.route;

import static org.springframework.web.reactive.function.server.RouterFunctions.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class HelloRouter {
	
	@Autowired
	HelloRouterProps props;
	
	public RouterFunction<ServerResponse> routes() {
		return RouterFunctions.nest( path( props.getRoute() ),
				route( POST( "/hello" ), this::hello )
				);
	}
	
	private Mono<ServerResponse> hello( ServerRequest req ) {
		return ok().body( Flux.just( "hello", "world" ), String.class );
	}

}
