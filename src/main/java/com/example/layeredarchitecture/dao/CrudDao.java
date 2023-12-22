package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao <T> extends SuperDao{
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean save(T dto) throws SQLException, ClassNotFoundException;
    boolean update(T dto) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    boolean exist(String id)throws SQLException, ClassNotFoundException;
    String generateNewId()throws SQLException, ClassNotFoundException;
    public T find(String newValue) throws SQLException, ClassNotFoundException;

}
