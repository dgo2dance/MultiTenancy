package multi.tenancy.solution3.interceptors;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import multi.tenancy.solution3.handler.MyTenantHandler;
import multi.tenancy.solution3.utils.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TenantInterceptor implements HandlerInterceptor {

    @Autowired
    private TenantContext tenantContext;

    @Autowired
    private PaginationInterceptor pi;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String path=httpServletRequest.getRequestURI();
        String token = path.substring(path.lastIndexOf("/") + 1);
        if (isTokenValid(token)) {
            prepareTenantContext(token);
            return true;
        }

        return false;
    }

    private void prepareTenantContext(String token) {
        TenantSqlParser tenantSqlParser = (TenantSqlParser) pi.getSqlParserList().get(0);
        MyTenantHandler myTenantHandler = (multi.tenancy.solution3.handler.MyTenantHandler) tenantSqlParser.getTenantHandler();
        myTenantHandler.setTenantId(tenantContext.getTenantIdWithToken(token));
    }

    private boolean isTokenValid(String token) {
        return null != tenantContext.getTenantIdWithToken(token);
    }
}
