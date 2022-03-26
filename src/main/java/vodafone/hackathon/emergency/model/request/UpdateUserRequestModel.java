package vodafone.hackathon.emergency.model.request;

import lombok.Data;

@Data
public class UpdateUserRequestModel {
    Long id;
    String mail;
    String password;
}
