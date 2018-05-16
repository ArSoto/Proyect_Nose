import java.util.Scanner;
import java.util.Random;

    public class Controlador {




        public static void controlador(){


            Contenedor robot = new Contenedor(1);
            boolean estado;
            robot.asignarArmas();

            robot.imprimirArmas(robot.b_der.getArma());
            robot.imprimirArmas(robot.b_izq.getArma());

            robot.armaBattloid(robot.b_izq.getArma(), robot.b_der.getArma());








            robot.getEstado();
            robot.setCambiar(1);
            int cont = 0;



        }





    }












