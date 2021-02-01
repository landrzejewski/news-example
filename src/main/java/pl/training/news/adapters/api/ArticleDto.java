package pl.training.news.adapters.api;

import lombok.Data;

import java.time.LocalDate;

@Data
final class ArticleDto {

    String author;
    String title;
    String description;
    LocalDate date;
    String url;

}
