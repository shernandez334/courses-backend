package mystore.service;

import mystore.dto.request.OrderItemRequestDTO;
import mystore.dto.request.PurchaseOrderRequestDTO;
import mystore.exception.CourseNotFoundException;
import mystore.model.CourseModel;
import mystore.model.OrderItemModel;
import mystore.model.PurchaseOrderModel;
import mystore.repository.CourseRepo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {

    private final CourseRepo courseRepo;

    public OrderItemService(CourseRepo courseRepo){
        this.courseRepo = courseRepo;
    }

    public List<OrderItemModel> buildOrderItem(PurchaseOrderRequestDTO purchaseOrder, PurchaseOrderModel purchase){
        List<OrderItemModel> ordersList = new ArrayList<>();
        for (OrderItemRequestDTO order: purchaseOrder.getOrders()){
            CourseModel courseFound = courseRepo.findById(order.getCourseId())
                    .orElseThrow(() -> new CourseNotFoundException("The course requested could not be found"));

            OrderItemModel orderItem = new OrderItemModel();
            orderItem.setCourse(courseFound);
            orderItem.setQuantity(order.getQuantity());
            orderItem.setCoursePrice(courseFound.getPrice());
            orderItem.setSubtotal(courseFound.getPrice() * order.getQuantity());
            orderItem.setPurchases(purchase);
            ordersList.add(orderItem);
        }
        return ordersList;
    }
}
