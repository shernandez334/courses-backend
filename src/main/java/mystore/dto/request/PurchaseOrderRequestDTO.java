package mystore.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PurchaseOrderRequestDTO {
    private Long userId;
    private List<OrderItemRequestDTO> orders;
}
