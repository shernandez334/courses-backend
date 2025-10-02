package mystore.repository;

import mystore.model.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepo extends JpaRepository<CourseModel, Long> {
    Optional<CourseModel> findByName(String name);
}
