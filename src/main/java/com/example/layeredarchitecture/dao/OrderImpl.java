package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderImpl implements OrderDao{
    OrderDetailDao orderDetail=new OrderDetailDaoImpl();
    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1";
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");*/
       ResultSet rst = SQLUtil.test(sql);

        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public OrderDTO find(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override

    public boolean save(OrderDTO dto) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)";
       /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
        stm.setString(1, orderId);
        stm.setDate(2, Date.valueOf(orderDate));
        stm.setString(3, customerId);

      return stm.executeUpdate()>0;*/
        Boolean test = SQLUtil.test(sql, dto.getOrderId(), dto.getOrderDate(), dto.getCustomerId());
        return test;

    }

    @Override
    public boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String orderId) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
        stm.setString(1, orderId);*/
        String sql="SELECT oid FROM `Orders` WHERE oid=?";
        ResultSet rst = SQLUtil.test(sql, orderId);
        /*if order id already exist*/
        if (rst.next()) {
            return false;

        }
        return true;
    }


}
