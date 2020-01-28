package micronaut.data.query.demo.repository

import io.micronaut.test.annotation.MicronautTest
import micronaut.data.query.demo.entity.SomeEntity
import spock.lang.Specification
import spock.lang.Unroll

import javax.inject.Inject

@MicronautTest
class SomeEntityRepositoryTest extends Specification {

    @Inject
    SomeEntityRepository repository

    def cleanup() {
        repository.deleteAll().blockingGet()
    }

    @Unroll
    void "should find by events"() {

        given:
        repository.save(SomeEntity
                .builder()
                .event("test")
                .internetNumber(1)
                .build())
                .blockingGet()

        repository.save(SomeEntity
                .builder()
                .event("lockout")
                .internetNumber(2)
                .build())
                .blockingGet()

        when:
        List<SomeEntity> result = repository.findByEventInList(eventList).toList().blockingGet()

        then:
        result.size() == expectedNumberOfResults

        where:
        eventList                  | expectedNumberOfResults
        List.of()                  | 0
        List.of("test")            | 1
        List.of("test", "lockout") | 2

    }

    @Unroll
    void "should finy by internetNumber"() {

        given:
        repository.save(SomeEntity
                .builder()
                .event("test")
                .internetNumber(1)
                .build())
                .blockingGet()

        repository.save(SomeEntity
                .builder()
                .event("lockout")
                .internetNumber(2)
                .build())
                .blockingGet()

        when:
        List<SomeEntity> result = repository.findByInternetNumberInList(internetNumbers).toList().blockingGet()

        then:
        result.size() == expectedNumberOfResults

        where:
        internetNumbers | expectedNumberOfResults
        List.of()       | 0
        List.of(1L)     | 1
        List.of(1L, 2L) | 2

    }

}
