import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.function.BinaryOperator;
import javax.swing.Timer;

public class Contenedor {
    int min = 20;
    private int cambiar;
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    private int avanzar = 5;
    private int retroceder = 1;
    private int caminar = 1;
    private String boton;
    private String boton1;
    private int contBoton = 0;
    private Armas armas;
    private String tempString;
    private int[] contadorArmas = {0, 0};
    private ArrayList<Armas> armasArrayListDer, armasArrayListIzq;

    int estamina = random.nextInt(100) + 1;
////////////////////////////////////////////////////////////////////

    int comenzar = 0;

    public int iniciarPista() {
        comenzar = 1;
        return 1;
    }

    public void imprimirControl() { //Imprime controles de los robots
        switch (panel.getEstado()) {
            case "Fighter": {
                System.out.println("\n_________________________________________________________________");
                System.out.println("\nPara controlar modo Fighter utilice las siguientes teclas:");
                System.out.println("\n\t(A) Giro derecha. \n\t (D) Giro izquerda. \n\t (W) Aumentar altura. \n\t (S) Disminuye altura. \n" +
                        "\t(I) Acelerar. \n\t (K) Desacelerar. \n\t (B) Cambiar a modo Battloid. \n\t(G) Cambiar a modo Gerwalk. \n\t(N): Guardar en hangar");
                if (panel.getAltura() <= min && "Fighter".equals(panel.getEstado())) System.out.print(" (Z) Crear pista.\n");
                if (panel.getPista() && "Fighter".equals(panel.getEstado())) System.out.print("(X) Aterrizar");

                System.out.println("__________________________________________________________________");
                break;
            }
            case ("Battloid"): {
                System.out.println("__________________________________________________________________");
                System.out.println("\n\tPara controlar modo Battloid utilice las siguientes teclas:" +
                        "(A): Avanzar con pierna izquierda \n\t(D): Avanzar con pierna derecha \n\t(R): Retroceder" +
                        "\nPara retroceder debe presionar (R), enter y luego la tecla de la pierna con la cual desea retroceder" +
                        "\n\t(G): Cambiar a modo Gerwalk" +
                        "\nADVERTENCIA: NO puedes avanzar dos veces con la misma pierna" +
                        "\n");

                System.out.println("____________________________________________________________________");
                break;

            }
            case ("Gerwalk"): {
                System.out.println("Para controlar modo Gerwalk utilice las siguientes teclas:");
                System.out.println("**CUANDO SE ENCUENTRE EN TIERRA: **\n\t");
                System.out.println("\n\t(A): Avanzar con pierna izquierda \n\t(D): Avanzar con pierna derecha \n\t(R): Retroceder \n\t(P): Disparar (P de pium)" +
                        "\nPara retroceder debe presionar (R), enter y luego la tecla de la pierna con la cual desea retroceder\n" +
                        "\nADVERTENCIA: NO puedes avanzar dos veces con la misma pierna\n" +
                        "PARA CAMBIAR VOLAR DESPUES DE CAMINAR: (F) ////// PARA CAMINAR DESPUES DE VOLAR: (V)");
                System.out.println("**CUANDO SE ENCUENTRE VOLANDO: **\n\t");
                System.out.println("\t(A) Giro derecha \n\t(D) Giro izquerda \n\t(W) Aumentar altura \n\t(S) Disminuye altura" +
                        "\n\t(I) Acelerar \n\t(K) Desacelerar \n\t(P) Disparar");

                System.out.println("____________________________________________________________________");
                break;
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }

    public void menuPrincipal() {
        System.out.println("\n--------------------------------------MENU DE OPCIONES-------------------------------------" +
                "\n\t(I)INICIAR PISTA...(EL INICIO SERÁ FORZADO DE TODAS MANERAS)" +
                "\n\t(A)VERIFICAR ESTADO" +
                "\n\t(B)CONTROLES DEL JUEGO " +
                "\n\t(D)CONTINUAR" +
                "\n--> RECUERDE: DESPUES DE CADA OPCION QUE ELIJA (LAS LETRAS SIEMPRE EN MINUSCULA) PRESIONE LA TECLA 'ENTER' PARA CONTINUAR" +
                "\n--> PARA VOLVER A VER EL MENU EN CUALQUIERA DE LOS MODOS PRESIONE (Q)" +
                "\n------------------------------------------------------------------------------------------------------");

        boton = scanner.nextLine();
        switch (boton) {
            case "i": {
                if (comenzar != 1) {
                    iniciarPista();
                    System.out.println("PISTA INICIADA...AHORA PUEDE CONTINUAR, ESTAS SON LAS CONDICIONES INICIALES:");
                } else {
                    System.out.println("PISTA YA FUE INICIADA");
                }
                break;
            }
            case "a": {
                if (comenzar == 1) {
                    getEstado();
                } else {
                    System.out.println("NO HA INICIADO PISTA");
                    iniciarPista();
                }
                break;
            }
            case "b": {
                if (iniciarPista() == 1) {
                    imprimirControl();
                } else {
                    System.out.println("NO HA INICIADO PISTA");
                    iniciarPista();
                }
                break;
            }
            case "c": {
                if (iniciarPista() == 1) {
                    //cambiar_Estado();
                } else {
                    System.out.println("NO HA INICIADO PISTA");
                    iniciarPista();
                }

                break;
            }
            case "d": {
                if (iniciarPista() == 1) {
                    System.out.println("Puede continuar jugando...Pista iniciada\n------------------------------------------------------------------");
                    iniciarPista();
                }
                break;
            }
            default: {
                System.out.println("\n-------------------------------------------------------------------------------------");
                comenzar = 1;
                break;
            }
        }

    }

    private int GuardarRobot ( int c){

        if (panel.getAltura() == 0 && panel.getEstado().equals("Fighter") && panel.getPos_robot() == 0 && panel.getVelocidad() == 0) {

            System.out.println("¿Desea guardar robot? (S): Sí/(N): No");

            boton = scanner.nextLine();
            if (boton.equals("s")) {
                c = 0;
            }
            if (boton.equals("n")) {
                c = 1;
            } else {
                while (!boton.equals("s") && !boton.equals("n"))
                    System.out.println("La tecla ingresada no es valida, intente nuevamente.");
                c = 0;
            }
        }
        return c;
    }

    ////////////////////////////////////////////////////////////////////TIMER
    Timer caidaBattloid = new Timer(500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (panel.getAltura() > 0) {
                panel.setAltura(panel.getAltura() - 10);
                System.out.println("Battloid cayendo...\nAltura:" + panel.setAltura(panel.getAltura() - 10) + " metros");
                System.out.println("Velocidad del robot: " + panel.setVelocidad(0) + "km/h");
            } else {
                caidaBattloid.stop();
            }
        }
    });

