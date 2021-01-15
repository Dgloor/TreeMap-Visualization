module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens app to javafx.fxml;
    exports app;
}