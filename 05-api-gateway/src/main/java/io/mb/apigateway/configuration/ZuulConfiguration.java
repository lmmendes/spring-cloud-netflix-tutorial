package io.mb.apigateway.configuration;

import io.mb.apigateway.filters.RequestCountryHeaderFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfiguration {
    @Bean
    public RequestCountryHeaderFilter requestCountryHeaderFilter(){
        return new RequestCountryHeaderFilter();
    }
}
