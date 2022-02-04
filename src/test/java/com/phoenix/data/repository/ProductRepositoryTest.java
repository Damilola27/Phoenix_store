package com.phoenix.data.repository;

import com.phoenix.data.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

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
    @Test
    @DisplayName("Find an existing product from database")
    void findExistingProductFromDatabase(){
        Product product =productRepository.findById(12L).orElse(null);
        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(12);
        assertThat(product.getName()).isEqualTo("Luxury Mop");
        assertThat(product.getPrice()).isEqualTo(2340);
        assertThat(product.getQuantity()).isEqualTo(3);

        log.info("Product retrieved :: {}", product);
    }
    @Test
    @DisplayName("Find all products in the database")
    void findAllProductsTest(){
       List<Product> productList = productRepository.findAll();
        assertThat(productList).isNotNull();
        assertThat(productList.size()).isEqualTo(4);
    }
    @Test
    @DisplayName("Find products by name")
    void findProductByName(){
        Product product = productRepository.findByName("Luxury Mop").orElse(null);
        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(12);
        assertThat(product.getPrice()).isEqualTo(2340);
        assertThat(product.getQuantity()).isEqualTo(3);

        log.info("Product retrieved :: {}", product);
    }

    @Test
    @DisplayName("Update product by id")
    void UpdateProductById(){
      Product savedProduct = productRepository.findByName("Macbook Air").orElse(null);
      assertThat(savedProduct).isNotNull();

      assertThat(savedProduct.getName()).isEqualTo("Macbook Air");
      assertThat(savedProduct.getPrice()).isEqualTo(18320);
      savedProduct.setName("Macbook Air 13");
      savedProduct.setPrice(23420);

      productRepository.save(savedProduct);
      assertThat(savedProduct.getName()).isEqualTo("Macbook Air 13");
      assertThat(savedProduct.getPrice()).isEqualTo(23420);
    }
}
//(13,"Macbook Air",18320,4),
