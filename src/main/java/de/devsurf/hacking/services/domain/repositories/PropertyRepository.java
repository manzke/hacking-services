package de.devsurf.hacking.services.domain.repositories;

import de.devsurf.hacking.services.domain.entities.Property;
import org.reactivestreams.Publisher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PropertyRepository extends ReactiveCrudRepository<Property, String> {

}
