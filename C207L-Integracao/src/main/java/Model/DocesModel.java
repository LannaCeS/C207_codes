package Model;

public class DocesModel {
    private String nomeDoce;
    private int quantidade;
    private String receitas_NomeReceita;

    public DocesModel(Integer quantidade, String nomeDoce, String receitas_NomeReceita) {
        this.quantidade = quantidade;
        this.nomeDoce = nomeDoce;
        this.receitas_NomeReceita = receitas_NomeReceita;
    }

    public Integer getQuantidade() {
        return this.quantidade;
    }

    public String getNomeDoce() {
        return nomeDoce;
    }

    public String getReceitas_NomeReceita() {
        return this.receitas_NomeReceita;
    }
}
