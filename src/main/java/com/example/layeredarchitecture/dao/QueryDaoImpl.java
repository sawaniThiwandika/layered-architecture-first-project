package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.tm.OrderDetailViewTm;
import com.example.layeredarchitecture.view.tdm.OrderDetailTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDaoImpl implements QueryDao {
    @Override
    public ArrayList<OrderDetailViewTm> orderDetails() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailViewTm> objects = new ArrayList<>();
        Connection connection = DBConnection.getDbConnection().getConnection();
        String sql="select o.oid,o.date,o.customerID,c.name,c.address\n" +
                "from orders o\n" +
                "join customer c\n" +
                "on c.id=o.customerID\n" +
                "group by oid order by oid;";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            objects.add(new OrderDetailViewTm(resultSet.getString(1),
                    resultSet.getString(4),resultSet.getString(3),resultSet.getDate("date").toLocalDate(),resultSet.getString(5)));

        }
        return objects;
    }
}
