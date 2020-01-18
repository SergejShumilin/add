package com.epam.esm.connection;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ManagerDataSource extends DriverManagerDataSource {
    private ConnectionPool connectionPool;

    public ManagerDataSource(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }


}
