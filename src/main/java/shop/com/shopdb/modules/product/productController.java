package shop.com.shopdb.modules.product;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.com.shopdb.modules.product.dto.ProductRep;
import shop.com.shopdb.modules.product.dto.ProductResAdd;
import shop.com.shopdb.modules.product.model.Product;

import java.util.List;
@CrossOrigin("*")
@RestController
public class productController {

    @Autowired
    private productService productService;

    @GetMapping("/product")
    public ResponseEntity<List<ProductRep>> getProduct() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/product/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/product/add")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductResAdd productRq) {
        System.out.println("Product added successfully" + productRq.getImages());
       try {
              return new ResponseEntity<Product>(productService.addProduct(productRq), HttpStatus.OK);
         } catch (Exception e) {
                return new ResponseEntity<Product>((Product) null, HttpStatus.BAD_REQUEST);
       }
    }
}
