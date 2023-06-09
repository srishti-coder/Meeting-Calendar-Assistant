package nitish.MeetingAssistant.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ConflictDateAndUser {
    private LocalDateTime dateTime;
    private String name;
}
