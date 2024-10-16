module org.example.projectoop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens org.example.projectoop to javafx.fxml;
    exports org.example.projectoop;
}