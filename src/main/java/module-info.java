module com.example.rugou {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.rugou to javafx.fxml;
    exports com.example.rugou;
}