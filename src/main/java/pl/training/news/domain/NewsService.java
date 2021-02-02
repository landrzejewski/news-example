package pl.training.news.domain;

import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PACKAGE;

@RequiredArgsConstructor(access = PACKAGE)
public class NewsService {

    private final NewsProvider newsProvider;
    private final ArticlesRepository articlesRepository;
    private final EventEmitter<NewsRequestEvent> eventEmitter;
    private final CountriesService countriesService;

    public News getNews(String country, String category) {
        countriesService.validate(country);
        eventEmitter.emit(new NewsRequestEvent());
        var news = newsProvider.getNews(country, category)
                .orElseThrow(NewsLoadingException::new);
        articlesRepository.saveAll(news.getArticles());
        return news;
    }

}
