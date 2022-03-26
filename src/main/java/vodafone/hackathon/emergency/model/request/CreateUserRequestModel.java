package vodafone.hackathon.emergency.model.request;

import lombok.Data;

@Data
public class CreateUserRequestModel {
    private String mail;
    private String password;
}
