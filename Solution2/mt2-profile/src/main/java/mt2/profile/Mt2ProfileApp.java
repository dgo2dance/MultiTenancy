package mt2.profile;

import com.google.common.collect.Maps;
import mt2.profile.utils.DatasourceContextSet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class Mt2ProfileApp {

    public static void main(String[] args) {
        SpringApplication.run(Mt2ProfileApp.class, args);
    }

    @Bean
    public DatasourceContextSet datasourceContextSet() {
        return new DatasourceContextSet();
    }

    @Bean
    public DataSourceBuilder dataSourceBuilder() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create().driverClassName("com.mysql.jdbc.Driver");
        return dataSourceBuilder;
    }

    @Bean(name = "dataSourceMap")
    public Map<Object, Object> dataSourceMap() {
        Map<Object, Object> dataSourceMap = Maps.newConcurrentMap();
        return dataSourceMap;
    }
}
