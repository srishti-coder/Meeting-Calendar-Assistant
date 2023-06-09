package nitish.MeetingAssistant.Service;

import nitish.MeetingAssistant.DTOs.*;
import nitish.MeetingAssistant.Enums.MeetingStatus;
import nitish.MeetingAssistant.Model.Meeting;
import nitish.MeetingAssistant.Model.User;
import nitish.MeetingAssistant.Repository.MeetingRepository;
import nitish.MeetingAssistant.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Service
public class MeetingService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MeetingRepository meetingRepository;

    public String addUser(List<userDTO> users){
        for(userDTO userN:users){
            User user=new User();
            user.setEmail(userN.getEmail());
            user.setName(userN.getName());
            userRepository.save(user);
        }
        return "Users Added Successfully";
    }

    public String addMeeting(MeetingDto meetingDto){
        List<User> users=new ArrayList<>();

        for(Integer userId:meetingDto.getUserId()){
            User user=userRepository.findById(userId).get();
            users.add(user);
        }
        //Create meeting List
        Meeting meeting=new Meeting();
        meeting.setStatus(MeetingStatus.Pending);
        meeting.setDateTime(meetingDto.getDateTime());
        meeting.setUsers(users);

        for(User user:users){
            user.getMeetingList().add(meeting);
        }
        meetingRepository.save(meeting);


        return "Meeting Confirmed at "+meetingDto.getDateTime()+". \n"+"Meeting ID: "+meeting.getMeetingId();
    }
    public List<ConflictListDTO> scheduleConflict(){
        List<User> users=userRepository.findAll();
        List<ConflictListDTO> conflictUserList=new ArrayList<>();
        for(User user:users){
            HashMap<LocalDateTime,List<Meeting>> map=new HashMap<>();
            for(Meeting meet:user.getMeetingList()){
                if(map.containsKey(meet.getDateTime())){
                    map.get(meet.getDateTime()).add(meet);
                }
                else{
                    List<Meeting> meetingList=new ArrayList<>();
                    meetingList.add(meet);
                    map.put(meet.getDateTime(),meetingList);
                }
            }
            for(LocalDateTime dateTime: map.keySet()){
                if(map.get(dateTime).size()>1){
                    ConflictListDTO conflictListDTO=new ConflictListDTO();
                    conflictListDTO.setName(user.getName());
                    conflictListDTO.setEmail(user.getEmail());
                    conflictListDTO.setDateTime(dateTime);
                    List<List<ConflictUser>> userlist=new ArrayList<>();
                    conflictListDTO.setUserList(userlist);
                    for(Meeting meeting:map.get(dateTime)){
                        List<ConflictUser> list=new ArrayList<>();
                        for(User user1:meeting.getUsers()){
                            ConflictUser conflictUser=new ConflictUser();
                            conflictUser.setEmail(user1.getEmail());
                            conflictUser.setName(user1.getName());
                            conflictUser.setMeetId(meeting.getMeetingId());
                            list.add(conflictUser);
                        }
                        conflictListDTO.getUserList().add(list);
                    }
                    //ConflictUser conflictUser=new ConflictUser();
                    conflictUserList.add(conflictListDTO);

                }
            }
        }
        return conflictUserList;
    }
    public void Attended(List<Meeting> meets){
        for(Meeting meet:meets){
            if(meet.getDateTime().compareTo(LocalDateTime.now())<0){
                meet.setStatus(MeetingStatus.Attended);
                meetingRepository.save(meet);
            }
        }
    }

    public List<freeSlotDTO> freeSlot(userIdDTO users){
        List<User> userList=new ArrayList<>();
        for(Integer userId:users.getUserId()){
            User user=userRepository.findById(userId).get();
            userList.add(user);
        }
        HashMap<String, Integer> dateTimeIntegerMap=new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for(User user:userList){
            for(Meeting meet:user.getMeetingList()){
                if(meet.getStatus().equals(MeetingStatus.Pending)){
                    LocalDateTime temp=meet.getDateTime();
                    String formatDateTime = temp.format(formatter);
                    //temp=LocalDateTime.parse(formatDateTime);
                    dateTimeIntegerMap.put(formatDateTime,1);
                }
            }
        }
        LocalDateTime t=LocalDateTime.now();
        //t=LocalDateTime.parse(formatDateTime);
        int min=t.getMinute();
        min=min>=30?60-min:30-min;
        t=t.plusMinutes(min);
        int slotCount=1;
        List<freeSlotDTO> freeSlots=new ArrayList<>();
        while(slotCount<=10){
            String formatDateTime = t.format(formatter);
            if(!dateTimeIntegerMap.containsKey(formatDateTime)){
                freeSlotDTO freeSlot=new freeSlotDTO();
                freeSlot.setSlotNo("Slot no: "+slotCount);
                freeSlot.setDateTime(t);
                freeSlots.add(freeSlot);
                slotCount++;
            }
            t=t.plusMinutes(30);
        }
        return freeSlots;

    }
}
