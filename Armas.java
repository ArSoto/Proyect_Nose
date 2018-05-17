public class Armas {
    private int balas;
    private String tipo;
    private boolean mostrar;
    private String boton;


    public Armas(int balas, String tipo, boolean mostrar, String boton) {
        this.balas = balas;
        this.tipo = tipo;
        this.mostrar = mostrar;
        this.boton = boton;

    }

    public void setBalas(int balas) {
        this.balas = balas;
    }

    public String getBoton() {
        return boton;
    }

    public int getBalas() {
        return balas;
    }

    public String getTipo() {
        return tipo;
    }


    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }
}
