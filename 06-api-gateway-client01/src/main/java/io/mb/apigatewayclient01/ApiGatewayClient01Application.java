package io.mb.apigatewayclient01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayClient01Application {
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayClient01Application.class, args);
	}
}

@RestController
class ApiGatewayClientRestController {

	public String getApplicationName() {
		return applicationName;
	}

	@Value("${spring.application.name}")
	private String applicationName;

	@RequestMapping("/")
	public String getIndex(@RequestHeader("x-country") String country){
		return String.format("Hello '%s' from %s", this.getApplicationName(), country);
	}
}
