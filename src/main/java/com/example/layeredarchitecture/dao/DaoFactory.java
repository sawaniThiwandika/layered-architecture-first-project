package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.dao.custom.CustomerDao;
import com.example.layeredarchitecture.dao.custom.impl.CustomerDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.ItemDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.OrderDetailDaoImpl;
import com.example.layeredarchitecture.dao.custom.impl.OrderImpl;
import com.example.layeredarchitecture.db.DBConnection;

import java.sql.SQLException;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {

    }
    public static DaoFactory getDaoFactory()  {
        return daoFactory == null ? daoFactory= new DaoFactory() : daoFactory;
    }
    public enum DAOTypes{
        CUSTOMER,ITEM,ORDER,ORDER_DETAIL,QUERY
    }
    public SuperDao getDao(DAOTypes daoTypes){
        switch (daoTypes){
            case ITEM:
                return new ItemDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();

            case ORDER:
                return new OrderImpl();
            case ORDER_DETAIL:
                return new OrderDetailDaoImpl();
            case QUERY:
                return new QueryDaoImpl();
            default:
                return null;

        }

    }


}
