import myexceptions.*;

public class App {

    Cantina cantina = new Cantina();
    FuncionarioDAO funcDAO = new FuncionarioDAO();

    public void acessaAdmin(int usuario, String senha) throws Exception {
        if (funcDAO.checarFuncionario(usuario) && funcDAO.checarSenha(senha)) {
            System.out.println("\nAutorização válida");

            int escolha = 0;
            while (escolha != 4) {
                try {
                    escolha = Input.inputInt(
                            "\nBem vindo ao modo de administrador.\n1 - Adicionar Produto\n2 - Comprar produto\n3 - Mostrar resumos úteis\n4 - Sair\nResposta: ");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                switch (escolha) {
                    case 1:
                        try {
                            String nome = Input.input("Digite o nome desejado.");
                            String descricao = Input.input("Digite a descrição desejada.");
                            float preco_venda = Input.inputFloat("Digite o preço de venda desejado.");
                            float preco_compra = Input.inputFloat("Digite o preço de compra desejado.");
                            this.cantina.adicionarItem(nome, descricao, preco_venda, preco_compra);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            cantina.verCardapioCompras();
                            int codigo = Input.inputInt("Digite o codigo do produto desejado.");
                            int quantidade = Input.inputInt("Digite o numero de unidades a serem compradas");
                            System.out.println(this.cantina.comprarItem(codigo, quantidade));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        int escolha_resumos = 0;
                        while (escolha_resumos != 4) {

                            try {
                                escolha_resumos = Input.inputInt(
                                        "Bem vindo aos resumos.\n1 - Resumo do lucro/prejúizo\n2 - Resumo de itens em  baixa\n3 - Mostrar resumos de todos os itens\n4 - Sair\nResposta: ");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }

                            switch (escolha_resumos) {
                                case 1:
                                    System.out.println(this.cantina.resumoLucro());
                                    break;

                                case 2:
                                    System.out.println(this.cantina.resumoItensBaixo());
                                    break;

                                case 3:
                                    int escolha_resumo_itens = 0;
                                    while (escolha_resumo_itens != 3) {
                                        try {
                                            escolha_resumo_itens = Input.inputInt(
                                                    "Selecione o tipo de ordenação do resumo.\n1 - Por quantidade\n2 - Por descrição\n3 - Sair\nResposta: ");
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                        switch (escolha_resumo_itens) {
                                            case 1:
                                                System.out.println(this.cantina.resumoItens(1));
                                                break;

                                            case 2:
                                                System.out.println(this.cantina.resumoItens(2));
                                                break;

                                            case 3:
                                                break;

                                            default:
                                                System.out.println("Escolha não encontrada!\n");
                                                break;
                                        }

                                    }
                                break;
                                case 4:
                                    break;

                                default:
                                    System.out.println("Escolha não encontrada!\n");
                                    break;
                            }
                        }
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Escolha não encontrada!\n");
                        break;

                }
            }
        } else {
            throw new FalhaAutenticacaoException("\nAutorização inválida!");
        }
    }

    public void acessoCliente() {
        int escolha = 0;
        while (escolha != 3) {

            try {
                escolha = Input.inputInt(
                        "\nBem vindo ao modo de cliente.\n1 - Comprar produto\n2 - Mostrar cardápio\n3 - Sair\nResposta: ");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            switch (escolha) {
                case 1:
                    try {
                        cantina.verCardapioVenda();
                        int codigo = Input.inputInt("Digite o codigo do produto desejado.");
                        int quantidade = Input.inputInt("Digite o numero de unidades a serem compradas.");
                        System.out.println(this.cantina.venderItem(codigo, quantidade));
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

        }
    }

    public void run() throws Exception {
        int escolha = 0;
        while (escolha != 3) {
            try {
                escolha = Input.inputInt(
                        "Bem vindo ao sistema de compras.\n1 - Acessar como administrador\n2 - Acessar como cliente\n3 - Sair\nResposta: ");
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
        }
    }
}