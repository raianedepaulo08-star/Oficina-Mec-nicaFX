
package com.senai.javafx.aula01.controllers;

import com.senai.javafx.aula01.HelloApplication;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.table.TableColumn;

public class CadastroCliente {

    public void mostrar(Stage stage) {


        // TÍTULO
        Label titulo = new Label("CADASTRO DE CLIENTE");

        // CAMPOS
        TextField txtNome = new TextField();
        txtNome.setPromptText("Nome completo");

        TextField txtCpf = new TextField();
        txtCpf.setPromptText("CPF");

        TextField txtTelefone = new TextField();
        txtTelefone.setPromptText("Telefone");

        // BOTÕES
        Button btnCadastrar = new Button("Cadastrar");
        Button btnExcluir = new Button("Excluir");
        Button btnVoltar = new Button("Voltar ao menu");

        // LISTA DE CLIENTES
        ListView<DadosOficina.Cliente> listaClientes =
                new ListView<>();

        // MOSTRAR CLIENTES CADASTRADOS
        listaClientes.getItems().setAll(
                DadosOficina.clientes
        );

        // =========================
        // CADASTRAR CLIENTE
        // =========================

        btnCadastrar.setOnAction(event -> {

            String nome = txtNome.getText().trim();
            String cpf = txtCpf.getText().trim();
            String telefone = txtTelefone.getText().trim();

            // VERIFICAR CAMPOS
            if (nome.isEmpty() || cpf.isEmpty()
                    || telefone.isEmpty()) {

                mostrarAviso(
                        "Preencha o nome, o CPF e o telefone."
                );

                return;
            }

            // VERIFICAR CPF DUPLICADO
            for (DadosOficina.Cliente cliente :
                    DadosOficina.clientes) {

                if (cliente.cpf.equalsIgnoreCase(cpf)) {

                    mostrarAviso(
                            "Já existe um cliente com esse CPF."
                    );

                    return;
                }
            }

            // CRIAR CLIENTE
            DadosOficina.Cliente novoCliente =
                    new DadosOficina.Cliente(
                            DadosOficina.proximoIdCliente++,
                            nome,
                            cpf,
                            telefone
                    );

            // GUARDAR CLIENTE
            DadosOficina.clientes.add(novoCliente);

            // ATUALIZAR LISTA
            listaClientes.getItems().setAll(
                    DadosOficina.clientes
            );

            mostrarAviso(
                    "Cliente cadastrado com sucesso!"
            );

            // LIMPAR CAMPOS APÓS CADASTRAR
            txtNome.clear();
            txtCpf.clear();
            txtTelefone.clear();

        });

        // =========================
        // EXCLUIR CLIENTE SELECIONADO
        // =========================

        btnExcluir.setOnAction(event -> {

            DadosOficina.Cliente clienteSelecionado =
                    listaClientes.getSelectionModel()
                            .getSelectedItem();

            if (clienteSelecionado != null) {

                // REMOVER DO SISTEMA
                DadosOficina.clientes.remove(
                        clienteSelecionado
                );

                // ATUALIZAR LISTA VISUAL
                listaClientes.getItems().setAll(
                        DadosOficina.clientes
                );

                mostrarAviso(
                        "Cliente excluído com sucesso!"
                );

            } else {

                mostrarAviso(
                        "Selecione um cliente para excluir!"
                );

            }

        });

        // =========================
        // VOLTAR AO MENU
        // =========================

        btnVoltar.setOnAction(event ->
                new HelloApplication().start(stage)
        );

        // =========================
        // ORGANIZAR TELA
        // =========================

        VBox tela = new VBox(10);

        tela.setStyle("-fx-padding: 20;");

        tela.getChildren().addAll(
                titulo,
                txtNome,
                txtCpf,
                txtTelefone,
                btnCadastrar,
                new Label("Clientes cadastrados:"),
                listaClientes,
                btnExcluir,
                btnVoltar
        );

        // CONFIGURAR JANELA
        stage.setTitle("Cadastro de Cliente");

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


    public void mostrarAviso(String mensagem) {

        Alert alerta = new Alert(
                Alert.AlertType.INFORMATION
        );

        alerta.setTitle("Cadastro de Cliente");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();

    }

}
