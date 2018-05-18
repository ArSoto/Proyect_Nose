import java.util.Scanner;
import java.util.Random;

public class Controlador {



    public void controlador(){
        Contenedor robot = new Contenedor(1);
        robot.menuPrincipal();
        robot.asignarArmas();


        int m=1;

        while(m==1) {

            robot.getEstado();

            m = robot.setDespegarModoAvion( robot.panel.getAltura());
            robot.condiciones_Despegar();
        }



        robot.Despegar();
        int c=1;
        while(c==1) {



            if (robot.panel.getEstado().equals("Fighter"))
            {
                robot.setCambiar("Fighter");
                do {
                    robot.getEstado();
                    robot.movimientosAire();

                }while (robot.panel.getEstado().equals("Fighter"));
            }
            if (robot.panel.getEstado().equals("Battloid"))
            {
                robot.setCambiar("Battloid");
                do {
                    robot.getEstado();
                    robot.movimientosSuelo();
                }while(robot.panel.getEstado().equals("Battloid"));
            }

            if (robot.panel.getEstado().equals("Gerwalk"))
            {
                robot.setCambiar("Gerwalk");
                do {
                    robot.getEstado();
                    robot.movimientosPajaros();
                }while(robot.panel.getEstado().equals("Gerwalk"));
            }

        }


    }





}