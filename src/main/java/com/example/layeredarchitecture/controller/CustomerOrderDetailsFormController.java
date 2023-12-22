package com.example.layeredarchitecture.controller;
import com.example.layeredarchitecture.bo.custom.BoFactory;
import com.example.layeredarchitecture.bo.custom.CustomerOrderDetailsBo;
import com.example.layeredarchitecture.bo.custom.impl.CustomerOrderDetailsBoImpl;
import com.example.layeredarchitecture.dao.QueryDaoImpl;
import com.example.layeredarchitecture.model.tm.OrderDetailViewTm;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CustomerOrderDetailsFormController {


    @FXML
    private TableColumn<?, ?> colCusName;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colOid;


    @FXML
    private TableView<OrderDetailViewTm> tableOrderDetails;
    @FXML
    private AnchorPane root;
    CustomerOrderDetailsBo customerOrderDetailsBo= (CustomerOrderDetailsBo) BoFactory.getBOFactory().getBo(BoFactory.BOTypes.QUERY);
    @FXML
    private void navigateToHome(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/com/example/layeredarchitecture/main-form.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());

    }
    public void initialize() throws ClassNotFoundException {
        setCellValueFactory();
        loadAllOrders();

    }

    private void loadAllOrders() throws ClassNotFoundException {

        ObservableList<OrderDetailViewTm> obList = FXCollections.observableArrayList();
        try {
            QueryDaoImpl queryDao = new QueryDaoImpl();
            List<OrderDetailViewTm> dtoList = customerOrderDetailsBo.getOrderCustomerDetails();

            for (OrderDetailViewTm dto : dtoList) {
                obList.add(
                        new OrderDetailViewTm(
                                dto.getId(),
                                dto.getName(),
                               dto.getCusId(),
                                dto.getDate(),
                                dto.getAddress()
                        )
                );
            }

            tableOrderDetails.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colOid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("cusId"));

    }

}
