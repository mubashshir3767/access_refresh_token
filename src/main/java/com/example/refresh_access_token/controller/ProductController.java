package com.example.refresh_access_token.controller;

import com.example.refresh_access_token.dto.response.ApiResponse;
import com.example.refresh_access_token.entity.ProductDocument;
import com.example.refresh_access_token.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductRepository productRepository;

    @PreAuthorize("hasRole('ADMIN') and hasAuthority('ADD')")
    @PostMapping("/add")
    public ApiResponse add(
            @RequestBody Map<String, String> product
    ) {
        ProductDocument build = ProductDocument.builder().quantity(Integer.parseInt(product.get("quantity"))).name(product.get("name")).build();
        ProductDocument document = productRepository.save(build);
        return new ApiResponse(
                0,
                "Success",
                document
        );
    }
}
