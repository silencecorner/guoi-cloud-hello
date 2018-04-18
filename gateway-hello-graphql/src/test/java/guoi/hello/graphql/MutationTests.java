package guoi.hello.graphql;

import guoi.cloud.hello.HelloMicroApplication;
import guoi.cloud.hello.grpc.HelloApiGrpcImpl;
import guoi.hello.HelloGatewayApplication;
import guoi.hello.graphql.types.hello.mutation.HelloCreateInput;
import guoi.hello.graphql.types.hello.mutation.HelloCreatePayload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HelloGatewayApplication.class,
        HelloMicroApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "grpc.port=0")

public class MutationTests {

    private static final Logger log = LoggerFactory.getLogger(MutationTests.class);

    @Autowired
    Mutation mutation;


    /**
     * To test the server, make calls with a real stub using the in-process channel, and verify
     * behaviors or state changes from the client side.
     */
    @Test
    public void test_createHello_then_return_hello() throws Exception {
        //Given


        //When
        HelloCreatePayload helloCreatePayload = mutation.hello0Create(new HelloCreateInput("conan"));
        log.debug("helloCreatePayload={}", helloCreatePayload);

        //Then
        assertThat(helloCreatePayload.getHello().getId(), startsWith("hellos/"));
    }
}
