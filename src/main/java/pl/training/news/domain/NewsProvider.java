package pl.training.news.domain;

import java.util.Optional;

public interface NewsProvider {

    Optional<News> getNews(String country, String category);

}
