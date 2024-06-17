package Model;

public class PedidosDocesModel {
    private String doces_nomeDoce, doces_receitas_nomeReceita;
    private int id, doces_Quantidade;
    public PedidosDocesModel( int Pedidos_idPedidos, String doces_nomeDoce, String doces_Receitas_NomeReceita, int doces_Quantidade) {
        this.doces_nomeDoce = doces_nomeDoce;
        this.doces_receitas_nomeReceita = doces_Receitas_NomeReceita;
        this.id = Pedidos_idPedidos;
        this.doces_Quantidade = doces_Quantidade;
    }


    public String getDoces_nomeDoce(){
        return this.doces_nomeDoce;
    }

    public String getDoces_Receitas_NomeReceita(){
        return this.doces_receitas_nomeReceita;
    }

    public int getPedidos_idPedidos(){
        return this.id;
    }
}
