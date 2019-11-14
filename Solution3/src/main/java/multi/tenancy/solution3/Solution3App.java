package multi.tenancy.solution3;

import multi.tenancy.solution3.utils.TenantContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Solution3App {

    public static void main(String[] args) {
        SpringApplication.run(Solution3App.class, args);
    }

    @Bean
    public TenantContext tenantContext() {
        return new TenantContext();
    }
}
