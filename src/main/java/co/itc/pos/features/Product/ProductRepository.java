package co.itc.pos.features.Product;

import co.itc.pos.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findByUuid (String uuid);

    Product findByName (String name);

    Product findTopByOrderByIdDesc();

    boolean existsByName (String name);

    Page findByName (String name, Pageable pageable);

}
