package shop.com.shopdb.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configurable
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Áp dụng cho tất cả các đường dẫn
                        .allowedOrigins("http://localhost:5173") // Cho phép nguồn gốc này
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Cho phép các phương thức này
                        .allowedHeaders("*") // Cho phép tất cả tiêu đề
                        .allowCredentials(true); // Cho phép gửi thông tin xác thực
            }
        };
    }
}
