package io.mb.apigatewayclient02;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayClient02Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayClient02Application.class, args);
	}

}

@RestController
class ApiGatewayClientRestController {

	@Value("${spring.application.name}")
	private String applicationName;

	public String getApplicationName() {
		return applicationName;
	}

	@RequestMapping("/")
	public String getIndex(){
		return this.getApplicationName();
	}

}

