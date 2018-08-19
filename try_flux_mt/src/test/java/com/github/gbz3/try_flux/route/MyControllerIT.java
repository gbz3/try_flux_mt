package com.github.gbz3.try_flux.route;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyControllerIT {
	
	@Autowired
	private WebTestClient cli;
	
	@Test
	public void testHandle() throws Exception {
		// setup
		
		// do
		final RequestResource rr = new RequestResource();
		rr.setParams( Arrays.asList( "stat", "all" ) );
		final WebTestClient.ResponseSpec spec = cli.post().uri( "/ctl_mpe/dummy" )
			.contentType( MediaType.APPLICATION_JSON_UTF8 )
			.accept( MediaType.APPLICATION_JSON_UTF8 )
			.body( Mono.just( rr ), RequestResource.class )
			.exchange();

		// test
		spec.expectStatus().isOk();
		spec.expectHeader().contentTypeCompatibleWith( MediaType.APPLICATION_JSON_UTF8 );
//		final ResponseResource expect = new ResponseResource();
//		expect.setParams( Arrays.asList( "/ctl_mpe", "stat", "dummy", "all" ) );
//		spec.expectBody().json( new ObjectMapper().writeValueAsString( expect ) );
		final EntityExchangeResult<ResponseResource> res = spec.expectBody( ResponseResource.class ).returnResult();
		assertThat( res.getResponseBody().getParams() ).as( "リスト比較" ).isEqualTo( Arrays.asList( "/ctl_mpe", "dummy", "stat", "all" ) );
		assertThat( res.getResponseBody().getParams().get( 0 ) ).as( "前方一致" ).startsWith( "/" );
//		assertThat( new String( spec.expectBody().returnResult().getResponseBodyContent() ) ).isEqualTo( "[/ctl_mpe, dummy, stat, all]" );
	}

}
