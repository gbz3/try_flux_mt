package com.github.gbz3.try_flux.route;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class HelloRouter {
	private static final Logger logger = LoggerFactory.getLogger( HelloRouter.class );
	
	@Autowired
	HelloRouterProps props;
	
	public RouterFunction<ServerResponse> routes() {
		return RouterFunctions
				.nest( path( props.getRoute() ),
						route( POST( "/hello" ), this::hello )
						.andRoute( POST( "/{method}" ), this::handle )
				);
	}
	
	private Mono<ServerResponse> hello( ServerRequest req ) {
		return ok().body( Flux.just( "hello", "world" ), String.class );
	}
	
	private Mono<ServerResponse> handle( ServerRequest req ) {
		final Mono<List<String>> rr = req.body( BodyExtractors.toMono( RequestResource.class ) ).subscribeOn( Schedulers.elastic() ).map( s -> {
			logger.info( "method={} params={}", req.pathVariable( "method" ), s.getParams() );
			return s.getParams();
		}).log();
//		final Mono<RequestResource> rr = req.body( BodyExtractors.toMono( RequestResource.class ) );
//		rr.subscribeOn( Schedulers.elastic() ).map( s -> {
//			logger.info( "method={} params={}", req.pathVariable( "method" ), s.getParams() );
//			return s.getParams();
//		}).log().subscribe( s -> System.out.println( s ) );
		//rr.subscribe( s -> System.out.println( s.getParams() ) );
		
		logger.info( "method={}", req.pathVariable( "method" ) );
		return ok().body( Mono.just( "hoge" ), String.class );
	}

}
