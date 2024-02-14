module com.example.project_da_eget_fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.json;
    requires java.sql;
    requires mysql.connector.java;
    requires java.dotenv;

    opens com.example.project_da_eget_fx to javafx.fxml;
    exports com.example.project_da_eget_fx;
}