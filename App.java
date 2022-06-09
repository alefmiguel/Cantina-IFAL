import myexceptions.*;

public class App {

    Cantina cantina = new Cantina();
    FuncionarioDAO funcDAO = new FuncionarioDAO();

    public void acessaAdmin(int usuario, String senha) throws Exception {
        funcDAO.checarFuncionario(usuario);
        funcDAO.checarSenha(senha);
            if (funcDAO.checarFuncionario(usuario) && funcDAO.checarSenha(senha)) {
                System.out.println("\nAutorização válida");
                while (true) {
                    int escolha = 0;
                    try {
                        escolha = Input.inputInt(
                                "\nBem vindo ao modo de administrador.\n1 - Adicionar Produto\n2 - Comprar produto\n3 - Mostrar resumos úteis\n4 - Sair");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    if (escolha == 1) {
                        try {
                            String nome = Input.input("Digite o nome desejado.");
                            String descricao = Input.input("Digite a descrição desejada.");
                            float preco_venda = Input.inputFloat("Digite o preço de venda desejado.");
                            float preco_compra = Input.inputFloat("Digite o preço de compra desejado.");
                            this.cantina.adicionarItem(nome, descricao, preco_venda, preco_compra);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (escolha == 2) {
                        try {
                            cantina.verCardapioCompras();
                            String nome = Input.input("Digite o nome do produto desejado.");
                            int quantidade = Input.inputInt("Digite o numero de unidades a serem compradas");
                            System.out.println(this.cantina.comprarItem(nome, quantidade));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (escolha == 3) {
                        while (true) {
                            int escolha_resumos = 0;
                            try {
                                escolha_resumos = Input.inputInt(
                                        "Bem vindo aos resumos.\n1 - Resumo do lucro/prejúizo\n2 - Resumo de itens em  baixa\n3 - Mostrar resumos de todos os itens\n4 - Sair");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            if (escolha_resumos == 1) {
                                System.out.println(this.cantina.resumoLucro());
                            } else if (escolha_resumos == 2) {
                                System.out.println(this.cantina.resumoItensBaixo());
                            } else if (escolha_resumos == 3) {
                                while (true) {
                                    int escolha_resumo_itens = 0;
                                    try {
                                        escolha_resumo_itens = Input.inputInt(
                                                "Selecione o tipo de ordenação do resumo.\n1 - Por quantidade\n2 - Por descrição\n3 - Sair");
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }

                                    if (escolha_resumo_itens == 1) {
                                        System.out.println(this.cantina.resumoItens(1));
                                    } else if (escolha_resumo_itens == 2) {
                                        System.out.println(this.cantina.resumoItens(2));
                                    } else if (escolha_resumo_itens == 3) {
                                        break;
                                    } else {
                                        System.out.println("Escolha não encontrada!\n");
                                        break;
                                    }
                                }
                            } else if (escolha == 3) {
                                break;
                            } else {
                                System.out.println("Escolha não encontrada!\n");
                                break;
                            }
                        }
                    } else if (escolha == 4) {
                        break;
                    } else {
                        System.out.println("Escolha não encontrada!\n");
                        break;
                    }
                }
            } else {
                throw new FalhaAutenticacaoException("\nAutorização inválida!");
            }
        }

    

    public void acessoCliente() {
        while (true) {
            int escolha = 0;
            try {
                escolha = Input.inputInt(
                        "\nBem vindo ao modo de cliente.\n1 - Comprar produto\n2 - Mostrar cardápio\n3 - Sair");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            switch (escolha) {
                case 1:
                    try {
                        String nome = Input.input("Digite o nome do produto desejado.");
                        int quantidade = Input.inputInt("Digite o numero de unidades a serem compradas.");
                        System.out.println(this.cantina.venderItem(nome, quantidade));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        acessoCliente();
                    }
                    break;

                case 2:
                    cantina.verCardapioVenda();
                    break;

                case 3:

                    break;

                default:
                    System.out.println("Escolha não encontrada!\n");
                    break;
            }

            // if (escolha == 1){
            // try {
            // String nome = Input.input("Digite o nome do produto desejado.");
            // int quantidade = Input.inputInt("Digite o numero de unidades a serem
            // compradas.");
            // System.out.println(this.cantina.venderItem(nome, quantidade));
            // } catch (Exception e) {
            // System.out.println(e.getMessage());
            // }
            // } else if (escolha == 2){
            // cantina.verCardapioVenda();
            // } else if (escolha == 3){
            // break;
            // } else {
            // System.out.println("Escolha não encontrada!\n");
            // break;
            // }
        }
    }

    public void run() throws Exception {
        while (true) {
            int escolha = 0;
            try {
                escolha = Input.inputInt(
                        "Bem vindo ao sistema de compras.\n1 - Acessar como administrador\n2 - Acessar como cliente\n3 - Sair");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            switch (escolha) {
                case 1:
                    try {
                        int usuario = Input.inputInt("Digite o Usuário");
                        String senha = Input.input("Digite a senha de administrador.");
                        acessaAdmin(usuario, senha);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        run();
                    }
                    break;

                case 2:
                    try {
                        acessoCliente();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:

                    break;

                default:
                    System.out.println("Escolha não encontrada!\n");
                    break;

            }
            break;

            // if (escolha == 1){
            // try{
            // String senha = Input.input("Digite a senha de administrador.");
            // acessaAdmin(senha);
            // } catch (Exception e) {
            // System.out.println(e.getMessage());
            // }
            // } else if (escolha == 2){
            // try {
            // acessoCliente();
            // } catch (Exception e) {
            // System.out.println(e.getMessage());
            // }
            // } else if (escolha == 3){
            // break;
            // } else {
            // System.out.println("Escolha não encontrada!\n");
            // }
        }
    }
}