module com.warehouse_manager {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.warehouse_manager to javafx.fxml;
    exports com.warehouse_manager;
}
