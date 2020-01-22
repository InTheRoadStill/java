package org.springCloud.eureka.client.service;

import org.springCloud.eureka.client.service.impl.FeignFallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "seri")
public interface TestService {
	@RequestMapping("/service")
    String hello();
}
