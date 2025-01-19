package co.itc.pos.mapper;

import co.itc.pos.domain.Product;
import co.itc.pos.features.Product.dto.ProductRequest;
import co.itc.pos.features.Product.dto.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toProductResponse(Product product);
    Product toProduct(ProductRequest productRequest);

}
