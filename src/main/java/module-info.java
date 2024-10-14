module org.example.projectoop {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.projectoop to javafx.fxml;
    exports org.example.projectoop;
}