module graphs {
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

    opens com.allert.allert to javafx.fxml;
    exports com.allert.allert;

    opens com.allert.allert.graphs to javafx.fxml;
    exports com.allert.allert.graphs;

    opens com.allert.allert.classes to javafx.fxml;
    exports com.allert.allert.classes;
}

