import java.util.Scanner;
import java.util.Random;

    public class Controlador {




        public static void controlador(){


            Contenedor robot = new Contenedor(1);
            int estado=1;


            robot.iniciarPista();

            int m=1;

            while(m==1) {

            robot.getEstado();
                m = robot.setDespegarModoAvion(robot.panel.getVelocidad(), robot.panel.getAltura(), robot.panel.getL_pista());
                robot.condiciones_Despegar();

            }

            estado=robot.Despegar();
            int c=1;
            while(c==1) {



                if (estado==1)
                {
                    do {
                        robot.getEstado();
                        estado=  robot.movimientosAire();

                    }while (estado==1);
                }
                if (estado == 2)
                {
                    do {
                        robot.getEstado();
                        estado= robot.movimientosSuelo();
                    }while(estado==2);
                }

                /*if (estado == 3)
                {
                    do {
                    robot.getEstado();
                        robot.movimientosPajaros();
                    }while(estado=true);
                }
                */
            }


        }





    }












