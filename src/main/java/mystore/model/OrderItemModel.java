package mystore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
public class OrderItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_id", nullable = false)
    private PurchaseOrderModel purchases;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseModel course;

    @Column(name = "price_per_course", nullable = false)
    private Double coursePrice;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal = 0.0;

    public OrderItemModel(CourseModel course, Double coursePrice, Integer quantity){
        this.course = Objects.requireNonNull(course, "The course object cannot be null");
        this.coursePrice = Objects.requireNonNull(coursePrice, "Theprice of the course cannot be null");
        this.quantity = Objects.requireNonNull(quantity, "The quantity of the course cannot be null");
    }
}