package ca.tango.livelearning.service;

import ca.tango.livelearning.domain.Breakpoint;
import ca.tango.livelearning.domain.StudentAnswer;
import ca.tango.livelearning.repository.BreakpointRepository;
import ca.tango.livelearning.repository.StudentAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LiveLearningDatabaseService {

    @Autowired
    private BreakpointRepository breakpointRepository;

    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    private static final AtomicInteger count = new AtomicInteger(0);

    public void insertBreakpoint(Breakpoint breakpoint) {
        breakpoint.setId(System.currentTimeMillis());
        breakpoint.setQuestionId(count.incrementAndGet());
        breakpointRepository.insert(breakpoint);
    }

    public void updateBreakpoint(Breakpoint breakpoint) {
        breakpointRepository.save(breakpoint);
    }

    public void deleteBreakpoint(Breakpoint breakpoint) {
        breakpointRepository.delete(breakpoint);
    }

    public Breakpoint findBreakpointById(String id) {
        Optional breakpoint;
        breakpoint = breakpointRepository.findById(id);
        if(((Optional) breakpoint).isPresent())
            return (Breakpoint) breakpoint.get();
        return null;
    }

    public List<Breakpoint> findAllBreakpoint() {
        return breakpointRepository.findAll();
    }

    public void insertStudentAnswer(StudentAnswer studentAnswer) {
        studentAnswer.setId(studentAnswer.getStudentId() +"_"+ System.currentTimeMillis());
        studentAnswerRepository.insert(studentAnswer);
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
