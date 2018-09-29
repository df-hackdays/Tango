package ca.tango.livelearning.service;

import ca.tango.livelearning.domain.AnswerBreakpoint;
import ca.tango.livelearning.domain.Breakpoint;
import ca.tango.livelearning.domain.StudentAnswer;
import ca.tango.livelearning.repository.AnswerBreakpointRepository;
import ca.tango.livelearning.repository.BreakpointRepository;
import ca.tango.livelearning.repository.StudentAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LiveLearningDatabaseService {

    @Autowired
    private AnswerBreakpointRepository breakpointRepository;

    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    private static final AtomicInteger count = new AtomicInteger(0);

    public void insertBreakpoint(AnswerBreakpoint breakpoint) {
        breakpoint.setQuestionId(count.incrementAndGet());
        breakpointRepository.insert(breakpoint);
    }

    public void updateBreakpoint(AnswerBreakpoint breakpoint) {
        breakpointRepository.save(breakpoint);
    }

    public void deleteBreakpoint(AnswerBreakpoint breakpoint) {
        breakpointRepository.delete(breakpoint);
    }

    public void insertStudentAnswer(StudentAnswer studentAnswer) {
        studentAnswer.setId(studentAnswer.getStudentId() +"_"+ System.currentTimeMillis());
        studentAnswer.setIsCorrectAnswer(isCorrectAnswer(studentAnswer));
        studentAnswerRepository.insert(studentAnswer);
    }

    private Boolean isCorrectAnswer(StudentAnswer studentAnswer) {
       Optional<AnswerBreakpoint> answerBreakpoint = breakpointRepository.findById(studentAnswer.getQuestionId());
       if(answerBreakpoint.isPresent()) {
            if (answerBreakpoint.get().getAnswer().equals(studentAnswer.getAnswer()))
                return true;
            else
                return false;
        } else
            return false;
    }

    public void updateStudentAnswer(StudentAnswer studentAnswer) {
        studentAnswerRepository.save(studentAnswer);
    }

    public void deleteStudentAnswer(StudentAnswer studentAnswer) {
        studentAnswerRepository.delete(studentAnswer);
    }

    public StudentAnswer findStudentAnswerById(String id) {
        Optional studentAnswer;
        studentAnswer = studentAnswerRepository.findById(id);
        if(studentAnswer.isPresent())
            return (StudentAnswer) studentAnswer.get();
        return null;
    }

    public List<StudentAnswer> findAllStudentAnswer() {
        return studentAnswerRepository.findAll();
    }

}
