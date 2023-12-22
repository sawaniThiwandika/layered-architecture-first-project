package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.bo.SuperBo;
import com.example.layeredarchitecture.model.tm.OrderDetailViewTm;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerOrderDetailsBo extends SuperBo {
    public ArrayList<OrderDetailViewTm> getOrderCustomerDetails() throws SQLException, ClassNotFoundException;
}
