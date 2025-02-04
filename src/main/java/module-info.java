module org.smartcity.smartcity {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires annotations;
    requires java.desktop;

    opens org.smartcity.smartcity to javafx.fxml;
    exports org.smartcity.smartcity;
    exports org.smartcity.smartcity.controllers;
    opens org.smartcity.smartcity.controllers to javafx.fxml;
    exports org.smartcity.smartcity.strategy;
    opens org.smartcity.smartcity.strategy to javafx.fxml;
    exports org.smartcity.smartcity.managers;
    opens org.smartcity.smartcity.managers to javafx.fxml;
    exports org.smartcity.smartcity.enums;
    opens org.smartcity.smartcity.enums to javafx.fxml;
    exports org.smartcity.smartcity.dbProxy;
    opens org.smartcity.smartcity.dbProxy to javafx.fxml;
    exports org.smartcity.smartcity.builder;
    opens org.smartcity.smartcity.builder to javafx.fxml;
}