module com.cori.typemaster {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.prefs;
    requires org.json;
    requires annotations;

    opens com.cori.typemaster to javafx.fxml;
    exports com.cori.typemaster.app;
    opens com.cori.typemaster.app to javafx.fxml;
    exports com.cori.typemaster.controller;
    opens com.cori.typemaster.controller to javafx.fxml;
}