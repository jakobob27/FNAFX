module com.ing {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.ing to javafx.fxml;
    exports com.ing;
}
