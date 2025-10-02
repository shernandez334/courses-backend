package mystore.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDeleteResponseDTO {
    private Long UserId;
    private String username;
    private String message;
}
