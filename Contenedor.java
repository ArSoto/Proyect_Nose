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
    private Scanner scanner = new Scanner(System.in);


    private String boton;
    private String boton1;
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
                System.out.println("_________________________________________________________________");
                System.out.println("\tPara controlar modo Fighter utilice las siguientes teclas:");

                System.out.println("__________________________________________________________________");
                break;
            }
            case("Battloid"):{
                System.out.println("__________________________________________________________________");
                System.out.println("Para controlar modo Battloid utilice las siguientes teclas:");

                System.out.println("____________________________________________________________________");
                break;

            }
            case("Gerwalk"):{
                System.out.println("Para controlar modo Gerwalk utilice las siguientes teclas:");

                System.out.println("____________________________________________________________________");
                break;
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }



////////////////////////////////////////////////////////////////////////////////////////////////////////ARTUROOOOOOOOOOOOOOOOOOOOOO
    //asigna un array de Clase arma a las extremidades que puden utilizarlas

    public void asignarArmas(){

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
            desactivarArmas(izq, der, "Desactivar");
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
            for (Armas j: der){
                if (j.getBoton().equals(letra)) {
                    if (j.getTipo().equals("Canion")) desactivarArmas(izq, der, letra);return;

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


        System.out.println("Elija el canion que desea ocupar en modo Battloid");

        for (Armas i: arrayListDer ){

            if(i.getTipo().equals("Canion"))
                System.out.println("[" + i.getBoton() + "] Canion con "+ i.getBalas());

        }


        for (Armas j: arrayListIzq){


            if(j.getTipo().equals("Canion"))
                System.out.println("[" + j.getBoton() + "] Canion con "+ j.getBalas());

        }

    }
    private void desactivarArmas(ArrayList<Armas> arrayListDer, ArrayList<Armas> arrayListIzq, String letra) { //solo deja activa l


        for (Armas i : arrayListDer) {
            if (!i.getBoton().equals(letra)) {
                i.setMostrar(false);
            }
        }
        for (Armas i : arrayListIzq) {
            if (!i.getBoton().equals(letra)) {
                i.setMostrar(false);
            }

        }

        System.out.println("Armas desactivadas :-) ");
    }

    private void activarArmas(ArrayList<Armas> arrayListDer, ArrayList<Armas> arrayListIzq){ //activa Armas para usarlas en modo avion o pajaro

        for (Armas i : arrayListDer) {

                i.setMostrar(true);

        }
        for (Armas i : arrayListIzq) {

                i.setMostrar(true);


        }

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

    public  void  setCambiar(String cambiar) {

        switch (cambiar){

            //Fighter: Avion
            case ("Fighter"): {
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
            case ("Battloid"):{
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
            case ("Gerwalk"):{
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
                p++;

             }
             if (boton.equals("n")){
                System.out.println("¿Desea pasar a modo Battloid o Gerwalk o desea despegar? b/g/d");
                Scanner scanner= new Scanner(System.in);
                String boton2;
                boton2=scanner.nextLine();
                if (boton2.equals("b")){
                    panel.setEstado("Battloid");
                    cambiare = 2;
                    p++;

                }

                if (boton2.equals("g")){
                    panel.setEstado("Gerwalk");
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


    public void cambiar_Estado() {

        //DE GERWALK A BATTLOID O FIGHTER
        if (((panel.getAltura() < 200) && (panel.getEstado().equals("Gerwalk")))){
            panel.setEstado("Battloid");
            System.out.println(panel.getEstado() +
                    "-----------------------------------------------------------------------------------------");
        }
        else if (panel.getAltura()>0){
            panel.setEstado("Fighter");
            System.out.println(panel.getEstado() +
                    "-----------------------------------------------------------------------------------------");
        }

        //DE FIGHTER A BATTLOID O GERWALK

        //CAMBIAR DE GERWALK O FIGHTER A BATTLOID

        if ((panel.getAltura() < 200) && (panel.getEstado().equals("Fighter"))){
            panel.setEstado("Battloid");
            System.out.println(panel.getEstado() +
                    "-----------------------------------------------------------------------------------------");
        }
        else {
            panel.setEstado("Gerwalk");
            System.out.println(panel.getEstado() +
                    "-----------------------------------------------------------------------------------------");
        }

        //DE BATTLOID A GERWALK
        if(((panel.getAltura() == 0) && (panel.getEstado().equals("Battloid")))){
            panel.setEstado("Gerwalk");
            System.out.println(panel.getEstado() +
                    "-----------------------------------------------------------------------------------------");
        }


    }

    public void movimientosAire() {

        imprimirArmas(b_der.getArma());
        imprimirArmas(b_izq.getArma());

        System.out.println("(a) Giro derecha. \t (d) Giro izquerda. \t (w) Aumentar altura. \t (s) Disminuye altura. \n" +
                "(i) Acelerar. \t (k) Desacelerar.  \t (b) Cambiar a modo Battloid. \t(g) Cambiar a modo Gerwalk. \n");

        boton = scanner.nextLine();


        switch (boton) {


            case "a":

                System.out.println("El avion ha avanzado hacia la izquierda" +
                        "-----------------------------------------------------------------------------------------");
                break;

            case "d":
                System.out.println("El avion ha avanzado hacia la derecha" +
                        "-----------------------------------------------------------------------------------------");
                break;

            case "w": {
                if (panel.getAltura() < 1000) {
                    panel.setAltura(panel.getAltura() + 100);
                    System.out.println("El avion ha subido: " + panel.getAltura() + " metros." +
                            "-----------------------------------------------------------------------------------------");
                    break;
                } else System.out.println("El avion ya esta en su altura maxima de vuelo");
                break;
            }

            case "s": {
                if (panel.getAltura() > 0) {
                    if (panel.getAltura() <= 21) {
                        panel.setAltura(0);
                        System.out.println("El avion ya esta en su altura minima de vuelo: " + panel.getAltura() + " metros.");break;
                    } else {
                        panel.setAltura(panel.getAltura() - 21);
                        System.out.println("El avion ha bajado: " + panel.getAltura() + " metros." +
                            "-----------------------------------------------------------------------------------------");
                    break;

                    }
                }

            }

            case "i":
                if (panel.getVelocidad() <= 650) {
                    panel.setVelocidad(panel.getVelocidad() + 100);
                    System.out.println("ESTADO DEL ROBOT" +
                            "\n \t Modo: " + panel.getEstado() +
                            "\n \t Altura: " + panel.getAltura() +
                            " metros\n \t Largo pista: " + panel.getL_pista() +
                            " metros \n \t Velocidad: " + panel.getVelocidad() +
                            " km/h \n \t Posicion: " + panel.getPos_robot() + " metros\n" +
                            "-----------------------------------------------------------------------------------------");
                    break;
                } else System.out.println("Imposible  acelerar, ya ha alcanzado el maximo");
                break;

            case "k": {
                if (panel.getVelocidad() > 0) {
                    if (panel.getVelocidad() <= 100) {
                        panel.setVelocidad(0);
                        System.out.println("El avion ya esta en su velocidad minima de vuelo: " + panel.getVelocidad() + " km/h.");break;
                    } else {
                        panel.setVelocidad(panel.getVelocidad() - 100);
                        System.out.println("El avion ha bajado su velocidad: " + panel.getVelocidad() + " km/h." +
                            "-----------------------------------------------------------------------------------------");
                    break;

                    }
            }
            }


            case "b": {
                cambiar_Estado();
            }

           //-----> CAMBIAR DE FIGHTER O BATTLOID A GERWALK
            case "g": {
                cambiar_Estado();
            }
            default: {
                System.out.println("La tecla dictada no es valida.");

        }


    }
    }

    int avanzar = 5;
    int retroceder = 1;
    Timer timerCorrer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (estamina >=0 && !"x".equals(boton)){
                System.out.println("Battloid corriendo...\nEstamina disponible: " + estamina+ " %");
                estamina -= 10;
                panel.setPos_robot(panel.getPos_robot()+10);
                System.out.println("Avanzando 10 metros...distancia recorrida desde el hangar: " + panel.getPos_robot()+ " metros" +
                        "\n ------------------------------------------------------------------------------");
                /*boton = scanner.nextLine();
                if ("x".equals(boton)) {
                    timerCorrer.stop();
                    System.out.println("Ha detenido el modo correr. Ahora puede caminar...");
                }*/
            }
            if(estamina <= 0){
                System.out.println("Battloid dice: 'Mi estamina esta baja...No puedo correr :'C'");
            }

        }
    });

    public void movimientosSuelo() {

        //armaBattloid(b_izq.getArma(),b_der.getArma());
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
                System.out.println("MODO CORRER ACTIVADO!!!\n");
                while (estamina > 0) {
                        timerCorrer.start();
                }
                            timerCorrer.stop();
                System.out.println("Battloid no puede correr...\n" +
                        "Para recupar estamina camine");
                break;
            }
            default: {
                   System.out.println("ERROR al leer tecla ingresada, vuelva a presionar");
                    System.out.println("-----------------------------------------------------------------------");
                    break;
                }
            }

    }

    Timer caidaBattloid = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(panel.getAltura() > 0){
                panel.setAltura(panel.getAltura()-10);
                System.out.println("Battloid cayendo...\nAltura:" + panel.setAltura(panel.getAltura()-10) + " metros");
            }
        }
    });

   /* public void cambioEstado(String boton) {
        //ACTIVAR ARMA CUANDO HAY CAMBIO: BATTLOID --> GERWALK O GERWALK --> AVION !!!!!!!!!!!!!!!!!!!!!
        //ARMAS BATTLOID: FIGHTER --> BATTLOID O GERWALK --> BATTLOID
        //GERWALK --> FIGHTER: CAMBIOARMABA
        //FIGHTER --> GERWALK: CAMBIOARMAAB
        //FIGHTER --> BATTLOID
        switch(boton){ //FIGHTER A BATTLOID
            case "b": {
                if ("Fighter".equals(getCambiar()) || "Gerwalk".equals(getCambiar())) {
                    if (panel.getAltura() > 200) {
                        System.out.println("No cumple las condiciones para cambiar a modo Battloid...\nDebe disminuir su altura a 200 metros");
                    }
                    while (panel.getAltura() <= 200 && panel.getAltura() >= 0) {
                        caidaBattloid.start();
                    }
                    caidaBattloid.stop();
                    System.out.println("Battloid ha llegado al nivel del suelo");
                }

            }

        }
    }*/

    /*public void movimientosPajaros() {
        //if (panel.getAltura() == 0) {
        //Altura < 200;
        System.out.println("¿Que desea? ((V: Volar/ C: Caminar))");
        boton = scanner.nextLine();
        switch (boton){

            case("c"): {
            System.out.println("GERWALK puede caminar");
            /*boton = scanner.nextLine();
            switch (boton) {
                //Avanzar
                case "r": {
                    retroceder = -1;
                    break;
                }
                case "d": { //PIERNA DERECHA
                    if (p_der.isAvanzar()) {
                        System.out.println("No puede avanzar/retroceder dos veces con la misma pierna");
                    }
                    if (!p_der.isAvanzar()) {
                        System.out.println(retroceder * (5) + " metros con la pierna derecha");
                        panel.setPos_robot(panel.getPos_robot() + avanzar * retroceder);
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
                        System.out.println(retroceder * (5) + " metros con la pierna izquierda");
                        panel.setPos_robot(panel.getPos_robot() + avanzar * retroceder);
                        p_izq.setAvanzar(true);
                        p_der.setAvanzar(false);
                    }
                    System.out.println("ADVERTENCIA: para el siguiente paso recuerda que no puedes avanzar dos veces con la misma pierna");
                    System.out.println("-----------------------------------------------------------------------");
                    break;
                }
                case "k": {//Modo correr
                    System.out.println("MODO CORRER ACTIVADO!!!\n");
                    while (estamina > 0) {
                        timerCorrer.start();
                    }
                    timerCorrer.stop();
                    System.out.println("Gerwalk no puede correr...\n" +
                            "Para recupar estamina camine");
                    break;
                }
                case "w": {
                    if (panel.getAltura() < 190) {
                        panel.setAltura(panel.getAltura() + 10);
                        System.out.println("GERWALK ha subido: " + panel.getAltura() + " metros." +
                                "-----------------------------------------------------------------------------------------");
                        break;
                } else {
                        System.out.println("Gerwalk ya esta en su altura maxima de vuelo");
                break;
            }
            }
                case "f":{
                    cambiar_Estado();
                }
            default: {
                System.out.println("ERROR al leer tecla ingresada, vuelva a presionar");
                System.out.println("-----------------------------------------------------------------------");
                break;
            }
        }
    }
        else{
            boton = scanner.nextLine();

            switch (boton) {


                case "a": {
                    System.out.println("GERWALK ha avanzado hacia la izquierda" +
                            "-----------------------------------------------------------------------------------------");
                    break;
                }

                case "d": {
                    System.out.println("GERWALK ha avanzado hacia la derecha" +
                            "-----------------------------------------------------------------------------------------");
                    break;
                }

                case "w": {
                    if(panel.getAltura()>200){
                        panel.setAltura(200);
                    }
                    if (panel.getAltura() < 190) {
                        panel.setAltura(panel.getAltura() + 10);
                        System.out.println( "GERWALK ha subido: " + panel.getAltura() + " metros." +
                                "-----------------------------------------------------------------------------------------");
                        break;
                    } else {
                        System.out.println("Gerwalk ya esta en su altura maxima de vuelo");
                        break;
                    }
                }


                case "s": {
                    if (panel.getAltura() > 0) {
                        if (panel.getAltura()<= 21){
                            panel.setAltura(0);
                            System.out.println("Gerwalk ya esta en su altura minima de vuelo: " +panel.getAltura()+" metros." );
                        }
                        else {
                            panel.setAltura(panel.getAltura() - 21);
                            System.out.println("GERWALK ha bajado: " + panel.getAltura() + " metros." +
                                    "-----------------------------------------------------------------------------------------");
                            break;

                        }
                    }
                }break;


                case "i": {
                    if (panel.getVelocidad() <= 750) {
                        panel.setVelocidad(panel.getVelocidad() + 100);
                        System.out.println("GERWALK ha acelerado a: " + panel.getVelocidad() + " km/h." +
                                "-----------------------------------------------------------------------------------------");
                        break;
                    } else {
                        System.out.println("Imposible  acelerar, ya ha alcanzado el maximo");
                        break;
    }
    }

                case "k": {
                    if (panel.getVelocidad() >= 100) {
                        panel.setVelocidad(panel.getVelocidad() - 100);
                        System.out.println("GERWALK ha desacelerado a: " + panel.getVelocidad() + "km/h" +
                                "-----------------------------------------------------------------------------------------");
                        break;
                    } else {
                        System.out.println("Imposible  desacelerar, ya ha alcanzado el minimo");
                        break;
    }
        }
                default:{
                    System.out.println("ERROR al leer tecla ingresada, vuelva a presionar");
                    System.out.println("-----------------------------------------------------------------------");
                    break;
    }
            }

        }

    }
    //}*/
///*****************************************
    //kathie

    public int GuardarRobot(int c) {

        if(panel.getAltura()==0 && panel.getEstado().equals("Fighter") && panel.getPos_robot()==0 && panel.getVelocidad()==0){

            System.out.println("¿Desea guardar robot? s/n");

            boton=scanner.nextLine();
            if (boton.equals("s")){
                c = 0;
            }
            if (boton.equals("n")){
                c = 1;
            }
            else {
                while (!boton.equals("s") && !boton.equals("n"))
                System.out.println("La tecla ingresada no es valida, intente nuevamente.");
                c=0;
            }
        }
        return c;
    }
}

