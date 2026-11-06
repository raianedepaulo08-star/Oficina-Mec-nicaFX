package com.senai.javafx.aula01;

public class Main {

        public static void main(String[] args) {

            Cliente cliente1 = new Cliente(
                    1,
                    "Paula",
                    "123.456.782",
                    "48 2679-0258"
            );

            Cliente cliente2 = new Cliente(
                    2,
                    "Marco",
                    "852.159.358",
                    "48 3571-0649"
            );

            Cliente cliente3 = new Cliente(
                    3,
                    "Sofia",
                    "369.852.741",
                    "48 6549-0379"
            );

            System.out.println(cliente1);
            System.out.println(cliente2);
            System.out.println(cliente3);


            Veiculo veiculo1 = new Veiculo(
                    1,
                    "Corolla",
                    "ABC123",
                    "Toyota",
                    cliente1
            );

            Veiculo veiculo2 = new Veiculo(
                    2,
                    "Golf",
                    "DEF4656",
                    "Volkswagen",
                    cliente2
            );

            Veiculo veiculo3 = new Veiculo(
                    3,
                    "Classe c",
                    "DEF4656",
                    "Mercedes-benz",
                    cliente3
            );

            System.out.println(veiculo1);
            System.out.println(veiculo2);
            System.out.println(veiculo3);


            cliente1.adicionarVeiculo(veiculo1);
            cliente2.adicionarVeiculo(veiculo2);
            cliente3.adicionarVeiculo(veiculo3);


            OrdemServico ordem1 = new OrdemServico(
                    1,
                    "Aberta",
                    veiculo1
            );

            OrdemServico ordem2 = new OrdemServico(
                    2,
                    "Aberta",
                    veiculo2
            );

            OrdemServico ordem3 = new OrdemServico(
                    3,
                    "Aberta",
                    veiculo3
            );

            System.out.println(ordem1);
            System.out.println(ordem2);
            System.out.println(ordem3);


            Servico servico1 = new Servico(
                    "Troca de oleo",
                    100.0
            );

            Servico servico2 = new Servico(
                    "Alinhamento",
                    80.0
            );

            Servico servico3 = new Servico(
                    "Troca de pastilhas",
                    200.0
            );

            System.out.println(servico1);
            System.out.println(servico2);
            System.out.println(servico3);


            ordem1.adicionarServico(servico1);
            ordem2.adicionarServico(servico2);
            ordem3.adicionarServico(servico3);


            int opcao = 3;

            switch (opcao) {

                case 1:
                    cliente1.listarVeiculos();
                    cliente2.listarVeiculos();
                    cliente3.listarVeiculos();
                    break;

                case 2:
                    ordem1.listarServicos();
                    ordem2.listarServicos();
                    ordem3.listarServicos();
                    break;

                case 3:
                    double total1 = ordem1.calcularTotal();
                    double total2 = ordem2.calcularTotal();
                    double total3 = ordem3.calcularTotal();

                    System.out.println("Total da ordem 1: R$" + total1);
                    System.out.println("Total da ordem 2: R$" + total2);
                    System.out.println("Total da ordem 3: R$" + total3);
                    break;

                case 4:
                    veiculo1.listarOrdens();
                    veiculo2.listarOrdens();
                    veiculo3.listarOrdens();
                    break;

                case 5:
                    ordem1.removerServico(servico2);
                    System.out.println("com.senai.javafx.aula01.Servico removido");
                    break;

                case 6:
                    ordem1.alterarStatus("Em execução");
                    System.out.println("Status da ordem: " + ordem1.status);
                    break;

                case 7:
                    System.out.println("Status da ordem 1: " + ordem1.status);
                    break;
            }
        }
    }