    Timer timerCorrer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (estamina >= 0 && !"x".equals(boton)) {
                System.out.println("Battloid corriendo...\nEstamina disponible: " + estamina + " %");
                estamina -= 10;
                panel.setPos_robot(panel.getPos_robot() + 10);
                System.out.println("Avanzando 10 metros...distancia recorrida desde el hangar: " + panel.getPos_robot() + " metros" +
                        "\n ------------------------------------------------------------------------------");
            }
            if (estamina <= 0) {
                timerCorrer.stop();
            }

        }
    });

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Contenedor(int cambiar) {
        this.cambiar = cambiar;
    }

    public int getCambiar() {
        return cambiar;
    }
    Panel_de_Control panel = new Panel_de_Control("Fighter", 0);
    Cabeza c = new Cabeza(false);
    Brazos b_der = new Brazos(0, 0, false, armasArrayListIzq); // 0: Estado fighter (ocultos)
    Brazos b_izq = new Brazos(0, 0, false, armasArrayListDer);
    Alas a_der = new Alas(true, armasArrayListDer);
    Alas a_izq = new Alas(true, armasArrayListIzq);
    Piernas p_der = new Piernas(false, false);
    Piernas p_izq = new Piernas(false, false);


    public Panel_de_Control getPanel() {
        return panel;
    }

    public void setPanel(Panel_de_Control panel) {
        this.panel = panel;
    }


    public void setCambiar(String cambiar) {

        switch (cambiar) {

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
            case ("Battloid"): {
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
            case ("Gerwalk"): {
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

    public void getEstado() {
        System.out.println("ESTADO DEL ROBOT" +
                "\n \t Modo: " + panel.getEstado() +
                "\n \t Altura: " + panel.getAltura() +
                " metros\n \t Largo pista: " + panel.getL_pista() +
                " metros \n \t Velocidad: " + panel.getVelocidad() +
                " km/h \n \t Posicion: " + panel.getPos_robot() + " metros\n" +
                "-----------------------------------------------------------------------------------------");
    }

    public void cambiar_Estado(String boton) {

        //DE GERWALK A BATTLOID O FIGHTER
        if ((boton.equals("b")) && (panel.getAltura() <= 200) && (panel.getEstado().equals("Gerwalk"))) {
            panel.setEstado("Battloid");
            System.out.println(panel.getEstado() +
                    "\n-----------------------------------------------------------------------------------------");
        } else if (boton.equals("f") && panel.getEstado().equals("Gerwalk") && panel.getAltura() > 0) {
            panel.setEstado("Fighter");
            System.out.println(panel.getEstado() +
                    "\n-----------------------------------------------------------------------------------------");
        }

        //CAMBIAR DE FIGHTER A BATTLOID O GERWALK

        if ((boton.equals("b")) && (panel.getAltura() < 200) && (panel.getEstado().equals("Fighter"))) {
            caidaBattloid.start();
            panel.setEstado("Battloid");
            System.out.println(panel.getEstado() +
                    "\n-----------------------------------------------------------------------------------------");
        } else if (boton.equals("g") && panel.getEstado().equals("Fighter")) {
            caidaBattloid.start();
            panel.setEstado("Gerwalk");
            System.out.println(panel.getEstado() +
                    "\n-----------------------------------------------------------------------------------------");
        }

        //DE BATTLOID A GERWALK
        if (((panel.getAltura() == 0) && (panel.getEstado().equals("Battloid")))) {
            panel.setEstado("Gerwalk");
            System.out.println("\n----------------------------------------------------------------------------");
        }
    }


    //////////////////.......MÉTODOS VUELO........//////////////////////////////

    public int setDespegarModoAvion(int altura) {


        if ((panel.getVelocidad() > 350) && (altura == 0) && ((getCambiar() == 1) || (getCambiar() == 3))) {
            System.out.println("---------------------------------|ESTA EN CONDICIONES DE VOLAR|--------------------------------" +
                    "\n----------------------------------------------------------------------------------------------");
            return 0;
        } else {
            System.out.println("No cumple condiciones para iniciar vuelo en modo Fighter, presione nuevamente la tecla (I).");
            boton = scanner.nextLine();
            return 1;
        }

    }

    public void condiciones_Despegar() {
        panel.setVelocidad(random.nextInt(450) + 300);
        panel.setPos_robot(random.nextInt(panel.getL_pista()));
    }

    public void Despegar() {
        while (true) {

            System.out.println("¿Desea despegar? (S): Sí/(N): No");
            boton = scanner.nextLine();

            if (boton.equals("s")) {
                panel.setAltura(random.nextInt(950) + 50);
                return;

            }
            if (boton.equals("n")) {
                avionSuelo();
                return;
            }

            if (!boton.equals("n") && !boton.equals("s"))
                System.out.println("La tecla presionada no es valida.");
        }
    }

    private void avionSuelo() {

        while (true) {
            System.out.println("¿Desea pasar a modo Battloid o Gerwalk o desea despegar? (B): Battloid/(G): Gerwalk /(D):Despegar de todos modos");

            String boton;
            boton = scanner.nextLine();
            if (boton.equals("b")) {
                panel.setEstado("Battloid");
                return;

            }

            if (boton.equals("g")) {
                panel.setEstado("Gerwalk");
                return;

            }

            if (boton.equals("d")) {
                panel.setAltura(random.nextInt(950) + 50);
                return;

            }

            if (!boton.equals("b") && !boton.equals("g") && !boton.equals("d")) {
                System.out.println("La tecla presionada no es valida.");

            }
        }
    }

    public boolean aterrizar() {

        while (true) {

            System.out.println("¿Desea aterrizar? (S): Sí/(N): No");
            boton = scanner.nextLine();

            if (boton.equals("s")) {
                panel.setAltura(0);
                panel.setVelocidad(0);

                System.out.println("Aterrizaje exitoso--");
                avionSuelo();
                return true;

            }
            if (boton.equals("n")) {
                return false;
            }


        }
    }

    public void movimientosAire() {

        if("Fighter".equals(panel.getEstado())) {
            imprimirArmas(b_der.getArma());
            imprimirArmas(b_izq.getArma());
        }
        //imprimirControl();

        System.out.println();

        boton = scanner.nextLine();


        switch (boton) {


            case "a": {

                System.out.println("El avion ha avanzado hacia la izquierda" +
                        "\n-----------------------------------------------------------------------------------------");
                break;
            }

            case "d": {
                System.out.println("El avion ha avanzado hacia la derecha" +
                        "\n-----------------------------------------------------------------------------------------");
                break;
            }

            case "w": {
                if (panel.getAltura() < 1000) {
                    panel.setAltura(panel.getAltura() + 100);
                    System.out.println("El avion ha subido: " + panel.getAltura() + " metros." +
                            "\n-----------------------------------------------------------------------------------------");
                    break;
                } else System.out.println("El avion ya esta en su altura maxima de vuelo");
                break;
            }

            case "s": {
                if (panel.getAltura() > 0) {
                    if (panel.getAltura() <= 21) {
                        panel.setAltura(0);
                        System.out.println("El avion ya esta en su altura minima de vuelo: " + panel.getAltura() + " metros.");
                        break;
                    } else {
                        panel.setAltura(panel.getAltura() - 21);
                        System.out.println("El avion ha bajado: " + panel.getAltura() + " metros." +
                                "\n-----------------------------------------------------------------------------------------");
                        break;

                    }
                }

            }

            case "i": {
                if (panel.getVelocidad() <= 650) {
                    panel.setVelocidad(panel.getVelocidad() + 100);
                    break;
                } else System.out.println("Imposible  acelerar, ya ha alcanzado el maximo");
                break;
            }

            case "k": {
                if (panel.getVelocidad() > 0) {
                    if (panel.getVelocidad() <= 100) {
                        panel.setVelocidad(0);
                        System.out.println("El avion ya esta en su velocidad minima de vuelo: " + panel.getVelocidad() + " km/h.");
                        break;
                    } else {
                        panel.setVelocidad(panel.getVelocidad() - 100);
                        System.out.println("El avion ha bajado su velocidad: " + panel.getVelocidad() + " km/h." +
                                "\n-----------------------------------------------------------------------------------------");
                        break;

                    }
                }
            }
            case "b": {
                cambiar_Estado("b");
                break;
            }
            case "g": {
                cambiar_Estado("g");
                caminar *= -1;
                break;
            }
            case "z": {
                if (panel.getAltura() <= min) {
                    panel.setPista(true);
                    System.out.println("Pista iniciada");
                    aterrizar();
                    break;
                }

            }
            case "x":
                if (panel.getPista()) {
                    if (aterrizar()) return;
                    else {
                        panel.getPista();
                        break;
                    }
                }

            case "c": {
                if (b_der.getArma().size() >= 1) disparar(b_der.getArma().get(0));
                break;

            }

            case "v": {
                if (b_der.getArma().size() >= 2) disparar(b_der.getArma().get(1));
                break;

            }

            case "n": {
                if (b_izq.getArma().size() >= 1) disparar(b_izq.getArma().get(0));
                break;

            }
            case "m": {
                if (b_izq.getArma().size() >= 2) disparar(b_izq.getArma().get(1));
                break;
            }
            case "q":{
                menuPrincipal();
            }
            default: {
                System.out.println("La tecla dictada no es valida.");
            }
        }
    }

////////////////////////...........METODOS CAMINAR.............//////////////////////////////

    public void movimientosSuelo() {
        if("Battloid".equals(panel.getEstado()) && panel.isArmasActivar()) {
            armaBattloid(b_izq.getArma(), b_der.getArma());
        }
        boton = scanner.nextLine();

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
                break;
            }
            case "k": {//Modo correr
                System.out.println("MODO CORRER ACTIVADO!!!\n");
                while (estamina > 0) {
                    timerCorrer.start();
                }

                System.out.println("Battloid no puede correr...\n" +
                        "Para recupar estamina camine");
                break;
            }
            case "g": {
                cambiar_Estado("g");
                activarArmas(b_der.getArma(), b_izq.getArma());
                break;
            }

            case "f": {
                caminar *= -1;
                break;
            }
            case "c": {
                if (b_der.getArma().size() >= 1) {
                    disparar(b_der.getArma().get(0));
                }
                break;
            }

            case "v": {
                if (b_der.getArma().size() >= 2) {
                    disparar(b_der.getArma().get(1));
                }
                break;

            }

            case "n": {
                if (b_izq.getArma().size() >= 1) {
                    disparar(b_izq.getArma().get(0));
                }
                break;
            }
            case "m": {
                if (b_izq.getArma().size() >= 2) {
                    disparar(b_izq.getArma().get(1));
                }
                break;
            }
            case "q":{
                menuPrincipal();
            }

            default: {
                System.out.println("ERROR al leer tecla ingresada, vuelva a presionar");
                System.out.println("-----------------------------------------------------------------------");
                break;
            }
        }

    }

///////////////////////////////////......GERWALK.......////////////////////////////////////////////////
    public void movimientosPajaros() {
        //imprimirControl();//MENU

        System.out.println("¿Que desea? ((V: Volar/ C: Caminar))");
        boton = scanner.nextLine();
        //Verificar si se puede caminar o volar
        switch (boton) {
            case ("c"): {
                while (caminar == 1) {
                    System.out.println("GERWALK puede caminar");
                    movimientosSuelo();
                }
                break;
            }
            case ("v"): {
                caminar *= -1;
                if (panel.getAltura() <= 200) {
                    System.out.println("GERWALK puede volar");
                    while (caminar == -1) {
                        movimientosAire();
                        if (panel.getAltura() > 200) {
                            System.out.println("No puede seguir ascendiendo...\nAltura maxima (Modo Gerwalk): 200 metros");
                        }
                    }
                    if (caminar == 1) {
                        caidaBattloid.start();
                    }
                    break;
                }
            }
            case ("f"):{
                cambiar_Estado("f");
            }
            case ("b"):{
                cambiar_Estado("b");
            }
        }
    }

    ///////////////////////////////////////////......ARMAS........../////////////////////////////////////////////////////////////
    //Asigna un array de Clase arma a las extremidades que puden utilizarlas

    public void asignarArmas() {

        b_der.setArma(setArmasArray());
        b_izq.setArma(setArmasArray());

        a_izq.setArmas(b_izq.getArma());
        a_der.setArmas(b_der.getArma());


    }

    // Cambio de armas de las alas a los brazos
    public void actualizarArmasAB() {

        b_der.setArma(a_der.getArmas());
        b_izq.setArma(a_izq.getArmas());

    }

    // cambio de Armas de los Brazos a las alas
    public void actualizarArmasBA() {
        a_der.setArmas(b_der.getArma());
        a_izq.setArmas(b_der.getArma());


    }

    // comprueba que el arma utilizada contiene municiones y de ser asi dispara
    private Armas disparar(Armas arma) {

        if (!arma.isMostrar()) {
            System.out.println("No posee arma asignada a ese boton");
            return arma;

        }

        if (arma.getBalas() <= 0) {
            System.out.println("El arma " + arma.getTipo() + " no tiene municiones ");
            return arma;

        }


        arma.setBalas(arma.getBalas() - 1);  //resta una municion
        System.out.println("Disparando...  \nMuniciones restantes:" + arma.getBalas());

        return arma;

    }

    public void armaBattloid(ArrayList<Armas> izq, ArrayList<Armas> der) {

        String letra;
        if (contadorArmas[0] == 0) {
            System.out.println("El Robot no posee ningun cañon que pueda utilizar");
            desactivarArmas(izq, der, "Desactivar");
            return;
        }

        while (true) {
            imprimirCaniones(der, izq);
            panel.setArmasActivar(false);
            letra = scanner.nextLine();

            for (Armas i : izq) {
                if (i.getBoton().equals(letra)) {
                    if (i.getTipo().equals("Canion")) desactivarArmas(izq, der, letra);
                    return;

                }

            }
            for (Armas j : der) {
                if (j.getBoton().equals(letra)) {
                    if (j.getTipo().equals("Canion")) desactivarArmas(izq, der, letra);
                    return;

                }


            }
            System.out.println("Tecla no valida");
        }

    }


    private Armas crearArmas() {           //Crea las armas de forma aleatoria, tanto el tipo como la cantidad de municiones

        int tempInt = random.nextInt(2) + 1;
        String botones[] = {"c", "v", "n", "m"}; // Servira para asignar boton a cada arma

        if (tempInt == 1) {

            tempString = "Canion";
            contadorArmas[0]++;
        } else {
            tempString = "Laser";
            contadorArmas[1]++;
        }

        armas = new Armas(random.nextInt(10) + 1, tempString, true, botones[contBoton]);
        contBoton++;

        return armas;
    }

    //Crea los arreglos para las armas

    private ArrayList<Armas> setArmasArray() {
        ArrayList<Armas> armasArrayList = new ArrayList<>();
        int tempInt;

        tempInt = random.nextInt(2) + 1;
        for (int i = 0; i < tempInt; i++) {
            armasArrayList.add(crearArmas());

        }
        return armasArrayList;

    }


    public void imprimirArmas(ArrayList<Armas> arrayList) {

        Iterator<Armas> iteArrayList = arrayList.iterator();
        while (iteArrayList.hasNext()) {
            Armas armas = iteArrayList.next();
            System.out.print("(" + armas.getBoton() + ")" + " Tipo:" + armas.getTipo() + " Balas:  " + armas.getBalas() + "\t");
        }
    }

    private void imprimirCaniones(ArrayList<Armas> arrayListDer, ArrayList<Armas> arrayListIzq) {


        System.out.println("Elija el canion que desea ocupar en modo Battloid");

        for (Armas i : arrayListDer) {

            if (i.getTipo().equals("Canion"))
                System.out.println("[" + i.getBoton() + "] Canion con " + i.getBalas());

        }


        for (Armas j : arrayListIzq) {


            if (j.getTipo().equals("Canion"))
                System.out.println("[" + j.getBoton() + "] Canion con " + j.getBalas());

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

    private void activarArmas(ArrayList<Armas> arrayListDer, ArrayList<Armas> arrayListIzq) { //activa Armas para usarlas en modo avion o pajaro

        for (Armas i : arrayListDer) {

            i.setMostrar(true);

        }
        for (Armas i : arrayListIzq) {

            i.setMostrar(true);


        }
        panel.setArmasActivar(true);

    }

    ///////////////////////////////////////////////////////////////



}

