package guoi.hello;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Random;


/**
 * @author hai
 * description
 * email hilin2333@gmail.com
 * date 2018/7/20 2:54 PM
 */
@Component
@Log4j2
public class LogRunner implements CommandLineRunner {

    private static Random random = new Random();
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
            while (true) {
                log.log(randomLevel(), randomMessage());
                Thread.sleep(3000);

            }
    }

    private Level randomLevel() {
        switch (random.nextInt(3)) {
            case 0:
                return Level.DEBUG;
            case 1:
                return Level.INFO;
            case 2:
                return Level.ERROR;
            default:
                return Level.INFO;
        }
    }

    private String randomMessage() {
      Map  map = restTemplate.getForObject("http://api.icndb.com/jokes/random", Map.class,new Object());
       Map map1 = (Map)map.get("value");
       return map1.get("joke").toString();
    }
}