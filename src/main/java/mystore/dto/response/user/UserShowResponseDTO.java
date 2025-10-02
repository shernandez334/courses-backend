package mystore.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class UserShowResponseDTO {

    private String username;
    private LocalTime createdAt;
}
