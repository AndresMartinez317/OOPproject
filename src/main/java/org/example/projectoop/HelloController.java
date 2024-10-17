package org.example.projectoop;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

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
    private TextField nametxt;

    @FXML
    public BarChart<String, Float> barChart;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mark1.getItems().addAll(choice);
        mark2.getItems().addAll(choice);

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
    @FXML
    public void Submit(ActionEvent event) throws SQLException, ClassNotFoundException {
        db.Connect();
        db.Add(nametxt.getText(),mark1.getValue(), mark2.getValue());
    }
    @FXML
    public void Chart(ActionEvent event){
        barChart.getData().clear();
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

}