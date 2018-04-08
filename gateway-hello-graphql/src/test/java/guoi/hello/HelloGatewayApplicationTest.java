package guoi.hello;

import static org.assertj.core.api.Assertions.assertThat;

import guoi.hello.graphql.Mutation;
import guoi.hello.graphql.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloGatewayApplicationTest {

    @Autowired
    private Mutation mutation;

    @Autowired
    private Query query;

    @Test
    public void contexLoads() throws Exception {
        assertThat(mutation).isNotNull();
        assertThat(query).isNotNull();
    }
}