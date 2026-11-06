package com.senai.javafx.aula01.controllers;


import com.senai.javafx.aula01.HelloApplication;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalcularTotal {

    public void mostrar(Stage stage) {
        ComboBox<DadosOficina.OrdemServico> ordens =
                new ComboBox<>();

        ordens.getItems().setAll(DadosOficina.ordens);

        Label resultado = new Label("Total: R$ 0,00");

        Button calcular = new Button("Calcular total");
        Button voltar = new Button("Voltar ao menu");

        calcular.setOnAction(e -> {
            DadosOficina.OrdemServico os = ordens.getValue();

            if (os == null) {
                avisar("Selecione uma ordem de serviço.");
                return;
            }

            resultado.setText(String.format(
                    java.util.Locale.forLanguageTag("pt-BR"),
                    "OS %d — Total: R$ %.2f",
                    os.id, os.calcularTotal()
            ));
        });

        voltar.setOnAction(e ->
                new HelloApplication().start(stage));

        VBox tela = new VBox(15,
                new Label("CALCULAR VALOR TOTAL"),
                ordens, calcular, resultado, voltar);

        tela.setStyle("-fx-padding: 20;");
        stage.setTitle("Calcular Total");

        Scene scene = new Scene(tela, 1200, 800);

        scene.getStylesheets().add(
                getClass().getResource("/style.css").toExternalForm()
        );

        stage.setTitle("Oficina Mecânica - Dashboard");
        stage.setScene(scene);
        stage.show();

        Platform.runLater(() -> {
            stage.setMaximized(false);
            stage.setMaximized(true);
        });
    }

    public void avisar(String mensagem) {
        new Alert(Alert.AlertType.INFORMATION, mensagem)
                .showAndWait();
    }
}
