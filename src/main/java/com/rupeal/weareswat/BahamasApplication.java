package com.rupeal.weareswat;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.rupeal.weareswat"})
@OpenAPIDefinition(
        info = @Info(
                title = "Project provide new functionalities for the invoice API",
                description = "Project that create Invoices on a local datasource and stores on an External API"
        )
)
public class BahamasApplication {

    public static void main(String[] args) {
        SpringApplication.run(BahamasApplication.class, args);
    }
}