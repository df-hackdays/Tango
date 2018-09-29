package ca.tango.livelearning.repository;

import ca.tango.livelearning.domain.AnswerBreakpoint;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnswerBreakpointRepository extends MongoRepository<AnswerBreakpoint, Integer> {
}
