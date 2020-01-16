package com.epam.esm.dao.connection;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    private final DataSource driverDataSource;
    private LinkedBlockingQueue<ProxyConnection> connectionQueue;
    private List<ProxyConnection> usedConnections = new ArrayList<>();

    public ConnectionPool(DataSource driverDataSource, int poolSize) {
        this.driverDataSource = driverDataSource;
        connectionQueue = new LinkedBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            connectionQueue.offer(createConnection());
        }
    }

    /**
     * @return new ProxyConnection
     */
    private ProxyConnection createConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = new ProxyConnection(driverDataSource.getConnection());
        } catch (SQLException e) {

        }
        return proxyConnection;
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
    /*package private*/ void releaseConnection(ProxyConnection connection) {
        try {
            boolean contains = usedConnections.contains(connection);
            if (contains) {
                connectionQueue.put(connection);
                usedConnections.remove(connection);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (InterruptedException e) {
            connectionQueue.offer(createConnection());
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
