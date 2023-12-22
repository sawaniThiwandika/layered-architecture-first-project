package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.CustomerDao;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDao {
    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM Customer";
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
       /*
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();*/
       // ResultSet rst = stm.executeQuery("SELECT * FROM Customer");
        ResultSet rst = SQLUtil.test(sql);

        while (rst.next()){

            customerDTOS.add( new CustomerDTO(rst.getString(1),rst.getString(2),rst.getString(3)));
        }
        return customerDTOS;
    }
    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO Customer (id,name, address) VALUES (?,?,?)";
        boolean test = SQLUtil.test(sql, dto.getId(), dto.getName(), dto.getAddress());

       /* PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        pstm.setString(1, id);
        pstm.setString(2, name);
        pstm.setString(3, address);*/
        return test;
    }
    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        String sql="UPDATE Customer SET name=?, address=? WHERE id=?";
       boolean test = SQLUtil.test(sql, dto.getName(), dto.getAddress(),dto.getId());

       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setString(1, name);
        pstm.setString(2, address);
        pstm.setString(3, id);*/
        return test;
    }
    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);*/
        String sql="SELECT id FROM Customer WHERE id=?";
       ResultSet test = SQLUtil.test(sql, id);
       if(!test.next()){
           return false;
       }
        return true;

    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setString(1, id)*/;
        String sql="DELETE FROM Customer WHERE id=?";
        boolean test = SQLUtil.test(sql, id);
        return test;

    }
    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        String sql="SELECT id FROM Customer ORDER BY id DESC LIMIT 1;";
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");*/
        ResultSet rst = SQLUtil.test(sql);
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }
    @Override
    public ArrayList<String> loadAllCustomerIds() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");*/
        String sql="SELECT * FROM Customer";
        ResultSet rst = SQLUtil.test(sql);
        ArrayList<String> ids = new ArrayList<>();
        while(rst.next()){
            ids.add(rst.getString("id"));
        }
        return ids;
    }
    public CustomerDTO find(String newValue) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
        pstm.setString(1, newValue + "");
        ResultSet rst = pstm.executeQuery();
        rst.next();*/
        String sql="SELECT * FROM Customer WHERE id=?";
        ResultSet rst = SQLUtil.test(sql,newValue + "");
        rst.next();
        CustomerDTO customerDTO = new CustomerDTO(newValue + "", rst.getString("name"), rst.getString("address"));
        return customerDTO;
    }
}
