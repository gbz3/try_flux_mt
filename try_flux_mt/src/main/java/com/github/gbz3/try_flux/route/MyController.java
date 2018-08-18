package com.github.gbz3.try_flux.route;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping( path = "${route-props.route}" )
public class MyController {
	private static final Logger logger = LoggerFactory.getLogger( MyController.class );
	
	@RequestMapping( path = "/{method}", method = RequestMethod.POST )
	public Mono<String> handle( @PathVariable String method, @RequestBody RequestResource rr ) {
		logger.info( "method={} params={}", method, rr.getParams() );
		return Mono.just( logic( method, rr.getParams() ) );
	}
	
	private String logic( final String method, final List<String> params ) {
		return params.toString();
	}

}
