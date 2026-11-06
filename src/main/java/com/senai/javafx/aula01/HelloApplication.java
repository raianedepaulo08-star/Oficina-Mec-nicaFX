
package com.senai.javafx.aula01;
import com.senai.javafx.aula01.controllers.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.table.TableColumn;

public class HelloApplication extends Application {


    public Parent root;

    public static void aplicarEstilo(Scene scene) {
        var css = HelloApplication.class.getResource("/style.css");

        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        } else {
            System.out.println("Erro: arquivo style.css não encontrado!");
        }
    }


    @Override
    public void start(Stage stage) {

        // BOTÕES DAS FUNCIONALIDADES
        Button cadastroCliente = new Button("Cadastrar Cliente");
        Button consultarCliente = new Button("Consultar Cliente");
        Button alterarCliente = new Button("Alterar Cliente");
        Button removerCliente = new Button("Remover Cliente");
        Button cadastroVeiculo = new Button("Cadastrar Veículo");
        Button cadastroOS = new Button("Abrir Ordem de Serviço");
        Button cadastroServico = new Button("Adicionar Serviço");
        Button calcularTotal = new Button("Calcular Total");
        Button alterarStatus = new Button("Alterar Status da OS");

        // AÇÕES DOS BOTÕES
        cadastroCliente.setOnAction(e ->
                new CadastroCliente().mostrar(stage));

        consultarCliente.setOnAction(e ->
                new ConsultarCliente().mostrar(stage));

        alterarCliente.setOnAction(e ->
                new AlterarCliente().mostrar(stage));

        removerCliente.setOnAction(e ->
                new RemoverCliente().mostrar(stage));

        cadastroVeiculo.setOnAction(e ->
                new CadastroVeiculo().mostrar(stage));

        cadastroOS.setOnAction(e ->
                new CadastroOS().mostrar(stage));

        cadastroServico.setOnAction(e ->
                new CadastroServico().mostrar(stage));

        calcularTotal.setOnAction(e ->
                new CalcularTotal().mostrar(stage));

        alterarStatus.setOnAction(e ->
                new AlterarStatusOS().mostrar(stage));


        // PAINEL PRINCIPAL
        BorderPane root = new BorderPane();
        root.getStyleClass().add("root");

        // =========================
        // MENU LATERAL
        // =========================

        VBox sidebar = new VBox(15);
        sidebar.setPrefWidth(225);
        sidebar.setPadding(new Insets(25, 15, 20, 15));
        sidebar.getStyleClass().add("sidebar");

        Label logoIcone = new Label("⚙");
        logoIcone.getStyleClass().add("logo-icon");

        Label logo = new Label("OFICINA");
        logo.getStyleClass().add("logo");

        Label logoSub = new Label("MECÂNICA");
        logoSub.getStyleClass().add("logo-sub");

        VBox logoBox = new VBox(2, logoIcone, logo, logoSub);
        logoBox.setAlignment(Pos.CENTER);

        Label usuario = new Label("OFICINA MECÂNICA");
        usuario.getStyleClass().add("usuario");

        Label tipoUsuario = new Label("Proprietário");
        tipoUsuario.getStyleClass().add("tipo-usuario");

        VBox perfil = new VBox(4, usuario, tipoUsuario);
        perfil.getStyleClass().add("perfil");

        Label principal = new Label("PRINCIPAL");
        principal.getStyleClass().add("secao-menu");

        Button inicio = criarMenu("⌂", "Início", null);

        Label operacional = new Label("OPERACIONAL");
        operacional.getStyleClass().add("secao-menu");

        Button clientes = criarMenu("♙", "Clientes",
                () -> cadastroCliente.fire());

        Button consultar = criarMenu("⌕", "Consultar Clientes",
                () -> consultarCliente.fire());

        Button veiculos = criarMenu("♧", "Veículos",
                () -> cadastroVeiculo.fire());

        Button ordens = criarMenu("▤", "Ordens de Serviço",
                () -> cadastroOS.fire());

        Button servicos = criarMenu("🔧", "Serviços",
                () -> cadastroServico.fire());

        Button alterar = criarMenu("✎", "Alterar Cliente",
                () -> alterarCliente.fire());

        Button remover = criarMenu("⌫", "Remover Cliente",
                () -> removerCliente.fire());

        Label financeiro = new Label("FINANCEIRO");
        financeiro.getStyleClass().add("secao-menu");

        Button total = criarMenu("R$", "Calcular Total",
                () -> calcularTotal.fire());

        Button status = criarMenu("◷", "Status das OS",
                () -> alterarStatus.fire());

        sidebar.getChildren().addAll(
                logoBox,
                perfil,
                principal,
                inicio,
                operacional,
                clientes,
                consultar,
                veiculos,
                ordens,
                servicos,
                alterar,
                remover,
                financeiro,
                total,
                status
        );

        // =========================
        // BARRA SUPERIOR
        // =========================

        HBox topbar = new HBox(15);
        topbar.setAlignment(Pos.CENTER_LEFT);
        topbar.setPadding(new Insets(12, 25, 12, 25));
        topbar.getStyleClass().add("topbar");

        Label pagina = new Label("▦   Início");
        pagina.getStyleClass().add("pagina");

        Region espaco = new Region();
        HBox.setHgrow(espaco, Priority.ALWAYS);

        TextField pesquisa = new TextField();
        pesquisa.setPromptText("Buscar cliente, placa ou OS...");
        pesquisa.setPrefWidth(250);
        pesquisa.getStyleClass().add("pesquisa");

        Button rapida = new Button("+ Ação rápida");
        rapida.getStyleClass().add("botao-rapido");
        rapida.setOnAction(e -> cadastroOS.fire());

        Label notificacao = new Label("♧");
        notificacao.getStyleClass().add("notificacao");

        topbar.getChildren().addAll(
                pagina, espaco, pesquisa, rapida, notificacao
        );

        // =========================
        // CONTEÚDO CENTRAL
        // =========================

        VBox conteudo = new VBox(20);
        conteudo.setPadding(new Insets(25));
        conteudo.getStyleClass().add("conteudo");

        Label titulo = new Label("Oficina Mecânica");
        titulo.getStyleClass().add("titulo");

        Label frase = new Label(
                "Cada peça bem colocada é um passo para o sucesso."
        );
        frase.getStyleClass().add("subtitulo");

        VBox cabecalho = new VBox(5, titulo, frase);

        // ATALHOS
        GridPane atalhos = new GridPane();
        atalhos.setHgap(12);
        atalhos.setVgap(12);

        atalhos.add(criarAtalho("▤", "Nova OS",
                () -> cadastroOS.fire()), 0, 0);

        atalhos.add(criarAtalho("🔧", "Serviços",
                () -> cadastroServico.fire()), 1, 0);

        atalhos.add(criarAtalho("R$", "Calcular Total",
                () -> calcularTotal.fire()), 2, 0);

        atalhos.add(criarAtalho("♙", "Novo Cliente",
                () -> cadastroCliente.fire()), 3, 0);

        // INDICADORES
        Label tituloIndicadores = new Label("VISÃO GERAL");
        tituloIndicadores.getStyleClass().add("secao-titulo");

        GridPane indicadores = new GridPane();
        indicadores.setHgap(12);
        indicadores.setVgap(12);

        indicadores.add(criarIndicador("♙", "0",
                "Clientes", "azul"), 0, 0);

        indicadores.add(criarIndicador("♧", "0",
                "Veículos", "verde"), 1, 0);

        indicadores.add(criarIndicador("▤", "0",
                "Ordens de Serviço", "amarelo"), 2, 0);

        indicadores.add(criarIndicador("⚙", "0",
                "Serviços", "vermelho"), 3, 0);

        // FINANCEIRO
        Label tituloFinanceiro = new Label("RESUMO FINANCEIRO");
        tituloFinanceiro.getStyleClass().add("secao-titulo");

        GridPane financeiroGrid = new GridPane();
        financeiroGrid.setHgap(12);
        financeiroGrid.setVgap(12);

        financeiroGrid.add(criarFinanceiro(
                "↗", "Receitas", "R$ 0,00", "verde"), 0, 0);

        financeiroGrid.add(criarFinanceiro(
                "↘", "Despesas", "R$ 0,00", "vermelho"), 1, 0);

        financeiroGrid.add(criarFinanceiro(
                "⌁", "Lucro", "R$ 0,00", "verde"), 2, 0);

        financeiroGrid.add(criarFinanceiro(
                "◷", "A Receber", "R$ 0,00", "amarelo"), 3, 0);

        // PAINÉIS INFERIORES
        HBox paineis = new HBox(15);

        VBox lembretes = criarPainel(
                "♧",
                "Lembretes de Serviço",
                "Acompanhe suas ordens de serviço"
        );

        VBox pendencias = criarPainel(
                "⚠",
                "Clientes e Pendências",
                "Acompanhe os registros da oficina"
        );

        HBox.setHgrow(lembretes, Priority.ALWAYS);
        HBox.setHgrow(pendencias, Priority.ALWAYS);

        paineis.getChildren().addAll(lembretes, pendencias);

        conteudo.getChildren().addAll(
                cabecalho,
                atalhos,
                tituloIndicadores,
                indicadores,
                tituloFinanceiro,
                financeiroGrid,
                paineis
        );

        ScrollPane scroll = new ScrollPane(conteudo);
        scroll.setFitToWidth(true);
        scroll.getStyleClass().add("scroll");

        // MONTAGEM FINAL
        root.setLeft(sidebar);
        root.setTop(topbar);
        root.setCenter(scroll);

        Scene scene = new Scene(root, 1200, 800);

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


// MÉTODOS AUXILIARES

    private Button criarMenu(String icone, String texto, Runnable acao) {
        Button botao = new Button(icone + "     " + texto);
        botao.getStyleClass().add("menu-lateral");
        botao.setMaxWidth(Double.MAX_VALUE);

        if (acao != null) {
            botao.setOnAction(e -> acao.run());
        }

        return botao;
    }

    private Button criarAtalho(String icone, String texto, Runnable acao) {
        Button botao = new Button(icone + "     " + texto + "     ›");
        botao.getStyleClass().add("atalho");
        botao.setMaxWidth(Double.MAX_VALUE);
        botao.setPrefHeight(65);
        botao.setOnAction(e -> acao.run());
        return botao;
    }

    private VBox criarIndicador(
            String icone, String numero, String texto, String cor) {

        Label simbolo = new Label(icone);
        simbolo.getStyleClass().add("simbolo");

        Label valor = new Label(numero);
        valor.getStyleClass().add("indicador-valor");

        Label nome = new Label(texto);
        nome.getStyleClass().add("indicador-nome");

        VBox card = new VBox(10, simbolo, valor, nome);
        card.getStyleClass().addAll("card", cor);
        card.setPadding(new Insets(15));
        card.setPrefSize(180, 125);

        return card;
    }

    private VBox criarFinanceiro(
            String icone, String titulo, String valor, String cor) {

        Label simbolo = new Label(icone);
        simbolo.getStyleClass().add("simbolo");

        Label nome = new Label(titulo);
        nome.getStyleClass().add("indicador-nome");

        Label quantia = new Label(valor);
        quantia.getStyleClass().add("financeiro-valor");
        quantia.getStyleClass().add(cor);

        VBox card = new VBox(10, simbolo, nome, quantia);
        card.getStyleClass().add("card");
        card.setPadding(new Insets(15));
        card.setPrefSize(180, 120);

        return card;
    }

    private VBox criarPainel(
            String icone, String titulo, String descricao) {

        Label simbolo = new Label(icone);
        simbolo.getStyleClass().add("simbolo");

        Label nome = new Label(titulo);
        nome.getStyleClass().add("painel-titulo");

        Label detalhe = new Label(descricao);
        detalhe.getStyleClass().add("indicador-nome");

        Label mensagem = new Label("Nenhum registro para exibir");
        mensagem.getStyleClass().add("mensagem-vazia");

        VBox cabecalho = new VBox(5, simbolo, nome, detalhe);

        VBox painel = new VBox(18, cabecalho, mensagem);
        painel.getStyleClass().add("card-painel");
        painel.setPadding(new Insets(20));
        painel.setPrefHeight(190);

        VBox.setVgrow(mensagem, Priority.ALWAYS);
        mensagem.setAlignment(Pos.CENTER);

        return painel;
    }

    public static void main(String[] args) {
        launch(args);
    }
}