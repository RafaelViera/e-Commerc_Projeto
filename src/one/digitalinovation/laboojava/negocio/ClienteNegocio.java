package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Cliente;
import one.digitalinovation.laboojava.utilidade.LeitoraDados;

import java.util.Optional;

/**
 * Classe para manipular a entidade {@link Cliente}.
 * @author thiago leite
 **/
public class ClienteNegocio {

    /**
     * {@inheritDoc}.
     **/
    private Banco bancoDados;

    /**
     * Construtor.
     * @param banco Banco de dados para ter acesso aos clientes cadastrados
     **/
    public ClienteNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    /**
     * Consulta o cliente pelo seu CPF.
     * @param cpf CPF de um cliente
     * @return O cliente que possuir o CPF passado.
     **/
    public Optional<Cliente> consultar(String cpf) {

        for (Cliente cliente : bancoDados.getCliente()) {
            if (cliente.getCpf().equals(cpf)) {
               return Optional.of(cliente);
            } else {
            return Optional.empty();
            }
        }
        return Optional.empty();
    }


    /**
     * Cadastra um novo cliente.
     **/
    public void incluirCliente(){
        Cliente novoCliente = LeitoraDados.lerCliente();

        boolean clienteRepetido = false;
        for (Cliente clienteComparativo : bancoDados.getCliente()) {
            if (novoCliente.getCpf().equals(clienteComparativo.getCpf())){
                clienteRepetido = true;
                System.out.println("Cliente já cadastrado.");
                break;
            }
        }
        if (!clienteRepetido) {
            this.bancoDados.adicionarCliente(novoCliente);
            System.out.println("Cliente cadastrado com sucesso");
        }
    }

    /**
     *
     * @param posicaoExcluir
     */
    public void excluirCliente(int posicaoExcluir){
        int posic = 1;
        if (bancoDados.getProdutos().length == 0) {
            System.out.println("Não há clientes cadastrados");
        } else {
            for (Cliente cliente : bancoDados.getCliente()){
                if (posicaoExcluir == posic){
                    this.bancoDados.removerCliente(posic);
                    break;
                }
                posic = posic + 1;
            }
        }
    }

}
