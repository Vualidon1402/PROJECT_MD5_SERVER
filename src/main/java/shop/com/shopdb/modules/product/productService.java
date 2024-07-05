package shop.com.shopdb.modules.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.com.shopdb.modules.category.service.CategoryService;
import shop.com.shopdb.modules.product.dto.ProductRep;
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

    public List<ProductRep> getAllProducts() {
        List<Product> products = productRepository.findByStatusTrue();
        List<ProductRep> productReps = new ArrayList<>();
        for (Product product : products) {
            ProductRep productRep = new ProductRep();
            productRep.setProduct_id(product.getProduct_id());
            productRep.setProduct_name(product.getProduct_name());
            productRep.setSku(product.getSku());
            productRep.setDescription(String.valueOf(product.getDescription()));
            productRep.setUnitPrice(product.getUnitPrice());
            productRep.setStock_quantity(product.getStock_quantity());
            productRep.setCategory_id(product.getCategory().getCategory_id());
            productRep.setImageUrls(new ArrayList<>());
//            productRep.setImageUrls(product.getImages().stream()
//                    .map(ImageProduct::getImageUrl)
//                    .collect(Collectors.toList()));
            productRep.setStatus(product.getStatus());
            productRep.setCreated_at(product.getCreated_at().toString());
            if (product.getUpdated_at() != null) {
                productRep.setUpdated_at(product.getUpdated_at().toString());
            }
            productReps.add(productRep);
        }
        return productReps;
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
//        List<ImageProduct> images = new ArrayList<>();
//        for (String imageUrl : productRq.getImages()) {
//            ImageProduct imageProduct = new ImageProduct();
//            imageProduct.setImageUrl(imageUrl);
//            imageProduct.setProduct(product);
//            images.add(imageProduct);
//        }
//        product.setImages(images);

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
}
