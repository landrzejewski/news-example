package pl.training.news.adapters.rest;

import lombok.Data;

import java.util.List;

@Data
final class NewsDto {

    String country;
    String category;
    List<ArticleDto> articles;

}
