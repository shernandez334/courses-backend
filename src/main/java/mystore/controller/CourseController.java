package mystore.controller;

import mystore.dto.response.course.CourseDeletedResponseDTO;
import mystore.model.CourseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mystore.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @PostMapping("/add")
    public ResponseEntity<CourseModel> createCourse(@RequestBody CourseModel courseModel){
        CourseModel savedCourse = courseService.addCourse(courseModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<CourseDeletedResponseDTO> eraseCourseDatabase(@PathVariable Long courseId){
        return ResponseEntity.ok(courseService.deleteCourse(courseId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseModel>> showAllCourses(){
        return ResponseEntity.ok(courseService.listCourses());
    }
}