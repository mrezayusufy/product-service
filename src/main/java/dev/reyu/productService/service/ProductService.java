package dev.reyu.productService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.reyu.productService.dto.ProductRequest;
import dev.reyu.productService.dto.ProductResponse;
import dev.reyu.productService.model.Product;
import dev.reyu.productService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
// will generate a constructor
@RequiredArgsConstructor
@Slf4j
public class ProductService {

  private final ProductRepository productRepository;

  
  // create product
  public void createProduct(ProductRequest productRequest) {
    Product product = Product.builder()
        .name(productRequest.getName())
        .description(productRequest.getDescription())
        .price(productRequest.getPrice())
        .build();
      productRepository.save(product);
      log.info("Product {} is saved.", product.getId());
  }

  // get products
  public List<ProductResponse> getAllProduct(){
    List<Product> products = productRepository.findAll();
    return products.stream().map(this::mapToProductResponse).toList();
  }

  private ProductResponse mapToProductResponse(Product product) {
    return ProductResponse.builder()
                          .id(product.getId())
                          .name(product.getName())
                          .description(product.getDescription())
                          .price(product.getPrice())
                          .build();
  }
}
