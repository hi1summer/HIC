package tsinghua.hic.commons.dynamicdatasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataSourceComponent {
    @Bean(name = "dataSource1")
    @ConfigurationProperties(prefix = "spring.datasource1")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataSource2")
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "dataSource")
    @DependsOn({ "dataSource1", "dataSource2" })
    public DataSource dynamicDataSource() {
        DataSource dataSource1 = dataSource1();
        DataSource dataSource2 = dataSource2();
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDatasources = new HashMap<>(2);
        targetDatasources.put(DSNames.MASTER, dataSource1);
        targetDatasources.put(DSNames.SLAVE1, dataSource2);
        dynamicDataSource.setDefaultTargetDataSource(dataSource1);
        dynamicDataSource.setTargetDataSources(targetDatasources);
        return dynamicDataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
