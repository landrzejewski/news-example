package pl.training.news.adapters.provider;

import lombok.Data;

import java.util.List;

@Data
final class NewsDto {

    String status;
    int totalResults;
    List<ArticleDto> articles;

}

