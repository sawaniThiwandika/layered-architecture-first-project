package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.bo.SuperBo;
import com.example.layeredarchitecture.bo.custom.impl.CustomerBoImpl;
import com.example.layeredarchitecture.bo.custom.impl.CustomerOrderDetailsBoImpl;
import com.example.layeredarchitecture.bo.custom.impl.ItemBoImpl;
import com.example.layeredarchitecture.bo.custom.impl.PlaceOrderBoImpl;
import com.example.layeredarchitecture.dao.DaoFactory;
import com.example.layeredarchitecture.dao.QueryDaoImpl;
import com.example.layeredarchitecture.dao.SuperDao;
import com.example.layeredarchitecture.dao.custom.impl.CustomerDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.ItemDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.OrderDetailDaoImpl;
import com.example.layeredarchitecture.dao.custom.impl.OrderImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {

    }
    public static BoFactory getBOFactory()  {
        return boFactory == null ? boFactory= new BoFactory() : boFactory;
    }
    public enum BOTypes{
        CUSTOMER,ITEM,PLACEORDER,ORDER_DETAIL,QUERY
    }
    public SuperBo getBo(BoFactory.BOTypes boTypes){
        switch (boTypes){
            case ITEM:
                return new ItemBoImpl();
            case CUSTOMER:
                return new CustomerBoImpl();

            case PLACEORDER:
                return new PlaceOrderBoImpl();
            case QUERY:
                return new CustomerOrderDetailsBoImpl();
            default:
                return null;

        }

    }


}
