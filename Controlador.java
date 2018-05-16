import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Iterator;

    public class Controlador {




        public static void controlador(){


            Contenedor robot = new Contenedor(1);
            boolean estado;


            //robot.setArmasArray();
            //robot.imprimirArmas(robot.b_der.getArma(), "derecho");

            robot.getEstado();
            robot.imprimirControl();
            robot.setCambiar(2);

            do{
                robot.movimientosSuelo();
            }while(true);



        }



        ///COMPROBAR CAMBIO DE ESTADO
        ///MODO CORRER: DESACTIVAR ARMAS
    }












