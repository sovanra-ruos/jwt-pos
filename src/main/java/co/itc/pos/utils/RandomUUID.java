package co.itc.pos.utils;

import co.itc.pos.domain.Product;
import co.itc.pos.domain.User;
import co.itc.pos.features.Product.ProductRepository;
import co.itc.pos.features.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RandomUUID {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public String generateUser(String prefix) {

        User user = userRepository.findTopByOrderByIdDesc();

        if (user == null) {
            String id = prefix + "1001";
            return id;
        } else {
            String id = prefix + 1000 + user.getId().toString();
            return id;
        }

    }

    public String generateProductId() {

        Product product = productRepository.findTopByOrderByIdDesc();

        if (product == null) {
            return "1001";
        } else {
            String id = 1000 + product.getId().toString();

            return id;
        }

    }


}
