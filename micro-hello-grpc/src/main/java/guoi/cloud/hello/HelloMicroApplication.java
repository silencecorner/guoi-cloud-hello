package guoi.cloud.hello;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class HelloMicroApplication extends AbstractReactiveMongoConfiguration {

    @Value("spring.data.mongodb.host") private String host;
    @Value("spring.data.mongodb.port") private Integer port;
    public static void main(String[] args) {
        SpringApplication.run(HelloMicroApplication.class, args);
    }

    @Override
    @Bean
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(String.format("mongodb://%s:%d", host,port));
    }

    @Override
    protected String getDatabaseName() {
        return "reactive";
    }
}
