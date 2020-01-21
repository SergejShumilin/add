package com.epam.esm.connection;

import com.epam.esm.exception.DataAccessException;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    private final DataSource driverDataSource;
    private static LinkedBlockingQueue<Connection> connectionQueue;

    public ConnectionPool(DataSource driverDataSource, int poolSize) throws DataAccessException {
        this.driverDataSource = driverDataSource;
        connectionQueue = new LinkedBlockingQueue<>(poolSize);
        try {
            for (int i = 0; i < poolSize; i++) {
                Connection connectionOrigin = driverDataSource.getConnection();
                connectionQueue.offer(connectionOrigin);
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    /**
     * @return ProxyConnection from pool
     */
    public Connection getConnection() {
        Connection proxyConnection = null;
        try {
            Connection connection = connectionQueue.take();
            ClassLoader classLoader = connection.getClass().getClassLoader();
            Class<?>[] interfaces = connection.getClass().getInterfaces();
            ConnectionInvocationHandler invocationHandler = new ConnectionInvocationHandler(connection);
            proxyConnection = (Connection) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return proxyConnection;
    }

    /**
     * @param connection return connection to pool
     */
    /*package private*/ static void releaseConnection(Connection connection) {
        try {
                connectionQueue.put(connection);
        } catch (InterruptedException e) {

        }
    }
}
