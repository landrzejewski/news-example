package pl.training.news.domain;

public class NewsServiceFactory {

    public NewsService create(NewsProvider newsProvider, EventEmitter<NewsRequestEvent> eventEmitter) {
        return new NewsService(newsProvider, eventEmitter, new CountriesService());
    }

}
