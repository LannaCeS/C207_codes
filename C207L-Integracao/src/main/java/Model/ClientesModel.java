package Model;

public class ClientesModel {

    private String CPF, nomeCliente, Enderecos_Endereco;
    public ClientesModel(String CPF, String nomeCliente, String Enderecos_Endereco) {
        this.CPF = CPF;
        this.nomeCliente = nomeCliente;
        this.Enderecos_Endereco = Enderecos_Endereco;
    }
    public ClientesModel(){}

    public String getCPF() {
        return this.CPF;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getEndereco() {
        return this.Enderecos_Endereco;
    }
}
