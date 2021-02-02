package pl.training.news.domain;

import java.util.List;

public interface ArticlesRepository {

    void saveAll(List<Article> articles);

}
