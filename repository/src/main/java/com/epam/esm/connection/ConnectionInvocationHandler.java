package com.epam.esm.connection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class ConnectionInvocationHandler implements InvocationHandler {
    private Connection connection;

    public ConnectionInvocationHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        if ("close".equals(method.getName())) {
            ConnectionPool.releaseConnection((Connection) o);
        } else if ("equals".equals(method.getName())) {
            if (args[0] instanceof Proxy) {
                return o.equals(connection);
            } else if (args[0] instanceof Connection) {
                return args[0].equals(connection);
            } else {
                return method.invoke(connection, args);
            }
        }
        else {
            return method.invoke(connection, args);
        }
        return null;
    }
}
