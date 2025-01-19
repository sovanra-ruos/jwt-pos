package co.itc.pos.features.SaleItem;

import co.itc.pos.domain.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem,Long> {

    SaleItem findByUuid (String uuid);

}
