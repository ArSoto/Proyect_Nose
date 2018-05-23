import java.util.Scanner;
import java.util.Random;

public class Controlador {

    Contenedor robot = new Contenedor();



    public void controlador(){

        robot.menuPrincipal();
        robot.asignarArmas();
        robot.panel.setEstado("Fighter");





            if (robot.panel.getEstado().equals("Fighter"))
            {
                do {
                    robot.getEstado();
                    if (robot.panel.getAltura() == 0){
                        robot.avionSuelo();
                    }
                    else {
                        robot.movimientosAire();
                    }


                }while (robot.panel.getEstado().equals("Fighter"));
            }
            if (robot.panel.getEstado().equals("Battloid"))
            {

                do {
                    robot.getEstado();
                    robot.movimientosSuelo();
                }while(robot.panel.getEstado().equals("Battloid"));
            }

            if (robot.panel.getEstado().equals("Gerwalk"))
            {

                do {
                    robot.getEstado();
                    robot.movimientosPajaros();
                }while(robot.panel.getEstado().equals("Gerwalk"));
            }

        }


    }





