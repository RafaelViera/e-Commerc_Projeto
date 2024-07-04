package one.digitalinovation.laboojava.entidade;

import one.digitalinovation.laboojava.entidade.constantes.Materias;

/**
 * Classe que representa um caderno, especialização da classe Produto
 * @author Rafael Vieira
 **/
public class Caderno extends Produto {
    /**
     *  Quantidade de matérias do caderno
     **/
    private Materias materias;

    /**
     * Método abstrato da Classe Mãe implementado
     **/
    @Override
    public double calcularFrete() {
        return ((this.getPreco()*this.getQuantidade()) + materias.getFator());
    }

    /**
     *  Métodos Acessores
     **/
    public Materias getMaterias() {
        return materias;
    }

    public void setMaterias(Materias materias) {
        this.materias = materias;
    }

    /**
     * Método Utilitários
     **/
    @Override
    public String toString() {
        return "Caderno{" +
                "materias=" + getMaterias() + '\'' +
                "código=" + getCodigo() + '\'' +
                "preco=" + getPreco() + '\'' +
                '}';
    }
}
