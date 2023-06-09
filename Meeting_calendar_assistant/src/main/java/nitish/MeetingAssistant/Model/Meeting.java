package nitish.MeetingAssistant.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nitish.MeetingAssistant.Enums.MeetingStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="meeting")
@Getter
@Setter


public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MeetingId;

    @ManyToMany
    @JoinColumn
    private List<User> users;

    private LocalDateTime dateTime;

    @CreationTimestamp
    private Date creationDateTime;

    @Enumerated(value = EnumType.STRING)
    private MeetingStatus status;

    @UpdateTimestamp
    private Date updateDateTime;
    //private LocalDateTime dateTime;
    
}
