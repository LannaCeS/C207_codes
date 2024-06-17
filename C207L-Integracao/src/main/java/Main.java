import DAO.*;
import Model.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Scanner para entrada de dados
        Scanner scanner = new Scanner(System.in);

        //Inserindo os doces do cardápio e a quantidade inicial do dia
        Doces doces = new Doces();

        Receitas receitas = new Receitas();

        ReceitasModel r1 = new ReceitasModel("bolo de cenoura", true, true, true, true, true, false, true, false, false, false);
        ReceitasModel r2 = new ReceitasModel("sonho", true, true, true, true, true, false, false, false, false, true);
        ReceitasModel r3 = new ReceitasModel("bolo de morango", true, true, true, true, true, false, false, true, false, true);
        ReceitasModel receitasModel = new ReceitasModel();
        receitas.insertReceita(r1);
        receitas.insertReceita(r2);
        receitas.insertReceita(r3);

        DocesModel d1 = new DocesModel(8, "bolo de cenoura", "bolo de cenoura");
        DocesModel d2 = new DocesModel(10, "sonho", "sonho");
        DocesModel d3 = new DocesModel(7, "bolo de morango", "bolo de morango");

        doces.insertDoces(d1);
        doces.insertDoces(d2);
        doces.insertDoces(d3);

        //variável de controle
        boolean continua = true;

        //Menu principal
        while(continua) {
            try {
                System.out.println();
                System.out.println("~~~~~Loja de Doces~~~~~");
                System.out.println("1- Caixa");
                System.out.println("2- Pedidos");
                System.out.println("3- Fechar o expediente");
                System.out.println("4- Sair");
                int op = scanner.nextInt();
                scanner.nextLine();

                switch (op) {
                    case 1:
                        boolean again = true;
                        //Menu do caixa
                        while (again) {
                            System.out.println();
                            System.out.println("~~~~Caixa~~~~");
                            System.out.println("1- Anotar pedido");
                            System.out.println("2- Cadastrar um cliente");
                            System.out.println("3- Voltar");
                            int secondOP = scanner.nextInt();
                            scanner.nextLine();
                            switch (secondOP) {
                                case 1:
                                    //anotar pedido
                                    System.out.println("O cliente já é cadastrado? (S/N)");
                                    String answer = scanner.nextLine();
                                    if (answer.equals("S")) {
                                        System.out.println("Informe o CPF do cliente:");
                                        String CPF = scanner.nextLine();
                                        Clientes c = new Clientes();
                                        if (c.selectCPF(CPF)) {
                                            String sweet = "";
                                            boolean denovo = true;
                                            String receita = "";

                                            //cliente já cadastrado, anotar pedido
                                            again = false;
                                            boolean insuficiente = true;
                                            while (denovo) {
                                                System.out.println();
                                                System.out.println("---Escolha o doce---");
                                                System.out.println("1- bolo de cenoura");
                                                System.out.println("2- bolo de morango");
                                                System.out.println("3- sonho");
                                                System.out.println("4- Voltar");
                                                int doceOP = scanner.nextInt();
                                                scanner.nextLine();
                                                if (doceOP == 1) {
                                                    sweet = "bolo de cenoura";
                                                    receita = "bolo de cenoura";
                                                    denovo = false;
                                                } else if (doceOP == 2) {
                                                    sweet = "bolo de morango";
                                                    receita = "bolo de morango";
                                                    denovo = false;
                                                } else if (doceOP == 3) {
                                                    sweet = "sonho";
                                                    receita = "sonho";
                                                    denovo = false;
                                                } else if(doceOP == 4){
                                                    denovo = false;
                                                    insuficiente = false;
                                                    System.out.println("Voltando...");
                                                    break;
                                                }else {
                                                    System.out.println("Digite uma opção válida");
                                                }
                                            }
                                            while (insuficiente) {
                                                System.out.println("---Quantidade---");
                                                System.out.println("Indique a quantidade desejada:");
                                                int quant = scanner.nextInt();
                                                Doces candy = new Doces();
                                                if (candy.verificarQuantidade(quant, sweet)) {
                                                    //tem essa quantidade no estoque
                                                    Pedido pedido1 = new Pedido();
                                                    insuficiente = false;

                                                    //reduzir da quantidade de doce -> update
                                                    DocesModel docesModel = new DocesModel(quant, sweet, receita);
                                                    Doces doces1 = new Doces();
                                                    int quantAnterior = doces1.selectQuantidade(sweet);
                                                    if(doces1.updateDoces(sweet, quant, quantAnterior)){
                                                        System.out.println("Doces atualizados");
                                                    }

                                                    //criar pedido
                                                    pedido1.insertPedido();
                                                    int id = pedido1.selectPedido();

                                                    //inserir o pedido em Clientes_has_Pedidos
                                                    //pegar o endereço da pessoa
                                                    String endereco = c.selectEndereco(CPF);
                                                    PedidosModel pedidosModel = new PedidosModel(id, CPF, endereco);
                                                    Cliente_has_Pedidos clienteHasPedidos = new Cliente_has_Pedidos();
                                                    clienteHasPedidos.insertPedidos(pedidosModel);

                                                    //inserir o pedido em Pedidos_has_Doces
                                                    PedidosDocesModel pedidosDocesModel = new PedidosDocesModel(id, sweet, receita, quant);
                                                    Pedidos_has_Doces pedidosHasDoces = new Pedidos_has_Doces();
                                                    pedidosHasDoces.insertPedidosDoces(pedidosDocesModel);
                                                    System.out.println("Pedido efetuado!");

                                                } else {
                                                    //quantidade excedente
                                                    System.out.println("Quantidade excedente a do estoque");
                                                }
                                            }

                                        } else {
                                            System.out.println("Cliente não cadastrado, por favor cadastre-o");
                                            again = true;
                                        }
                                    } else if (answer.equals("N")) {
                                        System.out.println("O cadastro do cliente é necessário para fazer o pedido");
                                        again = true;
                                    }
                                    break;

                                case 2:
                                    //cadastrar cliente
                                    boolean errado = true;
                                    while (errado) {
                                        System.out.println("---Cadastro---");
                                        System.out.println("Nome: ");
                                        String nome = scanner.nextLine();
                                        System.out.println("CPF:");
                                        String CPF = scanner.nextLine();
                                        System.out.println("Endereço:");
                                        String endereco = scanner.nextLine();

                                        //cadastrar endereço
                                        EnderecosModel enderecosModel = new EnderecosModel(endereco);
                                        Endereco endereco1 = new Endereco();
                                        endereco1.insertEnderecos(enderecosModel);

                                        //inserindo o cliente no BD
                                        ClientesModel clientesModel = new ClientesModel(CPF, nome, endereco);
                                        Clientes clientes = new Clientes();
                                        if (clientes.insertCliente(clientesModel)) {
                                            System.out.println("Cliente cadastrado!");
                                            errado = false;
                                        } else {
                                            System.out.println("Erro no cadastro, tente novamente");
                                        }
                                    }
                                    break;

                                case 3:
                                    System.out.println("Voltando...");
                                    again = false;
                                    break;

                                default:
                                    System.out.println("Insira uma opção válida");
                                    break;

                            }
                        }

                        break;

                    case 2:
                        //mostrar pedidos
                        Pedido pedido = new Pedido();
                        pedido.selectAllPedidos();

                        break;
                    case 3:
                        //apagar os pedidos do dia (sem apagar o id do pedido, portanto sua referência)
                        Pedidos_has_Doces pedidosHasDoces = new Pedidos_has_Doces();
                        pedidosHasDoces.truncatePedidos();
                        continua = false;

                    break;
                    case 4:
                        //sair
                        continua = false;
                    break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Dado inválido, tente novamente");
            }
        }
        System.out.println("A Loja de Doces agradece sua companhia!");
    }
}