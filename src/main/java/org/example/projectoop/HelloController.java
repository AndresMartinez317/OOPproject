package org.example.projectoop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class HelloController {
    @FXML
    private TextField mark1txt;

    @FXML
    private TextField mark2txt;

    @FXML
    private TextField nametxt;

    @FXML
    private TextField totaltxt;
    @FXML
    void Submit(ActionEvent event) {
        register();

    }
    public void register(){

    }
}