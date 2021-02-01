package pl.training.news.adapters.provider;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
final class ArticleDto {

    String author;
    String title;
    String description;
    Date publishedAt;
    String url;

}
