package pl.training.news.adapters.persistence;

import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PersistenceConfiguration {

    @Bean
    OrientDB database() {
        return new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
    }

}
