package mystore.controller;

import mystore.dto.request.PurchaseOrderRequestDTO;
import mystore.dto.response.purchaseOrder.PurchaseOrderCreatedResponseDTO;
import mystore.service.PurchaseOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseService;

    public PurchaseOrderController(PurchaseOrderService purchaseService){
        this.purchaseService = purchaseService;
    }

    @PostMapping("/add")
    public ResponseEntity<PurchaseOrderCreatedResponseDTO> addPurchase(@RequestBody PurchaseOrderRequestDTO purchaseRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(purchaseService.addPurchaseOrder(purchaseRequest));
    }
}
