package org.example.projectoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController implements Initializable {
    DBconn db = new DBconn();

    @FXML
    private Button Load;
    @FXML
    private ChoiceBox<Integer> mark1;
    private final Integer[] choice = {1,2,3,4,5};

    @FXML
    private ChoiceBox<Integer> mark2;

    @FXML
    private TableView<Student> table;
    @FXML
    private TableColumn<Student, Integer> ID;
    @FXML
    private TableColumn<Student, String> tfname;
    @FXML
    private TableColumn<Student, Integer> tmark2;
    @FXML
    private TableColumn<Student, Integer> tmark1;
    @FXML
    private TableColumn<Student, Float> Ttotal;

    @FXML
    private TextField nametxt;

    @FXML
    public BarChart<String, Float> barChart;
    ObservableList<Student> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mark1.getItems().addAll(choice);
        mark2.getItems().addAll(choice);
        XYChart.Series<String,Float> chart1 = new XYChart.Series<>();
        XYChart.Series<String,Float> chart2 = new XYChart.Series<>();
        XYChart.Series<String, Float> chart3 = new XYChart.Series<>();

        ID.setCellValueFactory(new PropertyValueFactory<Student,Integer>("ID"));
        tfname.setCellValueFactory(new PropertyValueFactory<Student,String>("Fname"));
        tmark1.setCellValueFactory(new PropertyValueFactory<Student,Integer>("mark1"));
        tmark2.setCellValueFactory(new PropertyValueFactory<Student,Integer>("mark2"));
        Ttotal.setCellValueFactory(new PropertyValueFactory<Student,Float>("total"));

        try {
            db.Connect();
            ResultSet rs = db.show();
            while(rs.next()){
                chart1.getData().add(new XYChart.Data<>(rs.getString(2),rs.getFloat(3)) );
                chart2.getData().add(new XYChart.Data<>(rs.getString(2),rs.getFloat(4)) );
                chart3.getData().add(new XYChart.Data<>(rs.getString(2),rs.getFloat(5)) );

                data.add(new Student(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getFloat(5)));

            }


            table.setItems(data);
            barChart.getData().addAll(chart1,chart2,chart3);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
    @FXML
    public void Submit(ActionEvent event) throws SQLException, ClassNotFoundException {
        db.Connect();
        db.Add(nametxt.getText(),mark1.getValue(), mark2.getValue());
    }
    @FXML
    public void Chart(ActionEvent event){
        barChart.getData().clear();
        barChart.layout();
        XYChart.Series<String,Float> chart1 = new XYChart.Series<>();
        XYChart.Series<String,Float> chart2 = new XYChart.Series<>();
        XYChart.Series<String, Float> chart3 = new XYChart.Series<>();
        try {
            db.Connect();
            ResultSet rs = db.show();
            while(rs.next()){
                chart1.getData().add(new XYChart.Data<>(rs.getString(2),rs.getFloat(3)) );
                chart2.getData().add(new XYChart.Data<>(rs.getString(2),rs.getFloat(4)) );
                chart3.getData().add(new XYChart.Data<>(rs.getString(2),rs.getFloat(5)) );
            }


            barChart.getData().addAll(chart1,chart2,chart3);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void table(ActionEvent event){

        try {
            db.Connect();
            ResultSet rs = db.show();
            data.clear();
            while(rs.next()){
                data.add(new Student(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getFloat(5)));
            }
            table.setItems(data);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }



    }

}