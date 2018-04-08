package guoi.cloud.hello;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class HelloMicroApplication extends AbstractReactiveMongoConfiguration
{

//    private final Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(HelloMicroApplication.class, args);
    }

    @Override
    @Bean
    public MongoClient reactiveMongoClient() {
//        int port = environment.getProperty("local.mongo.port", Integer.class);
        int port = 27017;
        return MongoClients.create(String.format("mongodb://localhost:%d", port));
    }

    @Override
    protected String getDatabaseName() {
        return "reactive";
    }
}
