package guoi.hello.graphql;

import com.github.conanchen.guoi.cloud.hello.grpc.Hello;
import com.google.protobuf.Timestamp;
import guoi.hello.graphql.types.hello.mutation.HelloCreateInput;
import guoi.hello.graphql.types.hello.mutation.HelloCreatePayload;
import guoi.hello.grpc.client.HelloGrpcClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "grpc.port=0")
public class MutationTests {


    @MockBean
    HelloGrpcClient helloGrpcClient;

    @Autowired
    Mutation mutation;

    /**
     * To test the server, make calls with a real stub using the in-process channel, and verify
     * behaviors or state changes from the client side.
     */
    @Test
    public void test_createHello_then_return_hello() throws Exception {
        //Given
        when(helloGrpcClient.createHello(anyString()))
                .thenReturn(Hello.newBuilder()
                        .setName("hellos/123")
                        .setMessage("hello " + anyString())
                        .setCreateTime(Timestamp.newBuilder().setSeconds((new Date()).getTime()).build())
                        .setUpdateTime(Timestamp.newBuilder().setSeconds((new Date()).getTime()).build())
                        .build());

        //When
        HelloCreatePayload helloCreatePayload = mutation.hello0Create(new HelloCreateInput("conan"));

        //Then
        assertThat(helloCreatePayload.getHello().getId(), startsWith("hellos/"));
    }
}
