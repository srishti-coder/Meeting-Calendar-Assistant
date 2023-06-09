package nitish.MeetingAssistant.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class freeSlotDTO {
    private String slotNo;
    private LocalDateTime dateTime;

    public freeSlotDTO() {
    }
}
