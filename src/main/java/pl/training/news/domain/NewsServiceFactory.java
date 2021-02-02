package pl.training.news.domain;

public class NewsServiceFactory {

    public NewsService create(NewsProvider newsProvider, ArticlesRepository articlesRepository, EventEmitter<NewsRequestEvent> eventEmitter) {
        return new NewsService(newsProvider, articlesRepository, eventEmitter, new CountriesService());
    }

}
