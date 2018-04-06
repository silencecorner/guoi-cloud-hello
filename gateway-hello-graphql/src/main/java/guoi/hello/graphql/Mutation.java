package guoi.hello.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import guoi.hello.graphql.types.hello.Hello;
import guoi.hello.graphql.types.hello.mutation.HelloCreateInput;
import guoi.hello.graphql.types.hello.mutation.HelloCreatePayload;
import guoi.hello.grpc.client.HelloGrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableAutoConfiguration
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    HelloGrpcClient helloGrpcClient;



//    # 创建Hello
//    hello0Create(input: HelloCreateInput!): HelloCreatePayload!
    public HelloCreatePayload hello0Create(HelloCreateInput input){
       com.github.conanchen.guoi.cloud.hello.grpc.Hello grpcHello =  helloGrpcClient.createHello(input.getName());
       return HelloCreatePayload.builder().hello( Hello.builder()
               .id(grpcHello.getName())
               .message(grpcHello.getMessage())
               .createdAt(new Date(grpcHello.getCreateTime().getSeconds()))
               .updateAt(new Date(grpcHello.getUpdateTime().getSeconds()))
               .build()).build();

    }
}
