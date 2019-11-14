package multi.tenancy.solution3.utils;

import com.google.common.collect.Maps;

import java.util.Map;

//@Component
public class TenantContext {

    private static final Map<String, Object> contextMap = Maps.newConcurrentMap();

    public void putTokenTenantIdPair(String token, Long tenantId) {
        contextMap.put(token, tenantId);
    }

    public Long getTenantIdWithToken(String token) {
        return (Long) contextMap.get(token);
    }
}
