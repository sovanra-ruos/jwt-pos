package co.itc.pos.features.Product;

import co.itc.pos.features.Product.dto.ProductRequest;
import co.itc.pos.features.Product.dto.ProductResponse;
import co.itc.pos.utils.CustomPage;

public interface ProductService {

    CustomPage<ProductResponse> getProducts(int page, int size);

    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse updateProduct(String uuid, ProductRequest productRequest);

    void deleteProduct(String uuid);

    ProductResponse disableProduct(String uuid);

    ProductResponse enableProduct(String uuid);

}
