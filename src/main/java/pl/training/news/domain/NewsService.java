package pl.training.news.domain;

import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PACKAGE;

@RequiredArgsConstructor(access = PACKAGE)
public class NewsService {

    private final NewsProvider newsProvider;
    private final CountriesService countriesService;

    public News getNews(String country, String category) {
        countriesService.validate(country);
        return newsProvider.getNews(country, category)
                .orElseThrow(NewsLoadingException::new);
    }

}
