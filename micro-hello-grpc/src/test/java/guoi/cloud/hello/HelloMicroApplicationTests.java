package guoi.cloud.hello;

import com.mongodb.reactivestreams.client.MongoClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.events.MutationEvent;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloMicroApplicationTests {

	@Autowired
	MongoClient reactiveMongoClient;

	@Test
	public void contextLoads() {
		assertThat(reactiveMongoClient).isNotNull();
	}

}
