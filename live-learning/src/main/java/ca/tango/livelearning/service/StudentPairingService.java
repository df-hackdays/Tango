package ca.tango.livelearning.service;

import ca.tango.livelearning.domain.StudentAnswer;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentPairingService {

    private static final Integer thresHold = new Integer(3);
    private AtomicInteger threshHoldCount = new AtomicInteger(0);

    public void checkThreshHOldAndPairStudent(StudentAnswer answer) {
    }


}
