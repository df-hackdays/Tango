package ca.tango.livelearning.service;

import ca.tango.livelearning.domain.StudentAnswer;
import ca.tango.livelearning.domain.StudentPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;



@Service
public class StudentPairingService {

    @Autowired
    LiveLearningDatabaseService liveLearningDatabaseService;

    private static final Integer thresHold = new Integer(3);
    private AtomicInteger threshHoldCount = new AtomicInteger(0);

    public List<StudentPair> studentPairs() {
        List<StudentPair> studentPairs = new ArrayList<StudentPair>();
        Map<String, Integer> studentCorrectAnswerCount = new HashMap<String, Integer>();
        Map<String, Integer> studentWrongAnswerCount = new HashMap<String, Integer>();
        Integer totalQuestions;

        totalQuestions = liveLearningDatabaseService.findAllBreakpoints().size();
        List<StudentAnswer> studentAnswerList = liveLearningDatabaseService.findAllStudentAnswer();
        studentAnswerList.forEach(studentAnswer -> {
            if(studentAnswer.getIsCorrectAnswer()) {
                if (studentCorrectAnswerCount.containsKey(studentAnswer.getStudentId())) {
                    studentCorrectAnswerCount.put(studentAnswer.getStudentId(), studentCorrectAnswerCount.get(studentAnswer.getStudentId()) + 1);
                } else {
                    studentCorrectAnswerCount.put(studentAnswer.getStudentId(), new Integer(1));
                }
            } else {
                if (studentWrongAnswerCount.containsKey(studentAnswer.getStudentId())) {
                    studentWrongAnswerCount.put(studentAnswer.getStudentId(), studentWrongAnswerCount.get(studentAnswer.getStudentId()) + 1);
                } else {
                    studentWrongAnswerCount.put(studentAnswer.getStudentId(), new Integer(1));
                }
            }
        });

        System.out.println("studentCorrectAnswerCount : "+ studentCorrectAnswerCount);
        System.out.println("studentWrongAnswerCount : "+ studentWrongAnswerCount);

        Object[] sortedCorrect =
                studentCorrectAnswerCount.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).toArray();

        Object[] sortedWrong =
                studentWrongAnswerCount.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).toArray();
        System.out.println("sortedCorrect : "+ sortedCorrect);
        System.out.println("sortedWrong : "+ sortedWrong);
        long correctCount = sortedCorrect.length;
        long wrongCount = sortedWrong.length;
        for (int i=0;  i < Math.min(correctCount, wrongCount); i++) {
            System.out.println("sortedCorrect[i] : "+ sortedCorrect[i]);
            System.out.println("sortedWrong[i] : "+ sortedWrong[i]);
            StudentPair studentPair = new StudentPair();
            String tutorId = String.valueOf(sortedCorrect[i]);
            studentPair.setRoomId(tutorId + System.currentTimeMillis());
            studentPair.setTutoreeId(String.valueOf(sortedWrong[i]));
            studentPair.setTutorId(tutorId);
            studentPairs.add(studentPair);
        }

        return studentPairs;
    }

}
