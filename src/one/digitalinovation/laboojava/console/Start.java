package one.digitalinovation.laboojava.console;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.*;
import one.digitalinovation.laboojava.negocio.ClienteNegocio;
import one.digitalinovation.laboojava.negocio.PedidoNegocio;
import one.digitalinovation.laboojava.negocio.ProdutoNegocio;
import one.digitalinovation.laboojava.utilidade.LeitoraDados;

import java.util.Optional;

/**
 * Classe responsável por controlar a execução da aplicação.
 * @author thiago leite
 **/
public class Start {

    public static Cliente clienteLogado = null;

    private static Banco banco = new Banco();

    private static ClienteNegocio clienteNegocio = new ClienteNegocio(banco);
    private static PedidoNegocio pedidoNegocio = new PedidoNegocio(banco);
    private static ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);


    /**
     * Método utilitário para inicializar a aplicação.
     * @param args Parâmetros que podem ser passados para auxiliar na execução.
     */
    public static void main(String[] args) {

        System.out.println("Bem vindo ao e-Compras");

        String opcao = "";

        while(true) {

            if (clienteLogado == null) {
                System.out.println("Fazer LOGIN ou CADASTRO? [login]/[cadastro]");
                String entrada = LeitoraDados.lerDado();

                switch (entrada) {
                    case "login":
                        System.out.println("Digite o cpf:");

                        String cpf = "";
                        cpf = LeitoraDados.lerDado();

                        identificarUsuario(cpf);
                        break;
                    case "cadastro":
                        clienteNegocio.incluirCliente();
                        System.out.println("Digite o cpf novamente:");

                        String cpf02 = "";
                        cpf02 = LeitoraDados.lerDado();

                        identificarUsuario(cpf02);
                    default:
                        System.out.println("Opção inválida");
                        break;
                }

            }

            System.out.println("Selecione uma opção:");
            System.out.println("1 - Cadastrar Livro"); //TRATAMENTO QUANDO DIGITA GENERO NAO EXISTENTE
            System.out.println("2 - Excluir Livro");
            System.out.println("3 - Consultar Livro");

            System.out.println("3.5 - Cadastrar Caderno"); //TRATAMENTO QUANDO DIGITA NUM MATERIAS NAO EXISTENTE
            System.out.println("4 - Excluir Caderno");
            System.out.println("4.5 - Consultar Caderno");

            System.out.println("5 - Fazer pedido");
            System.out.println("6 - Excluir pedido");

            System.out.println("7 - Listar produtos");
            System.out.println("8 - Listar pedidos");

            System.out.println("8.5 - Cadastrar Cliente");
            System.out.println("8.8 - Excluir Cliente");

            System.out.println("9 - Deslogar");
            System.out.println("10 - Sair");

            opcao = LeitoraDados.lerDado();

            switch (opcao) {
                case "1":
                    Livro livro = LeitoraDados.lerLivro();
                    produtoNegocio.salvar(livro);
                    break;
                case "2":
                    System.out.println("Digite o código do livro: ");
                    String codigoLivro00 = LeitoraDados.lerDado();
                    produtoNegocio.excluir(codigoLivro00);
                    break;
                case "3":
                    System.out.println("Digite o código do Livro: ");
                    String codigoLivro = LeitoraDados.lerDado();
                    produtoNegocio.consultar(codigoLivro);
                case "3.5":
                    Caderno caderno= LeitoraDados.lerCaderno();
                    produtoNegocio.salvar(caderno);
                    break;
                case "4":
                    System.out.println("Digite o código do caderno: ");
                    String codigoCaderno00 = LeitoraDados.lerDado();
                    produtoNegocio.excluir(codigoCaderno00);
                    break;
                case "4.5":
                    System.out.println("Digite a código do caderno: ");
                    String codigoCaderno = LeitoraDados.lerDado();
                    produtoNegocio.consultar(codigoCaderno);
                case "5":
                    Pedido pedido = LeitoraDados.lerPedido(banco);
                    Optional<Cupom> cupom = LeitoraDados.lerCupom(banco);

                    if (cupom.isPresent()) {
                        pedidoNegocio.salvar(pedido, cupom.get());
                    } else {
                        pedidoNegocio.salvar(pedido);
                    }
                    break;
                case "6":
                    System.out.println("Digite o código do pedido: ");
                    String codigoPedido = LeitoraDados.lerDado();
                    pedidoNegocio.excluir(codigoPedido);
                    break;
                case "7":
                    produtoNegocio.listarTodos();
                    break;
                case "8":
                    pedidoNegocio.listarTodos();
                    break;
                case "8.5":
                    clienteNegocio.incluirCliente();
                case "8.8":
                    System.out.println("Digite a posição do cliente na lista: ");
                    int posicao = LeitoraDados.lerInt();
                    clienteNegocio.excluirCliente(posicao);
                case "9":
                    System.out.println(String.format("Volte sempre %s!", clienteLogado.getNome()));
                    clienteLogado = null;
                    break;
                case "10":
                    System.out.println("Aplicação encerrada.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    /**
     * Procura o usuário na base de dados.
     * @param cpf CPF do usuário que deseja logar na aplicação
     */
    private static void identificarUsuario(String cpf) {

        Optional<Cliente> resultado = clienteNegocio.consultar(cpf);

        if (resultado.isPresent()) {

            Cliente cliente = resultado.get();
            System.out.println(String.format("Olá %s! Você está logado.", cliente.getNome()));
            clienteLogado = cliente;
        } else {
            System.out.println("Usuário não cadastrado.");
            System.exit(0);
        }
    }
}
