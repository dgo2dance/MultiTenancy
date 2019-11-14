package mt2.profile.interceptors;

import mt2.profile.client.UserClient;
import mt2.profile.config.TenantDatasouceConfig;
import mt2.profile.utils.AppContextHelper;
import mt2.profile.utils.DynamicRoutingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Map;

@Component
public class TenantInterceptor implements HandlerInterceptor {

    @Autowired
    private DataSourceBuilder dataSourceBuilder;

    @Autowired
    private Map<Object, Object> dataSourceMap;

    @Autowired
    private UserClient  userClient;

    @Autowired
    private TenantDatasouceConfig tdc;

//    @Autowired
//    private DynamicRoutingDataSource dynamicDataSource;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String path=httpServletRequest.getRequestURI();
        String token = path.substring(path.lastIndexOf("/") + 1);
        if (null != token) {
            //UserClient userClient = (UserClient) AppContextHelper.getBean(UserClient.class);
            Long tenantId = userClient.tenantIdByToken(token);
            if(null != tenantId) {
                prepareDatasource(tenantId);

                return true;
            }
        }

        return false;
    }

    private void prepareDatasource(Long tenantId) {
        DynamicRoutingDataSource dynamicDataSource = (DynamicRoutingDataSource) AppContextHelper.getBean("dynamicDataSource");
        DataSource dataSource = (DataSource) dataSourceMap.get(tenantId);

        if (null == dataSource) {
            dataSourceBuilder.url(String.format("jdbc:mysql://%s:%d/%s%d?useSSL=false&characterEncoding=UTF8", tdc.getHost(), tdc.getPort(), tdc.getSchema(), tenantId));
            dataSourceBuilder.username(tdc.getUsername());
            dataSourceBuilder.password(tdc.getPassword());
            dataSource = dataSourceBuilder.build();

            dataSourceMap.put(tenantId, dataSource);
            dynamicDataSource.setTargetDataSources(dataSourceMap);
            dynamicDataSource.afterPropertiesSet();
        }

        dynamicDataSource.setTenantId(tenantId);
    }
}
