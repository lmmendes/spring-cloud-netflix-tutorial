package io.mb.configclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
public class ConfigClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}
}

@RestController
class ServerInstanceRestController {
	@Autowired
	private DiscoveryClient discoveryClient;

	@Value("${service.external.host}")
	private String hostForExternalService;

	@RequestMapping("/")
	public String getIndex(){
		return this.getHostForExternalService();
	}

	private String getHostForExternalService(){
		return this.hostForExternalService;
	}
}

