module com.cori.typemaster {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.cori.typemaster to javafx.fxml;
    exports com.cori.typemaster;
}