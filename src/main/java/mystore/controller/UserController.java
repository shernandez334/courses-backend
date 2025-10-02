package mystore.controller;

import mystore.dto.UserDTO;
import mystore.dto.response.user.UserCreatedResponseDTO;
import mystore.dto.response.user.UserDeleteResponseDTO;
import mystore.dto.response.user.UserShowResponseDTO;
import mystore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserShowResponseDTO> getUser(@PathVariable Long userId){
        return ResponseEntity.ok(userService.showUser(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<UserCreatedResponseDTO> createUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.addUser(userDTO));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<UserDeleteResponseDTO> eraseUser(@PathVariable  Long userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}