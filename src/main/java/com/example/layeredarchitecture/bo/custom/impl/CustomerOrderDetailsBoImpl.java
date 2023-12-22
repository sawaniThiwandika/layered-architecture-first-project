package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.CustomerOrderDetailsBo;
import com.example.layeredarchitecture.bo.SuperBo;
import com.example.layeredarchitecture.dao.DaoFactory;
import com.example.layeredarchitecture.dao.QueryDaoImpl;
import com.example.layeredarchitecture.model.tm.OrderDetailViewTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderDetailsBoImpl implements CustomerOrderDetailsBo{
    QueryDaoImpl queryDao = (QueryDaoImpl) DaoFactory.getDaoFactory().getDao(DaoFactory.DAOTypes.QUERY);


    @Override
    public ArrayList<OrderDetailViewTm> getOrderCustomerDetails() throws SQLException, ClassNotFoundException {

        return queryDao.orderDetails();
    }
}
