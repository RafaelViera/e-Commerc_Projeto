package one.digitalinovation.laboojava.entidade;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa a entidade pedido, qual é a compra dos produtos por um cliente.
 * @author thiago leite
 **/
public class Pedido {
    /**
     * Atributo de identificação do pedido
     **/
    private String codigo;

    /**
     * Cliente a qual o pedido se refere
     **/
    private Cliente cliente;

    /**
     * Lista onde guardaremos nossos produtos
     **/
    private List<Produto> produtos;

    /**
     * Atributo que contabiliza total dos produtos
     **/
    private double total;

    /**
     * Método construtor
     **/
    public Pedido() {
        this.produtos= new ArrayList<>();
    }

    /**
     * Métodos Utilitários
     **/
    @Override
    public String toString() {
        return "Pedido{" +
                "codigo='" + codigo + '\'' +
                ", cliente=" + cliente +
                ", produtos=" + getProdutosComprados() +
                ", total=" + total +
                '}';
    }

    /**
     * Métodos Acessores
     **/
    public String getProdutosComprados(){
        StringBuilder produtos= new StringBuilder();
        produtos.append("[");
        for ( Produto produto: getProdutos()){
            produtos.append(produtos.toString());
            produtos.append("Qtd.:");
            produtos.append(produto.getQuantidade());
            produtos.append(" ");
        }
        produtos.append("]");

        return produtos.toString();
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
