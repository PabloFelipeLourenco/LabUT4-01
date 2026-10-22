package ut4.pruebas;

import java.util.List;
import ut4.modelo.Nave;
import ut4.util.HibernateUtil;

public class MainHangar {

    public static void main(String[] args) {
        HangarControl control = new HangarControl(); //Creamos un objetoo para comunicarnos con la clase HangarControl

        try {
            System.out.println("===== SISTEMA DE CONTROL DE HANGARES =====");

            //Registra una nueva nave
            Nave nave = new Nave(
                    "TIE Interceptor",
                    "TI-v2",
                    1,
                    "Critico"
            );

            control.registrarNuevaNave(nave); //Le pasamos la nave que estamos registrando a la funcion de registrar

            //Actualizar el estado
            if (nave.getId() != 0) { //Coge el id de la nave y que no pueda ser 0 (Empieza en el 1 obviamente) 
                control.actualizarEstadoNave(
                        nave.getId(), //Le pasamos el id de la nave que vamos a actualizar
                        "Estable" //Le pasamos el estado nuevo de la nave
                );
            }

            //Consultar las naves del modelo TI-v2
            System.out.println("\n===== ESCANEO DE CAZAS TI-v2 =====");

            List<Nave> naves = control.obtenerCazasPorModelo("TI-v2"); //LLama las naves que se encuentre y las guarda en un array 

            if (naves.isEmpty()) { //Si no encuentra ninguna nave
                System.out.println("No se encontraron cazas de este modelo.");
            } else {
                for (Nave caza : naves) { //Bucle que hace que mientras que encuentre naves que las recorra todas
                    System.out.println(caza); //Mientras que vaya imprimiendo las naves
                }
            }

        } finally {
            HibernateUtil.shutdown(); //Sirve para cerrar la conexion con la base de datos si o si se de un error o no
        }
    }
}
