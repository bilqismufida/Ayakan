package com.method.ayakan.repository;

import java.util.HashMap;

public abstract class BaseRepository<T> {
    
    protected HashMap<Integer, T> db = new HashMap<>();

    public abstract void save(T item);

    public T findById(int id) {
        return db.get(id);
    }

    public HashMap<Integer, T> findAll() {
        return db;
    }

    public void delete(int id) {
        db.remove(id);
    }

    public boolean check(int id) {
        return db.containsKey(id);
    }

    public boolean isEmpty() {
        return db.isEmpty();
    }
}