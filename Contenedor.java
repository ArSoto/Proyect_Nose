import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import javax.swing.Timer;

public class Contenedor {
    private int cambiar;
    private Random random = new Random();
    private Scanner scanner= new Scanner(System.in);


    private String boton;
    private int contBoton =0;
    private Armas armas;
    private int tempInt;
    private String tempString;
    private int[] contadorArmas = {0,0};
    private ArrayList<Armas> armasArrayListDer, armasArrayListIzq;
    private boolean anterior;

///////////////////////////////////////////////////////////////////
    public void imprimirControl(){ //Imprime controles de los robots
        switch (panel.getEstado()){
            case "Fighter":{
                System.out.println("Para controlar modo Fighter utilice las siguientes teclas:");
                System.out.println("\t(a) Giro derecha \n\t(d) Giro izquerda \n\t(w) Aumentar altura \n\t(s) Disminuye altura" +
                        "\n\t(i) Acelerar \n\t(k) Desacelerar \n\t(P) Disparar");
                break;
            }
            case("Battloid"):{
                System.out.println("Para controlar modo Battloid utilice las siguientes teclas:");
                System.out.println("(A): Avanzar con pierna izquierda \n\t(D): Avanzar con pierna derecha \n\t(R): Retroceder \n\t(P): Disparar (P de pium)" +
                        "\nPara retroceder debe presionar (R), enter y luego la tecla de la pierna con la cual desea retroceder\n"+
                        "\nADVERTENCIA: NO puedes avanzar dos veces con la misma pierna\n");
                break;

            }
            case("Gerwalk"):{
                System.out.println("Para controlar modo Gerwalk utilice las siguientes teclas:");

                break;
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }

    public void verificarCambio(){ //Comprueba las condiciones antes de cambiar de estado
        //BATTLOID --> GERWALK (Altura = 0 metros)
        //GERWALK --> BATTLOID (Altura < 200 metros || Altura = 0 metros)
        //GERWALK --> FIGHTER (Siempre que este volando)
        //FIGHTER --> GERWALK (Cualquier momento)
        System.out.println("¿Cambio de modo? (C: Cambiar; N: No cambiar)");
        boton = scanner.nextLine();
        switch (boton){
            case("C"):{
                if("Fighter".equals(panel.getEstado())){
                    System.out.println("");
                }
            }
        }





    }


////////////////////////////////////////////////////////////////////////////////////////////////////////ARTUROOOOOOOOOOOOOOOOOOOOOO
    //asigna un array de Clase arma a las extremidades que puden utilizarlas

    private void asignarArmas(){

        b_der.setArma(setArmasArray());
        b_izq.setArma(setArmasArray());

        a_der.setArmas(setArmasArray());
        a_izq.setArmas(setArmasArray());

    }
// Cambio de armas de las alas a los brazos
    public void actualizarArmasAB(){

        b_der.setArma(a_der.getArmas());
        b_izq.setArma(a_izq.getArmas());

    }
// cambio de Armas de los Brazos a las alas
    public void actualizarArmasBA(){
        a_der.setArmas(b_der.getArma());
        a_izq.setArmas(b_der.getArma());


    }
// comprueba que el arma utilizada contiene municiones y de ser asi dispara
    private void Disparar(String Estado, Armas arma){

        if (!arma.isMostrar()){
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

    private void armaBattloid (ArrayList<Armas> izq, ArrayList<Armas> der){
        
        if (contadorArmas[0] == 0){
            System.out.println("El Robot no posee ningun canion que pueda utilizar");
            return;
        }

        imprimirCaniones(der, izq);

        switch (scanner.nextLine()){

            case "u":{

            }
        }

    }


    private Armas crearArmas(){           //Crea las armas de forma aleatoria, tanto el tipo como la cantidad de municiones

            tempInt=random.nextInt(2)+1;
            String botones[] ={"u","i","o","p"}; // Servira para asignar boton a cada arma

            if (tempInt==1) {

                tempString = "Canion";
                contadorArmas[0]++;
            }
            else{
                tempString="Laser";
                contadorArmas[1]++;
            }


            armas= new Armas(random.nextInt(10)+1,tempString, true, botones[contBoton++]);

            return armas;
    }

    public ArrayList<Armas> setArmasArray() { //Asignar lista de armas a cada lado del robot
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

    public void imprimirCaniones(ArrayList<Armas> arrayListDer, ArrayList<Armas> arrayListIzq){
        Iterator<Armas> iteArrayListDer = arrayListDer.iterator();
        Iterator<Armas> iteratorListIzq = arrayListIzq.iterator();
        System.out.println("Elija el canion que desea ocupar en modo Battloid");

        while (iteArrayListDer.hasNext()){
            Armas armas = iteArrayListDer.next();
            if(armas.getTipo().equals("Canion"))
                System.out.println("[" + armas.getBoton() + "] Canion con "+ armas.getBalas());
        }

        while (iteArrayListDer.hasNext()){
            Armas armas = iteArrayListDer.next();
            if(armas.getTipo().equals("Canion"))
                System.out.println("[" + armas.getBoton() + "] Canion con "+ armas.getBalas());
        }
    }

/////////////////////////////////////////////////////////////////ARTUUUUUUUUUUUUUUUURO
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

    int estamina = random.nextInt(100)+1;



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
                break;
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
                break;
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
                break;
            }

        }
        return this.cambiar;
    }

    public void getEstado (){
        System.out.println("Estado: " + panel.getEstado() +
                "\n \t Altura: "+ panel.getAltura() +
                " metros\n \t Largo pista: "+ panel.getL_pista() +
                " metros \n \t Velocidad: " + panel.getVelocidad() +
                " km/h \n \t Posicion: " + panel.getPos_robot() + " metros\n---------------------------------------------------------------------------------------------------------");
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



    public boolean movimientosAire() {


        boton = scanner.nextLine();
        switch (boton) {
            case "a":
                System.out.println("Giro a la izquierda");
                break;

            case "d":
                System.out.println("Giro a la derecha");
                break;

            case "w": {
                if (panel.getAltura() < 10000) {
                    panel.setAltura(panel.getAltura() + 100);
                    System.out.println("El avion aumenta su altura a " + panel.getAltura());
                    break;
                } else System.out.println("El avion ya esta en su altura maxima de vuelo");
                break;
            }

            case "s": {
                if (panel.getAltura() >= 21) {
                    panel.setAltura(panel.getAltura() - 20);
                    System.out.println("El avion disminuye su altura a " + panel.getAltura());
                    break;
                } else System.out.println("El avion ya esta en su altura minima de vuelo");
                break;

            }

            case "i":
                if (panel.getVelocidad() <= 750) {
                    panel.setVelocidad(panel.getVelocidad() + 100);
                    System.out.println("Velocidad" + panel.getVelocidad());
                    break;
                } else System.out.println("Imposible  acelerar, ya ha alcanzado el maximo");
                break;

            case "k":
                if (panel.getVelocidad() >= 100) {
                    panel.setVelocidad(panel.getVelocidad() - 100);
                    System.out.println("Velocidad" + panel.getVelocidad());
                    break;
                } else {
                    System.out.println("Imposible  desacelerar, ya ha alcanzado el minimo");
                    break;
                }
            case "p":
                return false;

        }

        return true;
    }

    int avanzar = 5;
    int retroceder = 1;
    Timer timerCorrer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            estamina -= 10;
            System.out.println("Battloid corriendo...\nEstamina disponible: " + estamina);
        }
    });
    public boolean movimientosSuelo() {
        //SOLO SE MUEVE HACIA ADELANTE
        boton = scanner.nextLine();

        switch (boton) {
            //Avanzar
            case "r":{ retroceder = -1; break;}
            case "d": { //PIERNA DERECHA
                if (p_der.isAvanzar()) {
                    System.out.println("No puede avanzar dos veces con la misma pierna");
                }
                if (!p_der.isAvanzar()) {
                    System.out.println("Avanza 5 metros con la pierna derecha");
                    panel.setPos_robot(panel.getPos_robot() + avanzar*retroceder);
                    System.out.println(panel.getPos_robot());

                    p_der.setAvanzar(true);
                    p_izq.setAvanzar(false);
                }
                System.out.println("\nADVERTENCIA: para el siguiente paso recuerda que no puedes avanzar dos veces con la misma pierna");
                System.out.println("-----------------------------------------------------------------------\n");
                break;
            }
            case "a": { //PIERNA IZQUIERDA

                if (p_izq.isAvanzar()) {
                    System.out.println("No puede avanzar dos veces con la misma pierna");
                }
                if (!p_izq.isAvanzar()) {
                    System.out.println("Avanza 5 metros con la pierna izquierda");
                    panel.setPos_robot(panel.getPos_robot() + avanzar*retroceder);
                    System.out.println(panel.getPos_robot());
                    p_izq.setAvanzar(true);
                    p_der.setAvanzar(false);
                }
                System.out.println("\nADVERTENCIA: para el siguiente paso recuerda que no puedes avanzar dos veces con la misma pierna");
                System.out.println("-----------------------------------------------------------------------\n");
                break;
            }
            case "k":{//Modo correr
                System.out.println("Modo Correr Activado, \nEstamina disponible: " + estamina + " %");
                while (estamina > 0) {
                        timerCorrer.start();
                        if (estamina <=0){
                            timerCorrer.stop();
                        }
                    if (estamina < 0) {
                        System.out.println("Battloid no puede correr. Estamina baja.");
                    }
                }
                ////////////////////ARTURO: DESACTIVAR ARMAS!!!!!
            }

            default: {
                System.out.println("ERROR al leer tecla ingresada, vuelva a presionar");
                System.out.println("\n-----------------------------------------------------------------------\n");
                break;
            }
        }
        return true;
    }



}
