package ut4.pruebas;

import java.util.List;
import ut4.modelo.Nave;
import ut4.modelo.Planeta;
import ut4.pruebas.HangarControl;
import ut4.util.HibernateUtil;

public class MainLogistica {

    public static void main(String[] args) {
        HangarControl control = new HangarControl(); //Creamos un objetoo para comunicarnos con la clase HangarControl
        PlanetarioControl pc = new PlanetarioControl();
        
        try {
            System.out.println("===== SISTEMA DE CONTROL DE HANGARES =====");
            Planeta planeta = new Planeta(
                    "Astillero de Corellia",
                    "Mundos del Núcleo"
            );
            
            //Registra una nueva nave
            Nave tieBasico = new Nave(
                    "Caza TIE Basico",
                    "TI-v2",
                    1,
                    "Critico",
                    planeta
            );
            
            Nave tieBomber = new Nave(
                    "Caza TIE Bomber",
                    "TI-v2",
                    1,
                    "Critico",
                    planeta
            );
             
            planeta.agregarNave(tieBasico);
            planeta.agregarNave(tieBomber);
            pc.registrarNuevoPlaneta(planeta);
                       
            int idPlaneta = planeta.getId();
            Planeta recuperado = pc.obtenerPlanetaPorId(idPlaneta);

            if (recuperado == null) {
                System.out.println("No se encontró el planeta con ID " + idPlaneta);
            } else {
                System.out.println("\n===== MANIFIESTO DE " + recuperado.getNombrePlaneta().toUpperCase() + " =====");
                System.out.println(recuperado);
                for (Nave n : recuperado.getListaNaves()) {
                    System.out.println(" - " + n);
                }
            }

        } finally {
            HibernateUtil.shutdown(); //Sirve para cerrar la conexion con la base de datos si o si se de un error o no
        }
    }
}
