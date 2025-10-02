package mystore.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class UserCreatedResponseDTO {
    private Long id;
    private String username;
    private String message;
    private LocalTime createdAt;
}
