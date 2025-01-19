package co.itc.pos.features.inventory;

import co.itc.pos.domain.Inventory;
import co.itc.pos.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    Inventory findByUuid (String uuid);

    Inventory findInventoryByProduct_Uuid (String uuid);

    Inventory findInventoryByProduct (Product product);


}
