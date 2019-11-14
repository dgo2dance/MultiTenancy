package mt2.profile.config;

import mt2.profile.interceptors.TenantInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Order()
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TenantInterceptor tenantInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantInterceptor).addPathPatterns("/**").excludePathPatterns("/user/login/**");
        super.addInterceptors(registry);
    }
}
