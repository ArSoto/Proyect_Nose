public class Piernas {

    private boolean Mostrar_piernas;
    private boolean Avanzar;


    public Piernas(boolean mostrar_piernas, boolean avanzar) {
        Mostrar_piernas = mostrar_piernas;
        Avanzar = avanzar;

    }

    public boolean isMostrar_piernas() {
        return Mostrar_piernas;
    }

    public void setMostrar_piernas(boolean mostrar_piernas) {
        Mostrar_piernas = mostrar_piernas;
    }

    public boolean isAvanzar() {
        return Avanzar;
    }

    public void setAvanzar(boolean avanzar) {
        Avanzar = avanzar;
    }

}
