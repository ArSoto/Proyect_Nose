import java.util.Scanner;
import java.util.Random;

    public class Controlador {




        public static void controlador(){


            Contenedor robot = new Contenedor(1);
            boolean estado;


            robot.iniciarPista();



            robot.getEstado();
            robot.setCambiar(1);
            int cont = 0;
            do {
               estado= robot.movimientosSuelo(cont);

            }while (estado=true);


        }





    }












