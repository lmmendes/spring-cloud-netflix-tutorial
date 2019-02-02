package io.mb.discoveryclient;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableEurekaClient
@SpringBootApplication
public class DiscoveryClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(DiscoveryClientApplication.class, args);
	}
}

@RestController
class ServerInstanceRestController {
	@Autowired
	private DiscoveryClient discoveryClient;

	@Value("${spring.application.name}")
	private String applicationName;

	@RequestMapping("/")
	public List<ServiceInstance> getServerInstances() {
		return discoveryClient.getInstances(this.getApplicationName());
	}

	private String getApplicationName(){
		return this.applicationName;
	}
}
