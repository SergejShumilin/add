package com.epam.esm.config;

import com.epam.esm.connection.ConnectionPool;
import com.epam.esm.connection.ManagerDataSource;
import com.epam.esm.exception.DataAccessException;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan("com.epam.esm")
public class SpringAppConfig {

    @Bean
    @Primary
    public DataSource dataSource() throws DataAccessException {
        return new ManagerDataSource(connectionPool());
    }

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/gift_certificates?serverTimezone=Europe/Moscow&allowPublicKeyRetrieval=true&useSSL=false");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("12345");
        return driverManagerDataSource;
    }

    @Bean
    public ConnectionPool connectionPool() throws DataAccessException {
        return new ConnectionPool(mysqlDataSource(), 20);
    }

//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//        driverManagerDataSource.setUrl("jdbc:h2:~/gift_certificates;");
//        driverManagerDataSource.setUsername("");
//        driverManagerDataSource.setPassword("");
//        return driverManagerDataSource;
//    }

//    @Bean
//    public ConnectionPool h2ConnectionPool(){
//        return new ConnectionPool(h2DataSource(), 20);
//    }


}
