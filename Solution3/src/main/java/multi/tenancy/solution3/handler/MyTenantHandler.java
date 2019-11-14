package multi.tenancy.solution3.handler;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.google.common.collect.Lists;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

import java.util.List;

public class MyTenantHandler implements TenantHandler {

    //用来区分不同租户数据的字段名
    private static final String SYSTEM_TENANT_ID = "tenant_id";

    //不进行多租户sql条件改写处理的表
    private static final List<String> IGNORE_TENANT_TABLES = Lists.newArrayList("provider", "user");

    private Long tenantId;

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public Expression getTenantId() {
        if (null == this.tenantId) {
            throw new RuntimeException("getCurrentTenantId error.");
        }
        return new LongValue(this.tenantId);
    }

    @Override
    public String getTenantIdColumn() {
        return SYSTEM_TENANT_ID;
    }

    @Override
    public boolean doTableFilter(String tableName) {
        // 忽略掉一些表：如用户信息表（user）本身不需要执行这样的处理。
        return IGNORE_TENANT_TABLES.stream().anyMatch((e) -> e.equalsIgnoreCase(tableName));
    }
}
