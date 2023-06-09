package nitish.MeetingAssistant.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConflictUser {
    private String name;
    private String email;
    private Integer meetId;

    public ConflictUser() {
    }
}
