package vodafone.hackathon.emergency.model.request;

import lombok.Data;

@Data
public class CreateUserRequestModel {
    String mail;
    String password;
}
