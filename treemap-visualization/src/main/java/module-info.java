module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires com.jfoenix;
    opens app to javafx.fxml;
    exports app;
}