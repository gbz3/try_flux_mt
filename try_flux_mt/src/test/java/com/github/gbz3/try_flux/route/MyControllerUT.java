package com.github.gbz3.try_flux.route;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

public class MyControllerUT {

	@Test
	public void testHandle() {
		// setup
		final RequestResource rr = new RequestResource();
		rr.setParams( Arrays.asList( "" ) );
		final MyController ctl = new MyController();
		
		// UT では DI されないので自前で値を設定する。
		ctl.props = new HelloRouterProps();
		ctl.props.setRoute( "" );
		
		// test
		assertThat( ctl.handle( "", rr ) ).isEqualTo( "[]" );
	}

}
