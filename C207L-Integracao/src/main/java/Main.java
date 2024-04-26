import DAO.Doces;
import DAO.Receitas;
import Model.DocesModel;
import Model.ReceitasModel;

public class Main {
    public static void main(String[] args) {
        Doces doces = new Doces();

        Receitas receitas = new Receitas();

        ReceitasModel r1 = new ReceitasModel("bolo de cenoura", true, true, true,true, true, false, true, false,false, false);
        ReceitasModel r2 = new ReceitasModel("sonho", true, true, true, true, true, false, false, false, false, true);
        ReceitasModel r3 = new ReceitasModel("bolo de morango", true, true, true, true, true, false, false, true, false, true);

        receitas.insertReceita(r1);
        receitas.insertReceita(r2);
        receitas.insertReceita(r3);

        DocesModel d1 = new DocesModel(8,"bolo de cenoura","bolo de cenoura");
        DocesModel d2 = new DocesModel( 1, "sonho", "sonho");

        doces.insertDoces(d1);
        doces.insertDoces(d2);

        doces.selectDoce();

        doces.updateDoces("bolo de morango", "bolo de morango", "bolo de cenoura");

        doces.selectDoce();

        doces.deleteDoces("sonho");
    }
}