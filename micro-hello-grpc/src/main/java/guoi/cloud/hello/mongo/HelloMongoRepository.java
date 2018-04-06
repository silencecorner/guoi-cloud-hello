package guoi.cloud.hello.mongo;

import org.springframework.data.repository.reactive.RxJava2CrudRepository;

public interface HelloMongoRepository extends RxJava2CrudRepository<HelloMongo, String> {

}