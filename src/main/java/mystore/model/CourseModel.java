package mystore.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class CourseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    @Column(name = "course_name", nullable = false)
    private String name;

    @Column(name = "course_price", nullable = false)
    private Double price;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<OrderItemModel> orderItems;

    @Column(name = "course_image", nullable = false)
    private String imageURL;

    public CourseModel(String name, Double price, String imageURL){
        this.name = Objects.requireNonNull(name, "The classes name cannot be null");
        this.price = Objects.requireNonNull(price, "The classes price cannot be null");
        this.imageURL = Objects.requireNonNull(imageURL, "The imageURL cannot be null");
    }
}