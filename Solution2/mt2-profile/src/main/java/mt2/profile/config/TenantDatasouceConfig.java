package mt2.profile.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "tenant.datasource")
@Component
public class TenantDatasouceConfig {
    private String host;
    private int port;
    private String username;
    private String password;
    private String schema;
}
