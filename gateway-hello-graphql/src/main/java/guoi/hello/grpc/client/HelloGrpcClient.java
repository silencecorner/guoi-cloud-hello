package guoi.hello.grpc.client;

import com.github.conanchen.guoi.cloud.hello.grpc.*;
import com.google.gson.Gson;
import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.health.v1.HealthCheckRequest;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.logging.Logger;

public class HelloGrpcClient {

    private final static Logger logger = Logger.getLogger(HelloGrpcClient.class.getSimpleName());


    public interface HellosCallback {
        void onHellosResponse(HellosResponse hellosResponse);
    }

    private static final Gson gson = new Gson();

    final HealthCheckRequest healthCheckRequest = HealthCheckRequest
            .newBuilder()
            .setService(HelloApiGrpc.getServiceDescriptor().getName())
            .build();

    private ManagedChannel getManagedChannel() {
        return NettyChannelBuilder
                .forAddress("127.0.0.1", 6565)
                .usePlaintext(true)
                //                .keepAliveTime(60, TimeUnit.SECONDS)
                .build();
    }

    public Hello createHello(String helloname) {
        ManagedChannel channel = getManagedChannel();

        ConnectivityState connectivityState = channel.getState(true);
        System.out.println(String.format("getHellos connectivityState = [%s]", gson.toJson(connectivityState)));

        HelloApiGrpc.HelloApiBlockingStub helloApiBlockingStub = HelloApiGrpc.newBlockingStub(channel);
        return helloApiBlockingStub.createHello(HelloRequest.newBuilder().setName(helloname).build());
    }

    public List<Hello> getHellos() {
        ManagedChannel channel = getManagedChannel();

        ConnectivityState connectivityState = channel.getState(true);
        System.out.println(String.format("getHellos connectivityState = [%s]", gson.toJson(connectivityState)));

        HelloApiGrpc.HelloApiBlockingStub helloApiBlockingStub = HelloApiGrpc.newBlockingStub(channel);
        return helloApiBlockingStub.listHellos(ListHellosRequest.newBuilder().build()).getHelloList();
    }

    public void getAsyncHellos(HellosCallback callback) {
        ManagedChannel channel = getManagedChannel();

        ConnectivityState connectivityState = channel.getState(true);
        System.out.println(String.format("getHellos connectivityState = [%s]", gson.toJson(connectivityState)));

        HelloApiGrpc.HelloApiStub helloApiStub = HelloApiGrpc.newStub(channel);
        helloApiStub.listHellos(ListHellosRequest.newBuilder().build(), new StreamObserver<HellosResponse>() {
            @Override
            public void onNext(HellosResponse value) {
                callback.onHellosResponse(value);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }
}
