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
    Brazos b_der = new Brazos( 0,0,false);
    Brazos b_izq = new Brazos( 0,0,false);
    Alas a_der = new Alas(true);
    Alas a_izq = new Alas(true);
    Piernas p_der = new Piernas(false, false,false);
    Piernas p_izq = new Piernas(false, false,false);
    Armas laser= new Armas(10,"laser");
    Piernas anterior = new Piernas(true, false, false); //Va a tomar el mismo valor que boton al caminar

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
        System.out.println("Estado: " + panel.getEstado() +
                "\n \t Altura: "+ panel.getAltura() +
                " metros\n \t Largo pista: "+ panel.getL_pista() +
                " metros \n \t Velocidad: " + panel.getVelocidad() +
                " km/h \n \t Posicion: " + panel.getPos_robot() + " metros");
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


    public void Despegar(){ //?????????????????????????????????????????????
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

    int cont = 0;

    public boolean movimientosSuelo(int cont) {
        //SOLO SE MUEVE HACIA ADELANTE
        if (cont == 0) {
            System.out.println("MENU DE OPCIONES CAMINAR EN MODO BATTLOID: Para avanzar presione cualquiera de las siguientes letras" +
                    "\n\t(A): Avanzar con pierna izquierda \n\t(D): Avanzar con pierna derecha " +
                    "\nADVERTENCIA: recuerda que no puedes avanzar dos veces con la misma pierna\n");
            cont +=1;
        }
        boton = scanner.nextLine();
        switch (boton) {
            //Avanzar
            case "a": { //PIERNA DERECHA
                p_der.setAvanzar(true);
                p_izq.setAvanzar(false);
                if (anterior == p_der) {
                    System.out.println("No puede avanzar dos veces con la misma pierna");
                }
                if (p_der.isAvanzar() == true && anterior != p_der) {
                    System.out.println("Pierna derecha");
                    panel.setPos_robot(panel.getPos_robot() + 10);
                }
                anterior = p_der;
                System.out.println("\nADVERTENCIA: para el siguiente paso recuerda que no puedes avanzar dos veces con la misma pierna\n");
                System.out.println("\n-----------------------------------------------------------------------\n");
                break;
            }
            case "d": { //PIERNA IZQUIERDA
                p_izq.setAvanzar(true);
                p_der.setAvanzar(false);
                if (anterior == p_izq) {
                    System.out.println("No puede avanzar dos veces con la misma pierna");
                }
                if (p_izq.isAvanzar() == true && anterior != p_izq) {
                    System.out.println("Pierna izquierda");
                    panel.setPos_robot(panel.getPos_robot() + 10);
                }
                System.out.println("\nADVERTENCIA: para el siguiente paso recuerda que no puedes avanzar dos veces con la misma pierna\n");
                anterior = p_izq;
                System.out.println("\n-----------------------------------------------------------------------\n");
                break;
            }
            default: {
                System.out.println("ERROR al leer tecla ingresada, vuelva a presionar");
                System.out.println("\n-----------------------------------------------------------------------\n");
                break;
            }
        }
        getEstado();
        return true;
    }
}






