package guoi.hello.grpc.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZGrpcClientConfiguration {


    @Bean
    public HelloGrpcClient helloGrpcClient() {
        return new HelloGrpcClient();
    }

}
