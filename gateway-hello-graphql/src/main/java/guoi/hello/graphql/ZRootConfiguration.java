package guoi.hello.graphql;

import com.github.conanchen.guoi.graphql.types.scalars.ZGuoiScalarsAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
        ZGuoiScalarsAutoConfiguration.class})
public class ZRootConfiguration {

    @Bean
    public Query query() {
        return new Query();
    }

    @Bean
    public Mutation mutation() {
        return new Mutation();
    }
}
