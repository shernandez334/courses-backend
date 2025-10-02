package mystore.service;

import mystore.dto.response.course.CourseDeletedResponseDTO;
import mystore.exception.CourseNotFoundException;
import mystore.model.CourseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import mystore.repository.CourseRepo;

import java.time.LocalTime;
import java.util.List;

@Service
public class CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);
    private final CourseRepo courseRepo;

    public CourseService(CourseRepo courseRepo){
        this.courseRepo = courseRepo;
    }

    public CourseModel addCourse(CourseModel courseModel){
        return courseRepo.save(courseModel);
    }

    public List<CourseModel> listCourses(){
        List<CourseModel> allCourses = this.courseRepo.findAll();
        return allCourses;
    }

    public CourseDeletedResponseDTO deleteCourse(Long courseId){
        CourseModel courseDeleted = courseRepo.findById(courseId).
                orElseThrow(() -> new CourseNotFoundException("This id is not linked with any course."));

        logger.info("Deleting course: {} - {} - {}",
                courseDeleted.getId(), courseDeleted.getName(), courseDeleted.getPrice());

        courseRepo.delete(courseDeleted);
        return new CourseDeletedResponseDTO(
                courseDeleted.getId(),
                courseDeleted.getName(),
                "Course Deleted Successfully",
                LocalTime.now());
    }
}