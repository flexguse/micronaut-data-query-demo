package micronaut.data.query.demo.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.RxJavaCrudRepository;
import io.reactivex.Flowable;
import micronaut.data.query.demo.entity.SomeEntity;

import java.util.List;

@Repository
public interface SomeEntityRepository extends RxJavaCrudRepository<SomeEntity, Long> {

    Flowable<SomeEntity> findByEventInList(List<String> eventList);

    Flowable<SomeEntity> findByInternetNumberInList(List<Long> internetNumbers);

}
