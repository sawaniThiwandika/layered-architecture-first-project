package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDao;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDao extends CrudDao {
    public boolean saveOrderDetail(String orderId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;
}
