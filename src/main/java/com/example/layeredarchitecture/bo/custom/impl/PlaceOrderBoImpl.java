package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.PlaceOrderBo;
import com.example.layeredarchitecture.dao.DaoFactory;
import com.example.layeredarchitecture.dao.custom.ItemDao;
import com.example.layeredarchitecture.dao.custom.OrderDao;
import com.example.layeredarchitecture.dao.custom.OrderDetailDao;
import com.example.layeredarchitecture.dao.custom.impl.CustomerDAOImpl;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBoImpl implements PlaceOrderBo {
    private CustomerDAOImpl customerDAO = (CustomerDAOImpl) DaoFactory.getDaoFactory().getDao(DaoFactory.DAOTypes.CUSTOMER);
    private ItemDao item= (ItemDao) DaoFactory.getDaoFactory().getDao(DaoFactory.DAOTypes.ITEM);
    private OrderDao order= (OrderDao) DaoFactory.getDaoFactory().getDao(DaoFactory.DAOTypes.ORDER);
    OrderDetailDao orderDetail= (OrderDetailDao) DaoFactory.getDaoFactory().getDao(DaoFactory.DAOTypes.ORDER_DETAIL);
    @Override
    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        Connection connection = null;


        if (!order.exist(orderId)) {
            return true;
            // return false;
            // System.out.println("Already existed");
        }
        System.out.println("saveeeeee");
           /* connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
            stm.setString(1, orderId);
            //if order id already exist
            if (stm.executeQuery().next()) {

            }*/
        connection = DBConnection.getDbConnection().getConnection();

        connection.setAutoCommit(false);
        OrderDTO dto = new OrderDTO(orderId, orderDate, customerId);

        boolean saveORDER = order.save(dto);

        if (!saveORDER) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;

        }
        System.out.println("save order");
        boolean saveOrderDetail = orderDetail.saveOrderDetail(orderId, orderDetails);
        if (!saveOrderDetail) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
        System.out.println("save order detail");


        connection.commit();
        connection.setAutoCommit(true);
        return true;


    }
    @Override
    public CustomerDTO searchCustomer(String newValue) throws SQLException, ClassNotFoundException {

     return customerDAO.find(newValue);

    }
    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {

       return item.findItem(code);

    }
    @Override
    public ArrayList<String> getAllItemCodes() throws SQLException, ClassNotFoundException {

         return item.getAllItemsCodes();

    }
    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {

        return customerDAO.loadAllCustomerIds();

    }
    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {

        return item.exist(code);
    }
    @Override

    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {

        return customerDAO.exist(id);

    }
    @Override
    public String genarateNewId() throws SQLException, ClassNotFoundException {

        return order.generateNewId();

    }





}
