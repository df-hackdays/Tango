package ca.tango.livelearning.repository;

import ca.tango.livelearning.domain.StudentAnswer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentAnswerRepository extends MongoRepository<StudentAnswer, String> {
}
