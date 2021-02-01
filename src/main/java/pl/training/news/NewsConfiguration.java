package pl.training.news;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.news.domain.*;

@Configuration
class NewsConfiguration {

    @Bean
    public NewsService newsService(NewsProvider newsProvider, EventEmitter<NewsRequestEvent> eventEmitter) {
        return new NewsServiceFactory().create(newsProvider, eventEmitter);
    }

}
