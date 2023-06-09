package nitish.MeetingAssistant.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MeetingDto {
    private LocalDateTime dateTime;
    private List<Integer> userId;

    public MeetingDto() {
    }
}
