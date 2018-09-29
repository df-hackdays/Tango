package ca.tango.livelearning.domain;

import lombok.Data;

@Data
public class Student extends Participant {
    private String roomId = this.id + System.currentTimeMillis();
}
