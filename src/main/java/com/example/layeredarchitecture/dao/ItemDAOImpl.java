package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDao{
    @Override
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();*/
        String sql="SELECT * FROM Item";

        ResultSet rst = SQLUtil.test(sql);


        while (rst.next()){
            itemDTOS.add(new ItemDTO(rst.getString(1),rst.getString(2),rst.getBigDecimal(3),rst.getInt(4)));
        }
        return itemDTOS;

    }
    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM Item WHERE code=?";
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
        pstm.setString(1, code);*/
        boolean test = SQLUtil.test(sql, code);
        return test;
    }
    @Override
    public boolean save(ItemDTO dto) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
        pstm.setString(1, code);
        pstm.setString(2, description);
        pstm.setBigDecimal(3, unitPrice);
        pstm.setInt(4, qtyOnHand);
        return pstm.executeUpdate()>0;*/
        String sql="INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)";
        boolean test = SQLUtil.test(sql, dto.getCode(),dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand());
        return test;

    }
    @Override
    public boolean update(ItemDTO dto) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
        pstm.setString(1, dto.getDescription());
        pstm.setBigDecimal(2,dto.getUnitPrice());
        pstm.setInt(3, dto.getQtyOnHand());
        pstm.setString(4, dto.getCode());
        return pstm.executeUpdate()>0;*/
        String sql="UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?";
        return SQLUtil.test(sql,dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getCode());
    }
    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
      /*  Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();*/
        String sql="SELECT code FROM Item WHERE code=?";
        ResultSet resultSet = SQLUtil.test(sql, code);
        if(resultSet.next()){
            return true;
        }
        return false;

    }
    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        String sql="SELECT code FROM Item ORDER BY code DESC LIMIT 1";
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");*/
        ResultSet rst = SQLUtil.test(sql);
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }



    @Override
    public ArrayList<String> getAllItemsCodes() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM Item";
        ResultSet rst = SQLUtil.test(sql);
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");*/
        ArrayList<String> codes = new ArrayList<>();
        while (rst.next()){
            codes.add(rst.getString("code"));
        }
        return codes;
    }


    public ItemDTO findItem(String code) {
        try {
            String sql="SELECT * FROM Item WHERE code=?";
           /* Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
            pstm.setString(1, code);
            ResultSet rst = pstm.executeQuery();*/
            ResultSet rst = SQLUtil.test(sql, code);
            rst.next();
            return new ItemDTO(code, rst.getString("description"), rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand"));
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public ItemDTO find(String newItemCode) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM Item WHERE code=?";
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, newItemCode + "");
        ResultSet rst = pstm.executeQuery();*/
        ResultSet rst = SQLUtil.test(sql,newItemCode + "");
        rst.next();
        ItemDTO item = new ItemDTO(newItemCode + "", rst.getString("description"), rst.getBigDecimal
                ("unitPrice"), rst.getInt("qtyOnHand"));
        return item;
    }
}
