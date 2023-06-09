package nitish.MeetingAssistant.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String name;

    @Column(unique = true)
    private String email;

    @ManyToMany(mappedBy = "users",cascade = CascadeType.ALL)
    private List<Meeting> meetingList;

    public User(String name, String email, List<Meeting> meetingList) {
        this.name = name;
        this.email = email;
        this.meetingList = meetingList;
    }

    public User() {
        List<Meeting> meetingsList=new ArrayList<>();
        this.meetingList = meetingsList;
    }
}
