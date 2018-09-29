package com.domain.myapplication;

import okhttp3.OkHttpClient;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class RestServiceFactory {
	public static RestTemplate makeService() {
		final OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.connectTimeout( 30, TimeUnit.SECONDS )
				.readTimeout( 30, TimeUnit.SECONDS )
				.build();
		return makeTemplate( okHttpClient );
	}

	private static RestTemplate makeTemplate( OkHttpClient okHttpClient ) {
		RestTemplate restTemplate = new RestTemplate( new BufferingClientHttpRequestFactory( new OkHttp3ClientHttpRequestFactory( okHttpClient ) ) );
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add( new StringHttpMessageConverter() );
		restTemplate.setMessageConverters( messageConverters );
		return restTemplate;
	}
}