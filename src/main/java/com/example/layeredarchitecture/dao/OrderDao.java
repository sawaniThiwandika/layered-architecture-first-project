package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface OrderDao extends CrudDao<OrderDTO> {
   /* public String generateNewOrderId() throws SQLException, ClassNotFoundException;


    boolean saveORDER(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException;

    boolean existOrderId(String orderId) throws SQLException, ClassNotFoundException;*/
}
