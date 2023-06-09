package nitish.MeetingAssistant.Controller;

import nitish.MeetingAssistant.DTOs.*;
import nitish.MeetingAssistant.Model.User;
import nitish.MeetingAssistant.Service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meet")
public class MeetingController {
    @Autowired
    MeetingService meetingService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody List<userDTO> users){

        return meetingService.addUser(users);
    }

    @PostMapping("/addMeeting")
    public String addMeet(@RequestBody MeetingDto meetingDto){

        return meetingService.addMeeting(meetingDto);
    }

    @GetMapping("/freeSlots")
    public List<freeSlotDTO> getFreeSlots(@RequestBody userIdDTO userId){
        return meetingService.freeSlot(userId);
    }

    @GetMapping("/scheduleConflicts")
    public List<ConflictListDTO> getFreeSlots(){
        return meetingService.scheduleConflict();
    }

    @PutMapping("/cancelMeeting")
    public  void cancelMeeting(@RequestParam("q") Integer meetingId){

    }
}
