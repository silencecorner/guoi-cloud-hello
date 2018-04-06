package guoi.cloud.hello.grpc;

import com.github.conanchen.guoi.cloud.hello.grpc.*;
import com.google.gson.Gson;
import com.google.protobuf.Timestamp;
import guoi.cloud.hello.mongo.HelloMongo;
import guoi.cloud.hello.mongo.HelloMongoRepository;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@GRpcService(interceptors = {LogInterceptor.class})
public class HelloApiGrpcImpl extends HelloApiGrpc.HelloApiImplBase {
    private static final Logger log = LoggerFactory.getLogger(HelloApiGrpcImpl.class);
    private static final Gson gson = new Gson();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private HelloMongoRepository helloMongoRepository;

    @Override
    public void createHello(HelloRequest request, StreamObserver<Hello> responseObserver) {
        helloMongoRepository.save(
                HelloMongo.builder()
                        .name(String.format("hellos/%s", UUID.randomUUID().toString()))
                        .message(String.format("Hello %s, Welcome to Guoi Micro$!", request.getName()))
                        .createdAt(new Date())
                        .updateAt(new Date())
                        .build()
        ).subscribe(helloMongo -> {
            responseObserver.onNext(Hello.newBuilder()
                    .setName(helloMongo.getName())
                    .setMessage(helloMongo.getMessage())
                    .setCreateTime(Timestamp.newBuilder().setSeconds(helloMongo.getCreate_time().getTime()).build())
                    .setUpdateTime(Timestamp.newBuilder().setSeconds(helloMongo.getUpdate_time().getTime()).build())
                    .build());
            responseObserver.onCompleted();
        });
    }

    @Override
    public void listHellos(ListHellosRequest request, StreamObserver<HellosResponse> responseObserver) {
        helloMongoRepository
                .findAll()
                .map(helloMongo -> Hello
                        .newBuilder()
                        .setName(helloMongo.getName())
                        .setMessage(helloMongo.getMessage())
                        .setCreateTime(Timestamp.newBuilder().setSeconds(helloMongo.getCreate_time().getTime()).build())
                        .setUpdateTime(Timestamp.newBuilder().setSeconds(helloMongo.getUpdate_time().getTime()).build())
                        .build())
                .toList(10)
                .subscribe(hellos -> {
                    responseObserver.onNext(HellosResponse.newBuilder()
                            .addAllHello(hellos)
                            .build());
                    responseObserver.onCompleted();
                });
    }
}