package co.itc.pos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@OpenAPIDefinition(
//        info = @Info(
//                title = "Mobile Banking API(Data Analytics Class)",
//                version = "1.0",
//                description = "Mobile Banking API"
//        ),
//        security = @SecurityRequirement(name = "bearerAuth")
//)
//@SecurityScheme(
//        name="bearerAuth",
//        type = SecuritySchemeType.HTTP,
//        scheme = "bearer",
//        bearerFormat = "JWT",
//        in = SecuritySchemeIn.HEADER
//)
public class PosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PosApplication.class, args);
    }

}
