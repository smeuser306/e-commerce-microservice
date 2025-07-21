package com.pdt.controllers;

import com.pdt.entities.Product;
import com.pdt.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/catalog")
public class CatalogController {
    private final ProductRepository productRepository;

    public CatalogController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getProducts(){
        Iterable<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product.ProductId productId = new Product.ProductId(id);
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    @GetMapping("get/{category}")
    public ResponseEntity<Iterable<Product>> getProductsByCategory(@PathVariable String category){
       Iterable<Product> products = productRepository.findByCategory(category);
       return ResponseEntity.ok(products);
    }

   @PostMapping
   public ResponseEntity<Product> createProduct(@RequestBody ProductDto product){

        return ResponseEntity.ok(productRepository.save(product.toProduct()));
   }

   @PutMapping
   public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        return ResponseEntity.ok(productRepository.save(product));
   }

   @DeleteMapping("{id}")
   public ResponseEntity<String> deleteProduct(@PathVariable String id){
       productRepository.deleteById(new Product.ProductId(id));
        return ResponseEntity.ok(id);
   }

   private static class ProductDto{
       private String name;
       private String category;
       private String summary;
       private String description;
       private String imageFile;
       private double price;

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }

       public String getCategory() {
           return category;
       }

       public void setCategory(String category) {
           this.category = category;
       }

       public String getSummary() {
           return summary;
       }

       public void setSummary(String summary) {
           this.summary = summary;
       }

       public String getDescription() {
           return description;
       }

       public void setDescription(String description) {
           this.description = description;
       }

       public String getImageFile() {
           return imageFile;
       }

       public void setImageFile(String imageFile) {
           this.imageFile = imageFile;
       }

       public double getPrice() {
           return price;
       }

       public void setPrice(double price) {
           this.price = price;
       }

       public Product toProduct(){
           var product = new Product();
            product.setCategory(this.getCategory());
            product.setDescription(this.getDescription());
            product.setPrice(this.getPrice());
            product.setCategory(this.getCategory());
            product.setImageFile(this.getImageFile());
            product.setName(this.getName());
            product.setSummary(this.getSummary());
            return product;
       }
   }
}
