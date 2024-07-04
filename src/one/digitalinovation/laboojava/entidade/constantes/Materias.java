package one.digitalinovation.laboojava.entidade.constantes;

/**
 *  Quantidades de materias que os cadernos podem ter
 *  @author Rafael Vieira
 **/
public enum Materias {

    M2(2),

    M5(5),

    M10(15);

    private double fator;

    /**
     * Construtor
     * @param fator Valor por tipo que influencia no cálculo do frete.
     **/
    Materias(double fator){
        this.fator= fator;
    }

    public double getFator(){
        return fator;
    }
}
