package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.tm.OrderDetailViewTm;
import com.example.layeredarchitecture.view.tdm.OrderDetailTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDao extends SuperDao {
    public ArrayList<OrderDetailViewTm> orderDetails() throws SQLException, ClassNotFoundException;
}
