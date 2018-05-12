import java.util.Random;
import java.util.Scanner;

public class Contenedor {
    private int cambiar;
    Random random = new Random();
    Scanner scanner= new Scanner(System.in);

    String boton;


    public Contenedor(int cambiar) {
        this.cambiar = cambiar;
    }

    public int getCambiar() {
        return cambiar;
    }


    Panel_de_Control panel = new Panel_de_Control("Fighter",0,0,0);
    Cabeza c = new Cabeza(false);
    Brazos b_der = new Brazos( 0,0,false); // 0: Estado fighter (ocultos)
    Brazos b_izq = new Brazos( 0,0,false);
    Alas a_der = new Alas(true);
    Alas a_izq = new Alas(true);
    Piernas p_der = new Piernas(false, false,false);
    Piernas p_izq = new Piernas(false, false,false);
    Armas laser= new Armas(10,"laser");

    public Panel_de_Control getPanel() {
        return panel;
    }

    public void setPanel(Panel_de_Control panel) {
        this.panel = panel;
    }

    public int setCambiar(int cambiar) {

        switch (cambiar){

            //Fighter: Avion
            case 1: {
                c.setMostrar_Cabeza(false);
                b_der.setMostrar_brazo(false);
                b_izq.setMostrar_brazo(false);
                a_der.setMostrar_alas(true);
                a_izq.setMostrar_alas(true);
                p_der.setMostrar_piernas(false);
                p_izq.setMostrar_piernas(false);
                panel.setEstado("Fighter");
            }
            //Battloid: Humanoide
            case 2:{
                c.setMostrar_Cabeza(true);
                b_der.setMostrar_brazo(true);
                b_izq.setMostrar_brazo(true);
                a_der.setMostrar_alas(false);
                a_izq.setMostrar_alas(false);
                p_der.setMostrar_piernas(true);
                p_izq.setMostrar_piernas(true);
                panel.setEstado("Battloid");
            }
            //Gerwalk: Pajaro
            case 3:{
                c.setMostrar_Cabeza(false);
                b_der.setMostrar_brazo(true);
                b_izq.setMostrar_brazo(true);
                a_der.setMostrar_alas(true);
                a_izq.setMostrar_alas(true);
                p_der.setMostrar_piernas(true);
                p_izq.setMostrar_piernas(true);
                panel.setEstado("Gerwalk");
            }

        }
        return this.cambiar;
    }

    public void getEstado (){
        System.out.println("Estado: " + panel.getEstado() +"\n \t Altura: "+ panel.getAltura() + " metros\n \t Largo pista: "+ panel.getL_pista() + " metros \n \t Velocidad inicial: 0 km/h");

    }

    public int setDespegarModoAvion(int velocidad, int altura, int l_pista ){

        if ((panel.getVelocidad() > 350)&&(altura == 0)&& ((getCambiar() == 1)||(getCambiar()==3))){
            System.out.println("Esta en condiciones de volar");
        }
        else{
            System.out.println("No cumple condiciones para iniciar vuelo en modo Fighter");
        }
        return 1;
    }


    public int setMovimiento(){ //FALTA ARREGLAR COMO INGRESARLO
        boolean mover = true;
        System.out.println("Ingrese movimiento:");
        boton = scanner.nextLine();

        while(mover == true){
            if("A".equals(boton)){
                panel.setPos_robot(panel.getPos_robot() + 100);
            }
            mover = false;
        }
        System.out.println("Posicion en x: " + panel.getPos_robot());
        return panel.getPos_robot();
    }

    public void Despegar(){
        panel.setVelocidad(350);
        panel.setAltura(100);

    }

    public boolean movimientosAire()
    {

        System.out.println("(a) giro derecha \t (d) giro izquerda \t (w) aumentar altura\t (s) disminuye altura \n" +
                "(i) acelerar \t (k) desacelerar \t (j) disparar" );

        boton=scanner.nextLine();

        switch (boton){


            case "a": System.out.println("giro a la izquerda"); break;

            case "d": System.out.println("giro a la derecha");break;

            case "w":{
                if(panel.getAltura()<10000)
                {
                    panel.setAltura(panel.getAltura()+100);
                    System.out.println("El avion aumenta su altura a " + panel.getAltura());break;
                }
                else System.out.println("El avion ya esta en su altura maxima de vuelo");break;
            }

            case "s": {
                if (panel.getAltura() >= 21) {
                    panel.setAltura(panel.getAltura() -20);
                    System.out.println("El avion disminuye su altura a " + panel.getAltura());
                    break;
                } else System.out.println("El avion ya esta en su altura minima de vuelo");
                break;

                }

            case "i":
                if (panel.getVelocidad() <= 750) {
                panel.setVelocidad(panel.getVelocidad()+100);
                System.out.println("velocidad"+ panel.getVelocidad()); break;
                }
                else System.out.println("Imposible  acelerar, ya ha alcanzado el maximo");break;

            case "k":
                if (panel.getVelocidad() >=  100) {
                    panel.setVelocidad(panel.getVelocidad()-100);
                    System.out.println("velocidad"+ panel.getVelocidad()); break;
                }
                else {System.out.println("Imposible  desacelerar, ya ha alcanzado el minimo");break;}

            case "j":laser.setLaser(panel.getEstado());break;

            case "p":return false;

        }

        return true;


    }


}
