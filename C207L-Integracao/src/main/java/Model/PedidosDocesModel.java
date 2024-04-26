package Model;

public class PedidosDocesModel {
    String doces_nomeDoce, doces_receitas_nomeReceita;
    int id;
    public PedidosDocesModel( int Pedidos_idPedidos, String doces_nomeDoce, String doces_Receitas_NomeReceita) {
        this.doces_nomeDoce = doces_nomeDoce;
        this.doces_receitas_nomeReceita = doces_Receitas_NomeReceita;
        this.id = Pedidos_idPedidos;
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
