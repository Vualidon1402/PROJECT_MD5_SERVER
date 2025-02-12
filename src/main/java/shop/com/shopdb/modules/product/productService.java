package shop.com.shopdb.modules.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.com.shopdb.modules.category.service.CategoryService;
import shop.com.shopdb.modules.product.dto.ProductRep;
import shop.com.shopdb.modules.product.dto.ProductReqUpdate;
import shop.com.shopdb.modules.product.dto.ProductResAdd;
import shop.com.shopdb.modules.product.model.ImageProduct;
import shop.com.shopdb.modules.product.model.Product;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class productService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;

    public Page<ProductRep> getAllProducts(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> products = productRepository.findByStatusTrue(pageable);
        return products.map(this::convertToProductRep);
    }

    public Product addProduct(ProductResAdd productRq) {
        Product product = new Product();
        product.setProduct_name(productRq.getProduct_name());
        product.setSku(product.generateSku());
        product.setDescription(productRq.getDescription());
        product.setUnitPrice(productRq.getUnitPrice());
        product.setStock_quantity(productRq.getStock_quantity());
        product.setCategory(categoryService.getCategoryById(productRq.getCategory_id()));
        product.setCreated_at(new Date().toString());
        product.setStatus(true);
        List<ImageProduct> images = new ArrayList<>();
        for (String imageUrl : productRq.getImageUrls()) {
            ImageProduct imageProduct = new ImageProduct();
            imageProduct.setImageUrl(imageUrl);
            images.add(imageProduct);
        }
        product.setImageProducts(images);
        productRepository.save(product);
        return product;
    }

    public void deleteProduct(Integer productId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setStatus(false);
            productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    public ProductRep getProductById(Integer productId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            return convertToProductRep(productOpt.get());
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    public Page<ProductRep> searchProductByName(int page, int pageSize, String search) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> products = productRepository.findByProductNameContainingAndStatusTrue(search, pageable);
        return products.map(this::convertToProductRep);
    }
    public ProductRep convertToProductRep(Product product) {
        ProductRep productRep = new ProductRep();
        productRep.setProduct_id(product.getProduct_id());
        productRep.setProduct_name(product.getProduct_name());
        productRep.setSku(product.getSku());
        productRep.setDescription(product.getDescription());
        productRep.setUnitPrice(product.getUnitPrice());
        productRep.setStock_quantity(product.getStock_quantity());
        productRep.setCategory_id(product.getCategory().getCategory_id());
        productRep.setCreated_at(product.getCreated_at());
        productRep.setStatus(product.getStatus());
        productRep.setImageUrls(product.getImageProducts().stream().map(ImageProduct::getImageUrl).collect(Collectors.toList()));
        return productRep;
    }

    public String updateProduct(Integer productId, ProductReqUpdate productRq) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setProduct_name(productRq.getProduct_name());
            product.setDescription(productRq.getDescription());
            product.setUnitPrice(productRq.getUnitPrice());
            product.setStock_quantity(productRq.getStock_quantity());
            product.setCategory(categoryService.getCategoryById(productRq.getCategory_id()));
            product.setUpdated_at(new Date().toString());
            product.getImageProducts().clear();
            for (String imageUrl : productRq.getImageUrls()) {
                ImageProduct imageProduct = new ImageProduct();
                imageProduct.setImageUrl(imageUrl);
                product.getImageProducts().add(imageProduct);
            }
            productRepository.save(product);
            return "Product updated successfully";
        } else {
            throw new RuntimeException("Product not found");
        }
    }
}
