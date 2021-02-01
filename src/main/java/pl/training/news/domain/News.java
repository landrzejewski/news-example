package pl.training.news.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class News {

    private String country;
    private String category;
    private List<Article> articles;

}

