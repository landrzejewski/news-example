package pl.training.news.adapters.broker;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pl.training.news.domain.EventEmitter;
import pl.training.news.domain.NewsRequestEvent;

@Log
@Service
class KafkaEventEmitter implements EventEmitter<NewsRequestEvent> {

    private static final String EVENT_NAME = "NEWS_REQUEST";

    private final KafkaTemplate<String, Event> kafkaTemplate;
    private final String topic;

    public KafkaEventEmitter(KafkaTemplate<String, Event> kafkaTemplate, @Value("${kafka.topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void emit(NewsRequestEvent event) {
        log.info("Sending message");
        kafkaTemplate.send(topic, new Event(EVENT_NAME, event.getTimestamp()));
    }

}
