package Model;

public class PedidosModel {

    private String endereco, cpf;
    private int id;
    public PedidosModel( int Pedidos_idPedidos, String Clientes_CPF, String Clientes_Enderecos_Endereco) {
        this.endereco = Clientes_Enderecos_Endereco;
        this.cpf = Clientes_CPF;
        this.id = Pedidos_idPedidos;
    }


    public String getClientes_Enderecos_Endereco() {
        return this.endereco;
    }

    public String getClientes_CPF(){
        return this.cpf;
    }

    public int getPedidos_idPedidos(){
        return this.id;
    }
}
