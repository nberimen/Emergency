package vodafone.hackathon.emergency.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "\"user\"")
@ToString
@RequiredArgsConstructor
public class User extends BaseModel {
    private String mail;
    private String password;
    @OneToMany
    @ToString.Exclude
    private List<MailsToSendMessage> mailsToSendMessage;
}
