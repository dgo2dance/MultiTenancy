package mt2.user;

import mt2.user.utils.TenantContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class Mt2UserApp {

    public static void main(String[] args) {
        SpringApplication.run(Mt2UserApp.class, args);
    }

    @Bean
    public TenantContext tenantContext() {
        return new TenantContext();
    }
}
