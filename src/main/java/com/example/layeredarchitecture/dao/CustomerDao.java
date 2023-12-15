package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDao {
    ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
    boolean saveCustomer(String id, String name, String address) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(String id, String name, String address) throws SQLException, ClassNotFoundException;
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
    void deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    String generateNextCustomerId() throws SQLException, ClassNotFoundException;
    ArrayList<String> loadAllCustomerIds() throws SQLException, ClassNotFoundException;
}
