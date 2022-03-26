package vodafone.hackathon.emergency.model.request;

import lombok.Data;

@Data
public class UpdateUserRequestModel {
    Long id;
    String name;
    String surname;
    String username;
    String password;
}
