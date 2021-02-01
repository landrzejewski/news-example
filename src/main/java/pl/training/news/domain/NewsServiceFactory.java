package pl.training.news.domain;

public class NewsServiceFactory {

    public NewsService create(NewsProvider newsProvider) {
        return new NewsService(newsProvider, new CountriesService());
    }

}
