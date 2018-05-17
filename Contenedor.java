import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import javax.swing.Timer;

public class Contenedor {
    int cambiare;
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

    int estamina = random.nextInt(100)+1;

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

    public void verificarCambio(){ //Comprueba las condiciones antes de cambiar de estado????
        //BATTLOID --> GERWALK (Altura = 0 metros)
        //GERWALK --> BATTLOID (Altura < 200 metros || Altura = 0 metros)
        //GERWALK --> FIGHTER (Siempre que este volando)
        //FIGHTER --> GERWALK (Cualquier momento)
        System.out.println("¿Cambio de modo? (C: Cambiar; N: No cambiar)");
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////ARTUROOOOOOOOOOOOOOOOOOOOOO
    //asigna un array de Clase arma a las extremidades que puden utilizarlas

    private void asignarArmas(){

        b_der.setArma(setArmasArray());
        b_izq.setArma(setArmasArray());

        a_izq.setArmas(b_izq.getArma());
        a_der.setArmas(b_der.getArma());


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
    private Armas disparar(Armas arma){

        if (!arma.isMostrar()){
            System.out.println("No posee arma asignada a ese boton");
            return  arma;

        }

        if (arma.getBalas() < 0){
            System.out.println("El arma " + arma.getTipo() + " no tiene municiones ");
            return arma;

        }


        arma.setBalas(arma.getBalas()-1);  //resta una municion
        System.out.println("\nDisparando...  municiones restante \n " + arma.getBalas());

        return arma;

    }

    public void armaBattloid (ArrayList<Armas> izq, ArrayList<Armas> der){
        
        String letra;
        if (contadorArmas[0] == 0){
            System.out.println("El Robot no posee ningun cañon que pueda utilizar");
            desactivarArmas(izq, der, null);
            return;
        }

        while (true) {
            imprimirCaniones(der, izq);
            letra = scanner.nextLine();

            for (Armas i : izq) {
                if (i.getBoton().equals(letra)) {
                    if (i.getTipo().equals("Canion")) desactivarArmas(izq, der, letra);return;

                }

            }
            System.out.println("Tecla no valida");
        }

    }


    private Armas crearArmas(){           //Crea las armas de forma aleatoria, tanto el tipo como la cantidad de municiones

        int tempInt =random.nextInt(2)+1;
            String botones[] ={"u","i","o","p"}; // Servira para asignar boton a cada arma

            if (tempInt==1) {

                tempString = "Canion";
                contadorArmas[0]++;
            }
            else
                {
                tempString="Laser";
                contadorArmas[1]++;
            }

        armas= new Armas(random.nextInt(10)+1,tempString, true, botones[contBoton]);
        contBoton++;

            return armas;
    }

    //Crea los arreglos para las armas

    private ArrayList<Armas> setArmasArray() {
        ArrayList<Armas> armasArrayList = new ArrayList<>();
        int tempInt;

        tempInt =random.nextInt(2)+1;
        for (int i = 0; i < tempInt; i++){
            armasArrayList.add(crearArmas());

        }
        return armasArrayList;

    }


    public void imprimirArmas(ArrayList<Armas> arrayList){

        Iterator<Armas> iteArrayList = arrayList.iterator();
        while (iteArrayList.hasNext()){
            Armas armas = iteArrayList.next();
            System.out.print("("+ armas.getBoton() + ")" +" Tipo:" +armas.getTipo()+ " Balas:  "+armas.getBalas() + "\t");
        }
    }

    private void imprimirCaniones(ArrayList<Armas> arrayListDer, ArrayList<Armas> arrayListIzq){


        Iterator<Armas> iteArrayListDer = arrayListDer.iterator();
        Iterator<Armas> iteArrayListIzq = arrayListIzq.iterator();
        System.out.println("Elija el canion que desea ocupar en modo Battloid");

        while (iteArrayListDer.hasNext()){
            Armas armas = iteArrayListDer.next();


            if(armas.getTipo().equals("Canion"))
                System.out.println("[" + armas.getBoton() + "] Canion con "+ armas.getBalas());

        }

        while (iteArrayListIzq.hasNext()){
            Armas armas = iteArrayListIzq.next();


            if(armas.getTipo().equals("Canion"))
                System.out.println("[" + armas.getBoton() + "] Canion con "+ armas.getBalas());

        }

    }
    private void desactivarArmas(ArrayList<Armas> arrayListDer, ArrayList<Armas> arrayListIzq, String letra){ //solo deja activa l

        if(arrayListDer.get(0).getTipo().equals(letra)) arrayListDer.get(0).setMostrar(false);
        if(arrayListDer.get(1).getTipo().equals(letra)) arrayListDer.get(1).setMostrar(false);
        if(arrayListIzq.get(0).getTipo().equals(letra)) arrayListIzq.get(0).setMostrar(false);
        if(arrayListIzq.get(1).getTipo().equals(letra)) arrayListIzq.get(1).setMostrar(false);

        }

    private void activarArmas(ArrayList<Armas> arrayListDer, ArrayList<Armas> arrayListIzq){ //activa Armas para usarlas en modo avion o pajaro

       arrayListDer.get(0).setMostrar(true);
       arrayListDer.get(1).setMostrar(true);
       arrayListIzq.get(0).setMostrar(true);
       arrayListIzq.get(1).setMostrar(true);

    }


/////////////////////////////////////////////////////////////////
    public Contenedor(int cambiar) {
        this.cambiar = cambiar;
    }

    public int getCambiar() {
        return cambiar;
    }


    Panel_de_Control panel = new Panel_de_Control("Fighter", 0);
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

    public int iniciarPista() {

        Scanner scanner= new Scanner(System.in);
        int eleccion;
        do {
            System.out.println("MENU DE OPCIONES: \n");
            System.out.println("\t (1)Iniciar pista ");
            eleccion = scanner.nextInt();
            System.out.println("---------------------------------------------------------------------------------------");
            if(eleccion!=1) System.out.println("|||||No es posible iniciar  sin pista||||");
        }while (eleccion !=1);
        return 1;
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
        return cambiar;
    }

    public void getEstado (){
        System.out.println("ESTADO DEL ROBOT" +
                "\n \t Modo: " + panel.getEstado() +
                "\n \t Altura: "+ panel.getAltura() +
                " metros\n \t Largo pista: "+ panel.getL_pista() +
                " metros \n \t Velocidad: " + panel.getVelocidad() +
                " km/h \n \t Posicion: " + panel.getPos_robot() + " metros\n" +
                "-----------------------------------------------------------------------------------------");
    }

    public int setDespegarModoAvion(int velocidad, int altura, int l_pista ){


        if ((panel.getVelocidad() > 350) && (altura == 0) && ((getCambiar() == 1) || (getCambiar() == 3)))
        {
            System.out.println(" |Esta en condiciones de volar|\n");
            return 0;
        }
        else{
            System.out.println("No cumple condiciones para iniciar vuelo en modo Fighter, presione nuevamente 1.");
            Scanner scanner = new Scanner(System.in);
            int eleccion;
            eleccion = scanner.nextInt();
        return 1;
    }

    }


    public void condiciones_Despegar() {
        panel.setVelocidad(random.nextInt(450)+300);
        panel.setPos_robot(random.nextInt(panel.getL_pista()));


    }

    public int Despegar() {

        int p=0;
        while (p==0) {

             System.out.println("¿Desea despegar? s/n");
             boton = scanner.nextLine();

             if (boton.equals("s")) {
                 panel.setAltura(random.nextInt(950) + 50);
                cambiare=1;
                 p=1;
             }
             if (boton.equals("n")){
                System.out.println("¿Desea pasar a modo Battloid o Gerwalk o desea despegar? b/g/d");
                Scanner scanner= new Scanner(System.in);
                String boton2;
                boton2=scanner.nextLine();
                if (boton2.equals("b")){
                    setCambiar(2);
                    cambiare = 2;
                    p++;

                }

                if (boton2.equals("g")){
                    setCambiar(3);
                    cambiare = 3;
                    p++;

                }

                if (boton2.equals("d")) {
                    panel.setAltura(random.nextInt(950) + 50);
                    cambiare = 1;
                    p++;

                }

                if (!boton2.equals("b") && !boton2.equals("g") && !boton2.equals("d")){
                    System.out.println("La tecla presionada no es valida.");
                    p=0;
             }

            }

             if (!boton.equals("n") && !boton.equals("s")) {
                System.out.println("La tecla presionada no es valida.");
                 p=0;
             }
        }return cambiare;
    }



    public int movimientosAire() {

        System.out.println("(a) Giro derecha. \t (d) Giro izquerda. \t (w) Aumentar altura. \t (s) Disminuye altura.");
        System.out.println("(t) Acelerar. \t (g) Desacelerar. \t (h) Cambiar a modo Battloid. \t(j) Cambiar a modo Gerwalk. \t (f) Cambiar a modo Figher.\n" );
        imprimirArmas(b_der.getArma());
        imprimirArmas(b_izq.getArma());



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

            case "t":
                if (panel.getVelocidad() <= 750) {
                    panel.setVelocidad(panel.getVelocidad() + 100);
                    System.out.println("Velocidad" + panel.getVelocidad());
                    break;
                } else System.out.println("Imposible  acelerar, ya ha alcanzado el maximo");
                break;

            case "g":
                if (panel.getVelocidad() >= 100) {
                    panel.setVelocidad(panel.getVelocidad() - 100);
                    System.out.println("Velocidad" + panel.getVelocidad());
                    break;
                } else {
                    System.out.println("Imposible  desacelerar, ya ha alcanzado el minimo");
                    break;
                }



            case "h":{
                if (((panel.getAltura() < 200) && (panel.getEstado().equals("Gerwalk"))) || ((panel.getAltura() < 200) && (panel.getEstado().equals("Fighter")))){
                   setCambiar(2);
                    System.out.println("ESTADO DEL ROBOT" +
                            "\n \t Modo: " + panel.getEstado() +
                            "\n \t Altura: " + panel.getAltura() +
                            " metros\n \t Largo pista: " + panel.getL_pista() +
                            " metros \n \t Velocidad: " + panel.getVelocidad() +
                            " km/h \n \t Posicion: " + panel.getPos_robot() + " metros\n" +
                            "-----------------------------------------------------------------------------------------");
                    return 2;
                }
                else{
                    System.out.println("No cumple con los requerimientos basicos para cambiar de forma");break;
                }
            }

            case "j":{
                if ((panel.getEstado().equals("Fighter")) || ((panel.getAltura() == 0) && (panel.getEstado().equals("Battloid")))) {
                    setCambiar(3);
                    System.out.println("ESTADO DEL ROBOT" +
                            "\n \t Modo: " + panel.getEstado() +
                            "\n \t Altura: " + panel.getAltura() +
                            " metros\n \t Largo pista: " + panel.getL_pista() +
                            " metros \n \t Velocidad: " + panel.getVelocidad() +
                            " km/h \n \t Posicion: " + panel.getPos_robot() + " metros\n" +
                            "-----------------------------------------------------------------------------------------");
                    return 3;

                }
                else {
                    System.out.println("No cumple con los requerimientos basicos para cambiar de forma");break;
                }
            }





            case "f":{
                if (panel.getEstado().equals("Gerwalk") && panel.getAltura()>0){
                   setCambiar(1);
                    System.out.println("ESTADO DEL ROBOT" +
                            "\n \t Modo: " + panel.getEstado() +
                            "\n \t Altura: " + panel.getAltura() +
                            " metros\n \t Largo pista: " + panel.getL_pista() +
                            " metros \n \t Velocidad: " + panel.getVelocidad() +
                            " km/h \n \t Posicion: " + panel.getPos_robot() + " metros\n" +
                            "-----------------------------------------------------------------------------------------");break;
                }
                else {
                    System.out.println("No cumple con los requerimientos basicos para cambiar de forma");break;
                }
            }
            case "u": {
                if (a_der.getArmas().size() == 1)
                 a_der.getArmas().set(0, disparar(a_der.getArmas().get(0)));break;

            }
            case "i": {
                if (a_der.getArmas().size() == 2 )
                    a_der.getArmas().set(1, disparar(a_der.getArmas().get(1)));break;
            }

            case "o":{
                if (a_izq.getArmas().size() == 1)
                    a_izq.getArmas().set(0, disparar(a_izq.getArmas().get(0)));break;
            }

            case  "p":{
                if (a_izq.getArmas().size() < 1)
                    a_izq.getArmas().set(0, disparar(a_izq.getArmas().get(0)));break;

            }



        }




        return 1;
    }

    int avanzar = 5;
    int retroceder = 1;
    Timer timerCorrer = new Timer(1500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (estamina >=0){
                System.out.println("Battloid corriendo...\nEstamina disponible: " + estamina+ " %");
                estamina -= 10;
                panel.setPos_robot(panel.getPos_robot()+10);
                System.out.println("Avanzando 10 metros...distancia recorrida desde el hangar: " + panel.getPos_robot()+ " metros" +
                        "\n ------------------------------------------------------------------------------");

            }
        }
    });
    public int movimientosSuelo() {
        //SOLO SE MUEVE HACIA ADELANTE
        boton = scanner.nextLine();

        switch (boton) {
            //Avanzar
            case "r":{ retroceder = -1; break;}
            case "d": { //PIERNA DERECHA
                if (p_der.isAvanzar()) {
                    System.out.println("No puede avanzar/retroceder dos veces con la misma pierna");
                }
                if (!p_der.isAvanzar()) {
                    System.out.println( retroceder*(5) + " metros con la pierna derecha");
                    panel.setPos_robot(panel.getPos_robot() + avanzar*retroceder);
                    p_der.setAvanzar(true);
                    p_izq.setAvanzar(false);
                }
                System.out.println("ADVERTENCIA: para el siguiente paso recuerda que no puedes avanzar dos veces con la misma pierna");
                System.out.println("-----------------------------------------------------------------------");
                break;
            }
            case "a": { //PIERNA IZQUIERDA

                if (p_izq.isAvanzar()) {
                    System.out.println("No puede avanzar dos veces con la misma pierna");
                }
                if (!p_izq.isAvanzar()) {
                    System.out.println( retroceder*(5) +" metros con la pierna izquierda");
                    panel.setPos_robot(panel.getPos_robot() + avanzar*retroceder);
                    p_izq.setAvanzar(true);
                    p_der.setAvanzar(false);
                }
                System.out.println("ADVERTENCIA: para el siguiente paso recuerda que no puedes avanzar dos veces con la misma pierna");
                System.out.println("-----------------------------------------------------------------------");
                break;
            }
            case "k":{//Modo correr
                System.out.println("Modo correr activado");
                while (estamina > 0) {
                        timerCorrer.start();
                        if (estamina <=0){
                            timerCorrer.stop();
                        }
                    if (estamina < 0) {
                        System.out.println("Battloid no puede correr. Estamina baja.\n" +
                                "Para recupar estamina camine");
                    }
                }
                ////////////////////ARTURO: DESACTIVAR ARMAS!!!!!
                break;
            }

            default: {
                System.out.println("ERROR al leer tecla ingresada, vuelva a presionar");
                System.out.println("-----------------------------------------------------------------------");
                break;
            }
        }
        return 1;
    }



}
