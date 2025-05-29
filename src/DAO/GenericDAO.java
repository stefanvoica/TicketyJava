package DAO;

import Utile.DatabaseConnection;

import java.sql.Connection;

public abstract class GenericDAO<T> {
    protected Connection connection;

    public GenericDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public abstract void create(T entity);
    public abstract T read(int id);
    public abstract void update(T entity);
    public abstract void delete(int id);
}
