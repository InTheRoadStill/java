package org.springCloud.eureka.client;

import org.springCloud.eureka.client.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@RestController
@EnableFeignClients
public class App
{

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	TestService testService;
	
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
        System.out.println( "Hello World2!" );
    }
    
    @HystrixCommand(fallbackMethod = "serviceError")
    @GetMapping("/service")
    public String service() {
    		//return "22";
           return restTemplate.getForObject("http://192.168.3.220:801", String.class);
    }
    
    public String serviceError() {
    	System.out.println("熔断，默认回调函数");
    	return "use error!";
    }
    
    @GetMapping("/service/service")
    public String service1() {
           return testService.hello();
    }
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
       // Do any additional configuration here
       return builder.build();
    }
}
