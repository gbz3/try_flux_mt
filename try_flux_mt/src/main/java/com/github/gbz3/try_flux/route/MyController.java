package com.github.gbz3.try_flux.route;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( path = "${route-props.route}" )
public class MyController {
	private static final Logger logger = LoggerFactory.getLogger( MyController.class );
	
	@Autowired
	HelloRouterProps props;
	
	@RequestMapping( path = "/{method}", method = RequestMethod.POST )
	public ResponseResource handle( @PathVariable String method, @RequestBody RequestResource rr ) {
		logger.info( "method={} params={} props={}", method, rr.getParams(), props.getRoute() );
		return logic( method, rr.getParams() );
	}
	
	private ResponseResource logic( final String method, final List<String> params ) {
		final ResponseResource rr = new ResponseResource();
		rr.setParams( params );
		params.addAll( 0, Arrays.asList( props.getRoute(), method ) );
		return rr;
	}

}
