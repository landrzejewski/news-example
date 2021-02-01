package pl.training.news.adapters.provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class ProviderConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
