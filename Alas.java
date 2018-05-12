import java.util.ArrayList;

public class Alas {


    private boolean Mostrar_alas;
    private ArrayList<Armas> armas;


    public Alas(boolean mostrar_alas, ArrayList<Armas> Armamento) {

        Mostrar_alas = mostrar_alas;
        armas = Armamento;

    }

    public boolean isMostrar_alas() {
        return Mostrar_alas;
    }

    public void setMostrar_alas(boolean mostrar_alas) {
        Mostrar_alas = mostrar_alas;
    }

    public ArrayList<Armas> getArmas() {
        return armas;
    }

    public void setArmas(ArrayList<Armas> armas) {
        this.armas = armas;
    }
}
