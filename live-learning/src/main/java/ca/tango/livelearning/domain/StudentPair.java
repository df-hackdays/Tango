package ca.tango.livelearning.domain;

import lombok.Data;

@Data
public class StudentPair {
    private String tutorId;
    private String tutoreeId;
    private String roomId;
}
