package mt2.profile.utils;

import com.google.common.collect.Maps;

import javax.sql.DataSource;
import java.util.Map;

//@Component
public class DatasourceContextSet {

    private static final Map<Long, Object> contextMap = Maps.newConcurrentMap();

    public void putTenantIdDatasourcePair(Long tenantId, DataSource datasource) {
        contextMap.put(tenantId, datasource);
    }

    public DataSource getDatasourceByTenantId(Long tenantId) {
        return (DataSource) contextMap.get(tenantId);
    }
}
