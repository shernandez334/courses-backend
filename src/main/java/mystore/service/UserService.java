package mystore.service;

import mystore.dto.UserDTO;
import mystore.dto.response.user.UserCreatedResponseDTO;
import mystore.dto.response.user.UserDeleteResponseDTO;
import mystore.dto.response.user.UserShowResponseDTO;
import mystore.exception.UserNotFoundException;
import mystore.model.UserModel;
import mystore.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final String userNotFoundMessage = "The user with the id {} could not be found.";

    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public UserCreatedResponseDTO addUser(UserDTO userDTO){
        UserModel user = new UserModel();
        user.setUsername(userDTO.getUsername());
        user.setCreatedAt(LocalTime.now());
        UserModel savedUser = userRepo.save(user);
        return new UserCreatedResponseDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                "User stored successfully",
                savedUser.getCreatedAt()
        );
    }

    public UserShowResponseDTO showUser(Long userId){
        UserModel userFound = userRepo.findById(userId).
                orElseThrow(() -> new UserNotFoundException(String.format("The user with the id %s could not be found.", userId)));

        return new UserShowResponseDTO(
                userFound.getUsername(),
                userFound.getCreatedAt());
    }

    public UserDeleteResponseDTO deleteUser(Long userId){
        UserModel userFound = userRepo.findById(userId).
                orElseThrow(() -> new UserNotFoundException(String.format(userNotFoundMessage, userId)));
        userRepo.delete(userFound);

        return new UserDeleteResponseDTO(
                userFound.getId(),
                userFound.getUsername(),
                "User deleted successfully");
    }
}
