module com.warehouse_manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.warehouse_manager to javafx.fxml;
    exports com.warehouse_manager;
}
