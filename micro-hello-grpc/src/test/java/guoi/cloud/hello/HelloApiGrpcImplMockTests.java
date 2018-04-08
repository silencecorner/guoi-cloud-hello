package guoi.cloud.hello;

import com.github.conanchen.guoi.cloud.hello.grpc.Hello;
import com.github.conanchen.guoi.cloud.hello.grpc.HelloApiGrpc;
import com.github.conanchen.guoi.cloud.hello.grpc.HelloRequest;
import guoi.cloud.hello.grpc.HelloApiGrpcImpl;
import guoi.cloud.hello.mongo.HelloMongo;
import guoi.cloud.hello.mongo.HelloMongoRepository;
import io.grpc.testing.GrpcServerRule;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "grpc.port=0")
public class HelloApiGrpcImplMockTests {
    /**
     * This creates and starts an in-process server, and creates a client with an in-process channel.
     * When the test is done, it also shuts down the in-process client and server.
     */
    @Rule
    public final GrpcServerRule grpcServerRule = new GrpcServerRule().directExecutor();

    @MockBean
    HelloMongoRepository helloMongoRepository;

    @Autowired
    HelloApiGrpcImpl helloApiGrpcImpl;

    /**
     * To test the server, make calls with a real stub using the in-process channel, and verify
     * behaviors or state changes from the client side.
     */
    @Test
    public void test_createHello_then_return_hello() throws Exception {
        //Given
        when(helloMongoRepository.save(any(HelloMongo.class))).thenReturn(new Single<HelloMongo>() {
            @Override
            protected void subscribeActual(SingleObserver<? super HelloMongo> observer) {
                observer.onSuccess(HelloMongo
                        .builder()
                        .name("hellos/*")
                        .message("test name")
                        .createdAt(new Date())
                        .updateAt(new Date())
                        .build());
            }
        });
        // Add the service to the in-process server.
        grpcServerRule.getServiceRegistry().addService(helloApiGrpcImpl);

        HelloApiGrpc.HelloApiBlockingStub blockingStub =
                HelloApiGrpc.newBlockingStub(grpcServerRule.getChannel());
        String testName = "test name";

        //When
        Hello reply = blockingStub.createHello(HelloRequest.newBuilder().setName(testName).build());

        //Then
        assertThat(reply.getName(), startsWith("hellos/"));
        assertThat(reply.getMessage(), containsString(testName));
    }
}
