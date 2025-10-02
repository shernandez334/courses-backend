package mystore.dto.response.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class CourseDeletedResponseDTO {

    private Long id;
    private String name;
    private String message;
    private LocalTime deletedAt;
}
