package admin_user.configurations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("images/**")
                .addResourceLocations("classpath:/static/images/")
                .addResourceLocations("classpath:/static/images/assets/img/gallery/")
                .addResourceLocations("classpath:/static/images/assets/img/testimonials/")
                .addResourceLocations("classpath:/static/images/assets/img/vendor/bootstrap-icons")
                .addResourceLocations("classpath:/static/fonts/**")
                .addResourceLocations("classpath:/static/vendor/")
        		.addResourceLocations("classpath:/static/js");
    }
}
