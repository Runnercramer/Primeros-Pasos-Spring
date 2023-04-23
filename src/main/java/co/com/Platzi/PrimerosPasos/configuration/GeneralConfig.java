package co.com.Platzi.PrimerosPasos.configuration;

import co.com.Platzi.PrimerosPasos.bean.MyBeanWithProperties;
import co.com.Platzi.PrimerosPasos.bean.MyBeanWithPropertiesImplementation;
import co.com.Platzi.PrimerosPasos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:connection.properties")
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfig {

    @Value("${value.name}")
    private String name;

    @Value("${value.lastName}")
    private String lastName;
    @Value("${value.random}")
    private String random;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${dirver}")
    private String driver;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;

    @Bean
    public MyBeanWithProperties function() {
        return new MyBeanWithPropertiesImplementation(name, lastName);
    }


    @Bean
    public DataSource datasource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(jdbcUrl);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }
}
