package com.senai.javafx.aula01.controllers;

import com.senai.javafx.aula01.HelloApplication;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RemoverCliente {

    public void mostrar(Stage stage) {
        ListView<DadosOficina.Cliente> lista =
                new ListView<>();

        lista.getItems().setAll(DadosOficina.clientes);

        Button remover = new Button("Remover cliente");
        Button voltar = new Button("Voltar ao menu");

        remover.setOnAction(e -> {
            DadosOficina.Cliente c =
                    lista.getSelectionModel().getSelectedItem();

            if (c == null) {
                avisar("Selecione um cliente.");
                return;
            }

            for (DadosOficina.Veiculo v :
                    DadosOficina.veiculos) {
                if (v.cpfCliente.equalsIgnoreCase(c.cpf)) {
                    avisar("Este cliente possui veículos cadastrados.");
                    return;
                }
            }

            DadosOficina.clientes.remove(c);
            lista.getItems().remove(c);
            avisar("Cliente removido.");
        });

        voltar.setOnAction(e ->
                new HelloApplication().start(stage));


        VBox tela = new VBox(10,
                new Label("REMOVER CLIENTE"),
                lista, remover, voltar);

        tela.setStyle("-fx-padding: 20;");
        stage.setTitle("Remover Cliente");
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
