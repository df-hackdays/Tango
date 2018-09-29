package ca.tango.livelearning.repository;

import ca.tango.livelearning.domain.Breakpoint;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BreakpointRepository extends MongoRepository<Breakpoint, Long> {
}
