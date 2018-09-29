package ca.tango.livelearning.service;

import ca.tango.livelearning.domain.Student;
import ca.tango.livelearning.domain.StudentAnswer;
import ca.tango.livelearning.domain.StudentPair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentPairingService {

    private List<Student> blacklist = new ArrayList<>();

    private static final Integer thresHold = new Integer(3);
    private AtomicInteger threshHoldCount = new AtomicInteger(0);

    public void checkThreshHOldAndPairStudent(StudentAnswer answer) {
    }


    public List<StudentPair> studentPairs() {
        return null;
    }


}
