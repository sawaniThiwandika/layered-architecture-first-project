package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDao {
    public boolean saveOrderDetail(String orderId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;
}
