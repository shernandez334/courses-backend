package mystore.service;

import mystore.dto.OrderItemDTO;
import mystore.dto.UserDTO;
import mystore.dto.request.PurchaseOrderRequestDTO;
import mystore.dto.response.purchaseOrder.PurchaseOrderCreatedResponseDTO;
import mystore.exception.UserNotFoundException;
import mystore.model.OrderItemModel;
import mystore.model.PurchaseOrderModel;
import mystore.model.UserModel;
import mystore.repository.CourseRepo;
import mystore.repository.OrderItemRepo;
import mystore.repository.PurchaseOrderRepo;
import mystore.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOrderService {

    private final UserRepo userRepo;
    private final PurchaseOrderRepo purchaseOrderRepo;
    private final CourseRepo courseRepo;
    private final OrderItemService orderItemService;

    public PurchaseOrderService(UserRepo userRepo, PurchaseOrderRepo purchaseOrderRepo,
                                CourseRepo courseRepo, OrderItemService orderItemService){

        this.userRepo = userRepo;
        this.purchaseOrderRepo = purchaseOrderRepo;
        this.courseRepo = courseRepo;
        this.orderItemService = orderItemService;
    }

    public PurchaseOrderCreatedResponseDTO addPurchaseOrder(PurchaseOrderRequestDTO purchaseOrder){
        PurchaseOrderModel purchase = purchaseOrderBuilder(purchaseOrder);
        UserDTO userDTO = userDTOBuilder(purchase.getUser());
        List<OrderItemDTO> orderItemDTOS = orderItemDTObuilder(purchase.getOrders());
        Double purchaseTotal= purchase.getPurchaseTotal();

        return new PurchaseOrderCreatedResponseDTO(
                purchase.getId(),
                userDTO,
                orderItemDTOS,
                purchaseTotal);
    }

    private PurchaseOrderModel purchaseOrderBuilder (PurchaseOrderRequestDTO purchaseOrder){
        UserModel userFound = userRepo.findById(purchaseOrder.getUserId()).
                orElseThrow(() -> new UserNotFoundException("The User could not be found"));

        PurchaseOrderModel purchase = new PurchaseOrderModel();
        purchase.setUser(userFound);
        purchase.setOrders(orderItemService.buildOrderItem(purchaseOrder, purchase));
        Double totalPrice = purchase.getOrders().stream()
                .mapToDouble(OrderItemModel::getSubtotal)
                .sum();

        purchase.setPurchaseTotal(totalPrice);
        this.purchaseOrderRepo.save(purchase);
        return purchase;
    }

    private List<OrderItemDTO> orderItemDTObuilder(List<OrderItemModel> orders) {
        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
        for (OrderItemModel orderItem: orders){
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setCoursePrice(orderItem.getCoursePrice());
            orderItemDTO.setQuantity(orderItem.getQuantity());
            orderItemDTO.setSubtotal(orderItem.getSubtotal());

            orderItemDTOS.add(orderItemDTO);
        }
        return orderItemDTOS;
    }

    private UserDTO userDTOBuilder(UserModel userModel){
        return new UserDTO(
                userModel.getId(), userModel.getUsername());
    }
}