package org.springCloud.eureka.client.service.impl;

import org.springCloud.eureka.client.service.TestService;
import org.springframework.stereotype.Component;

@Component
public class FeignFallBack implements TestService{
	
	@Override
	public String hello() {
		return "error hello";
	}
}
