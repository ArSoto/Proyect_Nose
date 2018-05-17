import java.util.ArrayList;

import java.util.ArrayList;

public class Brazos {


    private int SubirBajar_Brazo;
    private int DerIzq_Brazo;
    private boolean Mostrar_brazo;
    private ArrayList<Armas> arma;


    public Brazos(int subirBajar_Brazo, int derIzq_Brazo, boolean mostrar_brazo, ArrayList<Armas> armamento) {

        SubirBajar_Brazo = subirBajar_Brazo;
        DerIzq_Brazo = derIzq_Brazo;
        Mostrar_brazo = mostrar_brazo;
        arma= armamento;
    }



    public int getSubirBajar_Brazo() {
        return SubirBajar_Brazo;
    }

    public void setSubirBajar_Brazo(int subirBajar_Brazo) {
        SubirBajar_Brazo = subirBajar_Brazo;
    }

    public int getDerIzq_Brazo() {
        return DerIzq_Brazo;
    }

    public void setDerIzq_Brazo(int derIzq_Brazo) {
        DerIzq_Brazo = derIzq_Brazo;
    }

    public boolean isMostrar_brazo() {
        return Mostrar_brazo;
    }

    public void setMostrar_brazo(boolean mostrar_brazo) {
        Mostrar_brazo = mostrar_brazo;
    }

    public ArrayList<Armas> getArma() {
        return arma;
    }

    public void setArma(ArrayList<Armas> arma) {
        this.arma = arma;
    }
}