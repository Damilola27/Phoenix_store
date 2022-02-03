package com.phoenix.data.repository;

import com.phoenix.data.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/insert.sql"})
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }
    @Test
    @DisplayName("save a new product to the database")
    void saveProductToDatabaseTest(){
        Product product = new Product();
        product.setName("Bamboo Chair");
        product.setDescription("World class bamboo");
        product.setPrice(5540);
        product.setQuantity(9);

        assertThat(product.getId()).isNull();

        productRepository.save(product);
        log.info("Product Saved :: {}", product);

        assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo("Bamboo Chair");
        assertThat(product.getPrice()).isEqualTo(5540);
        assertThat(product.getDateCreated()).isNotNull();
    }
}