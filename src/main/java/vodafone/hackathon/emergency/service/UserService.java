package vodafone.hackathon.emergency.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import vodafone.hackathon.emergency.model.User;
import vodafone.hackathon.emergency.model.request.CreateUserRequestModel;
import vodafone.hackathon.emergency.model.request.UpdateUserRequestModel;
import vodafone.hackathon.emergency.model.response.UserResponseModel;
import vodafone.hackathon.emergency.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(CreateUserRequestModel requestModel) {
        if (userRepository.existsByMail(requestModel.getMail()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists.");
        User user = mapper.map(requestModel, User.class);
        user.setPassword(passwordEncoder.encode(requestModel.getPassword()));
        userRepository.save(user);
        return true;
    }

    public UserResponseModel findUserById(long id) {
        if (!userRepository.existsById(id))
            return null;
        return mapper.map(userRepository.getById(id), UserResponseModel.class);
    }

    public List<UserResponseModel> findUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(x -> mapper.map(x, UserResponseModel.class)).collect(Collectors.toList());
    }

    public boolean deleteUser(long id) {
        if (!userRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found  by that id.");
        userRepository.delete(userRepository.getUserById(id));
        return true;
    }

    public boolean updateUser(UpdateUserRequestModel requestModel) {
        if (!userRepository.existsById(requestModel.getId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found by that id");
        User user = userRepository.getById(requestModel.getId());
        mapper.map(requestModel, user);
        user.setPassword(passwordEncoder.encode(requestModel.getPassword()));
        userRepository.save(user);
        return true;
    }


}
