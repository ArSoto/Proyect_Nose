import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Contenedor {
    private int cambiar;
    private Random random = new Random();
    private Scanner scanner= new Scanner(System.in);


    private String boton;
    private Armas armas;
    private int tempInt;
    private String tempString;
    private ArrayList<Armas> armasArrayListDer, armasArrayListIzq;

///////////////////////////////////////////////////////////////////

    private void asignarArmas(){

        b_der.setArma(setArmasArray());
        b_izq.setArma(setArmasArray());

        a_der.setArmas(setArmasArray());
        a_der.setArmas(setArmasArray());

    }

    public void actualizarArmasAB(){

        b_der.setArma(a_der.getArmas());
        b_izq.setArma(a_izq.getArmas());

    }

    public void actualizarArmasBA(){
        a_der.setArmas(b_der.getArma());
        a_izq.setArmas(b_der.getArma());


    }

    private void Disparar(String Estado, Armas arma){

        if (arma.getTipo().equals("Vacio")){
            System.out.println("No posee arma asignada a ese boton");
            return ;

        }

        if (arma.getBalas() < 0){
            System.out.println("El arma " + arma.getTipo() + " no tiene municiones ");
            return;

        }


        arma.setBalas(arma.getBalas()-1);  //resta una municion
        System.out.println("Disparando...  municiones restante  " + arma.getBalas());

    }



    private Armas crearArmas(){           //Crea las armas de forma aleatoria, tanto el tipo como la cantidad de municiones

            tempInt=random.nextInt(2)+1;

            if (tempInt==1)
                tempString= "Canion";
            else
                tempString="Laser";

            armas= new Armas(random.nextInt(10)+1,tempString);

            return armas;
    }

    public ArrayList<Armas> setArmasArray() {
        ArrayList<Armas> armasArrayList = new ArrayList<Armas>();

        tempInt = random.nextInt(2);
        for (int i = 0; i < tempInt; i++){
            armasArrayList.add(crearArmas());

        }
        return armasArrayList;

    }

    public void imprimirArmas(ArrayList<Armas> arrayList, String lado){

        System.out.println("prueba de Armas");
        Iterator<Armas> iteArrayList = arrayList.iterator();
        while (iteArrayList.hasNext()){
            Armas armas = iteArrayList.next();
            System.out.println(" Lado \n"+ lado+ " Tipo " +armas.getTipo()+ "Municiones  "+armas.getBalas() );
        }
    }


/////////////////////////////////////////////////////////////////
    public Contenedor(int cambiar) {
        this.cambiar = cambiar;
    }

    public int getCambiar() {
        return cambiar;
    }



    Panel_de_Control panel = new Panel_de_Control("Fighter",0,0,0);
    Cabeza c = new Cabeza(false);
    Brazos b_der = new Brazos( 0,0,false, armasArrayListIzq); // 0: Estado fighter (ocultos)
    Brazos b_izq = new Brazos( 0,0,false, armasArrayListDer);
    Alas a_der = new Alas(true, armasArrayListDer);
    Alas a_izq = new Alas(true, armasArrayListIzq);
    Piernas p_der = new Piernas(false, false,false);
    Piernas p_izq = new Piernas(false, false,false);




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

       //

            case "p":return false;

        }

        return true;


    }


}
