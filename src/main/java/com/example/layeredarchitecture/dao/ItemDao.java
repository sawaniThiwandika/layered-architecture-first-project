package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.ItemDTO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDao extends CrudDao<ItemDTO> {
    /* ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
     boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
     boolean saveItem(String code, String description, BigDecimal unitPrice, int qtyOnHand) throws SQLException, ClassNotFoundException;
     boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
     boolean existItem(String code) throws SQLException, ClassNotFoundException;
     String generateNewItemId() throws SQLException, ClassNotFoundException;


     public ItemDTO find(String newItemCode) throws SQLException, ClassNotFoundException;*/
    ArrayList<String> getAllItemsCodes() throws SQLException, ClassNotFoundException;
    ItemDTO findItem(String code);

}
