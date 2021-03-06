package pl.training.news.adapters.broker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
final class Event {

    String name;
    LocalDateTime timestamp;

}
