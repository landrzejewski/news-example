package pl.training.news.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewsRequestEvent {

    private LocalDateTime timestamp;

}
