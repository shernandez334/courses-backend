package mystore.repository;

import mystore.model.PurchaseOrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrderModel, Long> {
}
