package mt2.profile.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import mt2.profile.utils.DynamicRoutingDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@MapperScan("mt2.profile.mapper")
public class MybatisPlusConfig {

    @Autowired
    private Map<Object, Object> dataSourceMap;

    @Autowired
    private DataSourceBuilder dataSourceBuilder;

    @Autowired
    private TenantDatasouceConfig tdc;

    @Bean
    public PaginationInterceptor paginationInterceptor() {

        return new PaginationInterceptor();
    }

    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    /**
     * Dynamic data source.
     *
     * @return the data source
     */
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();

        dataSourceBuilder.url(String.format("jdbc:mysql://%s:%d?useSSL=false", tdc.getHost(), tdc.getPort()));
        dataSourceBuilder.username(tdc.getUsername());
        dataSourceBuilder.password(tdc.getPassword());
        DataSource dataSource = dataSourceBuilder.build();

        dataSourceMap.put((long) 0, dataSource);
        dynamicRoutingDataSource.setDefaultTargetDataSource(dataSource);
        // 可动态路由的数据源里装载了所有可以被路由的数据源
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);

        return dynamicRoutingDataSource;
    }

//    @Bean("sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dynamicDataSource());
//
//        MybatisConfiguration configuration = new MybatisConfiguration();
//        configuration.setJdbcTypeForNull(JdbcType.NULL);
//        configuration.setMapUnderscoreToCamelCase(true);
//        configuration.setCacheEnabled(false);
//        sqlSessionFactory.setConfiguration(configuration);
//        sqlSessionFactory.setPlugins(new Interceptor[]{ //PerformanceInterceptor(),OptimisticLockerInterceptor()
//                paginationInterceptor() //添加分页功能
//        });
//
//        return sqlSessionFactory.getObject();
//    }
}
