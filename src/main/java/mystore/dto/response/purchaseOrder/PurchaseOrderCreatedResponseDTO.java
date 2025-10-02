package mystore.dto.response.purchaseOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mystore.dto.OrderItemDTO;
import mystore.dto.UserDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseOrderCreatedResponseDTO {
    private Long purchaseOrderId;
    private UserDTO user;
    private List<OrderItemDTO> orderItemDTOS;
    private double purchaseTotal;
}
