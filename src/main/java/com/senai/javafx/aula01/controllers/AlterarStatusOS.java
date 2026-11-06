package com.senai.javafx.aula01.controllers;


import com.senai.javafx.aula01.HelloApplication;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlterarStatusOS {

    public void mostrar(Stage stage) {
        ComboBox<DadosOficina.OrdemServico> ordens =
                new ComboBox<>();

        ordens.getItems().setAll(DadosOficina.ordens);

        ComboBox<String> status = new ComboBox<>();

        status.getItems().addAll(
                "Aberta",
                "Em execução",
                "Finalizada"
        );

        ordens.setOnAction(e -> {
            DadosOficina.OrdemServico os = ordens.getValue();

            if (os != null) {
                status.setValue(os.status);
            }
        });

        Button alterar = new Button("Salvar status");
        Button voltar = new Button("Voltar ao menu");

        alterar.setOnAction(e -> {
            DadosOficina.OrdemServico os = ordens.getValue();
            String novoStatus = status.getValue();

            if (os == null || novoStatus == null) {
                avisar("Selecione a ordem e o status.");
                return;
            }

            os.status = novoStatus;
            ordens.setValue(null);
            ordens.setValue(os);

            avisar("Status atualizado: " + novoStatus);
        });

        voltar.setOnAction(e ->
                new HelloApplication().start(stage));

        VBox tela = new VBox(15,
                new Label("ALTERAR STATUS DA OS"),
                ordens,
                status,
                alterar,
                voltar);

        tela.setStyle("-fx-padding: 20;");
        stage.setTitle("Alterar status");

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
