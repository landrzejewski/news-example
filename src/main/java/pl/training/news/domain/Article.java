package pl.training.news.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Article {

    private String author;
    private String title;
    private String description;
    private LocalDate publicationDate;
    private String url;
    private String source;

}
