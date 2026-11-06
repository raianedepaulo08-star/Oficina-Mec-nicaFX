module com.senai.javafx.aula01 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.senai.javafx.aula01 to javafx.fxml;
    exports com.senai.javafx.aula01;
    exports com.senai.javafx.aula01.controllers;
    opens com.senai.javafx.aula01.controllers to javafx.fxml;
}