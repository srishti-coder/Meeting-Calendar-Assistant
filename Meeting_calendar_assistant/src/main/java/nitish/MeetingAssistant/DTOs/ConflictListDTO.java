package nitish.MeetingAssistant.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ConflictListDTO {
    private String name;
    private String email;
    private LocalDateTime dateTime;
    private List<List<ConflictUser>> userList;

    public ConflictListDTO() {
    }
}
