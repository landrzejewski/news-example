package pl.training.news.adapters.broker;

import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log
class EventsConsumer {

    @KafkaListener(topics = "news", groupId = "g1", containerFactory = "containerFactory")
    void onEvent(Event event) {
        log.info("New event: " + event);
        // call domain service
    }

}
