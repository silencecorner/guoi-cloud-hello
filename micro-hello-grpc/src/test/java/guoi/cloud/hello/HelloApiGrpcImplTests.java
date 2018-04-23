package guoi.cloud.hello;

import com.github.conanchen.guoi.cloud.hello.grpc.CreateHelloRequest;
import com.github.conanchen.guoi.cloud.hello.grpc.Hello;
import com.github.conanchen.guoi.cloud.hello.grpc.HelloApiGrpc;
import guoi.cloud.hello.grpc.HelloApiGrpcImpl;
import guoi.cloud.hello.mongo.HelloMongoRepository;
import io.grpc.testing.GrpcServerRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "grpc.port=0")
public class HelloApiGrpcImplTests {
    /**
     * This creates and starts an in-process server, and creates a client with an in-process channel.
     * When the test is done, it also shuts down the in-process client and server.
     */
    @Rule
    public final GrpcServerRule grpcServerRule = new GrpcServerRule().directExecutor();

    @Autowired
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
        // Add the service to the in-process server.
        grpcServerRule.getServiceRegistry().addService(helloApiGrpcImpl);

        HelloApiGrpc.HelloApiBlockingStub blockingStub =
                HelloApiGrpc.newBlockingStub(grpcServerRule.getChannel());
        String firstName = "Conan";
        String lastName = "Chen";

        //When
        Hello reply = blockingStub.createHello(CreateHelloRequest.newBuilder().setFirstName(firstName).setLastName(lastName).build());

        //Then
        assertThat(reply.getName(), startsWith("hellos/"));
        assertThat(reply.getMessage(), containsString(firstName));
    }
}
