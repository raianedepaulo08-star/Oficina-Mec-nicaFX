package com.senai.javafx.aula01.controllers;

import com.senai.javafx.aula01.HelloApplication;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CadastroOS {

    public void mostrar(Stage stage) {
        TextField placa = new TextField();
        placa.setPromptText("Placa do veículo");

        ListView<DadosOficina.OrdemServico> lista =
                new ListView<>();

        lista.getItems().setAll(DadosOficina.ordens);

        Button abrir = new Button("Abrir ordem de serviço");
        Button voltar = new Button("Voltar ao menu");

        abrir.setOnAction(e -> {
            String p = placa.getText().trim().toUpperCase();

            if (p.isEmpty()) {
                avisar("Informe a placa.");
                return;
            }

            boolean existe = false;

            for (DadosOficina.Veiculo v :
                    DadosOficina.veiculos) {
                if (v.placa.equalsIgnoreCase(p)) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                avisar("Veículo não cadastrado.");
                return;
            }

            DadosOficina.OrdemServico os =
                    new DadosOficina.OrdemServico(
                            DadosOficina.proximoIdOrdem++, p);

            DadosOficina.ordens.add(os);
            lista.getItems().add(os);
            placa.clear();

            avisar("OS " + os.id + " aberta!");
        });

        lista.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(
                    DadosOficina.OrdemServico os, boolean vazio) {
                super.updateItem(os, vazio);
                setText(vazio || os == null ? null : os.toString());
            }
        });

        voltar.setOnAction(e ->
                new HelloApplication().start(stage));

        VBox tela = new VBox(10,
                new Label("ABRIR ORDEM DE SERVIÇO"),
                placa, abrir, lista, voltar);

        tela.setStyle("-fx-padding: 20;");
        stage.setTitle("Ordens de Serviço");

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

