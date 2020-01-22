package org.springCloud.eureka.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class App
{
	
	@Value("${custom.name}")
	public String name;
	
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
        System.out.println( "Hello World!" );
    }

    @GetMapping("/service")
    public String service() {
    	   if(name == null) {
    		   name = "service";
    	   }
           return name;
    }
    
    @GetMapping("/service/service")
    public String service1() {
           return "service2serviec";
    }
}
