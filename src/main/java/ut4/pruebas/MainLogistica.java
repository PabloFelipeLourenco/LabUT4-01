package ut4.pruebas;

import java.util.List;
import ut4.modelo.Nave;
import ut4.modelo.Planeta;
import ut4.pruebas.HangarControl;
import ut4.util.HibernateUtil;

public class MainLogistica {

    public static void main(String[] args) {
        // Creamos los objetos para gestionar los hangares y los planetas
        HangarControl control = new HangarControl();
        PlanetarioControl pc = new PlanetarioControl();

        try {
            System.out.println("===== SISTEMA DE CONTROL DE HANGARES =====");

            // Creamos un planeta con su nombre y sector galáctico
            Planeta planeta = new Planeta(
                    "Astillero de Corellia",
                    "Mundos del Núcleo"
            );

            // Creamos las naves que pertenecen al planeta
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

            // Añadimos las naves al planeta y registramos el planeta
            planeta.agregarNave(tieBasico);
            planeta.agregarNave(tieBomber);
            pc.registrarNuevoPlaneta(planeta);

            // Obtenemos el ID y recuperamos el planeta de la base de datos
            int idPlaneta = planeta.getId();
            Planeta recuperado = pc.obtenerPlanetaPorId(idPlaneta);

            // Comprobamos si se ha encontrado el planeta
            if (recuperado == null) {
                System.out.println("No se encontró el planeta con ID " + idPlaneta);
            } else {
                System.out.println("\n===== MANIFIESTO DE " + recuperado.getNombrePlaneta().toUpperCase() + " =====");
                System.out.println(recuperado);

                // Mostramos todas las naves del planeta
                for (Nave n : recuperado.getListaNaves()) {
                    System.out.println(" - " + n);
                }
            }

        } finally {
            // Cerramos Hibernate aunque se produzca un error
            HibernateUtil.shutdown();
        }
    }
}
