package com.epam.esm.connection;

import com.epam.esm.exception.DataAccessException;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    private final DataSource driverDataSource;
    private static LinkedBlockingQueue<ProxyConnection> connectionQueue;
    private static List<ProxyConnection> usedConnections = new ArrayList<>();

    public ConnectionPool(DataSource driverDataSource, int poolSize) throws DataAccessException {
        this.driverDataSource = driverDataSource;
        connectionQueue = new LinkedBlockingQueue<>(poolSize);
        try {
            ProxyConnection proxyConnection = new ProxyConnection(driverDataSource.getConnection());
            for (int i = 0; i < poolSize; i++) {
                connectionQueue.offer(proxyConnection);
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    /**
     * @return ProxyConnection from pool
     */
    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = connectionQueue.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {

        }
        return connection;
    }

    /**
     * @param connection return connection to pool
     */
    public static void releaseConnection(ProxyConnection connection) {
        try {
            boolean contains = usedConnections.contains(connection);
            if (contains) {
                connectionQueue.put(connection);
                usedConnections.remove(connection);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (InterruptedException e) {

        }
    }

    /**
     * close all connections in pool
     */
    @PreDestroy
    public void closeAll() {
        connectionQueue.forEach(ProxyConnection::doClose);
    }


}
