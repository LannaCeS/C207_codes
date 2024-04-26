package Model;

public class ReceitasModel {
    String nomeReceita;
    boolean ovo, farinha, fermento, leite, acucar, cacau, cenoura, morango, abacaxi, chantilly;
    public ReceitasModel(String nomeReceita, boolean ovo, boolean farinha, boolean fermento, boolean leite, boolean acucar, boolean cacau, boolean cenoura, boolean morango, boolean abacaxi, boolean chantilly) {
        this.nomeReceita = nomeReceita;
        this.ovo = ovo;
        this.farinha = farinha;
        this.fermento = fermento;
        this.leite = leite;
        this.acucar = acucar;
        this.cacau = cacau;
        this.cenoura = cenoura;
        this.morango = morango;
        this.abacaxi = abacaxi;
        this.chantilly = chantilly;
    }


    public String getNomeReceita() {
        return this.nomeReceita;
    }

    public Boolean getOvo(){
        return this.ovo;
    }

    public Boolean getFarinha(){
        return this.farinha;
    }

    public Boolean getFermento(){
        return this.fermento;
    }

    public Boolean getLeite(){
        return this.leite;
    }

    public Boolean getAcucar(){
        return this.acucar;
    }

    public Boolean getCacau(){
        return this.cacau;
    }

    public Boolean getCenoura(){
        return this.cenoura;
    }

    public Boolean getMorango(){
        return this.morango;
    }

    public Boolean getAbacaxi(){
        return this.abacaxi;
    }

    public Boolean getChantilly(){
        return this.chantilly;
    }
}
